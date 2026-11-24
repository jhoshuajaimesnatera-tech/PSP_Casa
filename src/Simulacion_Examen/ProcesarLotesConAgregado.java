package Simulacion_Examen;

import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;

public class ProcesarLotesConAgregado { // Ejecuta Trabajador en paralelo con redirección de salida/error, ignora fallos y acumula de forma segura con FileLock
    public static void main(String[] args) throws Exception {
        int[] limites = {500_000, 1_500_000, 2_500_000}; // Límites que calculará cada proceso Trabajador en paralelo.
        Path carpetaSalida = Path.of("salida_examen"); // Directorio donde se guardarán los archivos .txt y .err de cada trabajador.
        Files.createDirectories(carpetaSalida); // Crea la carpeta si no existe sin lanzar error si ya estaba creada.

        List<Process> procesos = new ArrayList<>(); // Lista con los procesos lanzados para esperarlos después.
        List<Integer> limitesLanzados = new ArrayList<>(); // Guarda el límite asociado a cada proceso en el mismo orden.

        for (int n : limites) { // Lanza todos los procesos de forma concurrente antes de llamar a waitFor().
            ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.Trabajador", String.valueOf(n)); // Prepara el hijo Trabajador pasando el límite n.
            pb.redirectOutput(new File(carpetaSalida.toFile(), "trab_" + n + ".txt")); // Redirige su salida estándar ("n;cuantos") a su propio archivo .txt.
            pb.redirectError(new File(carpetaSalida.toFile(), "trab_" + n + ".err")); // Redirige su salida de error a un archivo .err independiente.
            procesos.add(pb.start()); // Inicia el proceso y lo añade a la lista.
            limitesLanzados.add(n); // Registra qué límite corresponde a ese proceso.
        }

        int sumaLote = 0; // Suma acumulada de primos de los trabajadores que hayan terminado correctamente (código 0).

        for (int i = 0; i < procesos.size(); i++) { // Recorre cada proceso lanzado para esperar su fin y comprobar su resultado.
            int n = limitesLanzados.get(i); // Límite asociado a este trabajador.
            Process p = procesos.get(i); // Instancia Process del trabajador i.
            int codigo = p.waitFor(); // Espera a que termine este trabajador y obtiene su código de salida.

            if (codigo != 0) { // Si el trabajador falló, avisa y no suma su archivo.
                System.out.println("Aviso: el trabajador con límite " + n + " falló con código " + codigo + "; no se suma."); // Muestra qué trabajador falló.
                continue; // Salta a la siguiente iteración sin leer ni sumar su resultado.
            }

            Path archivoRes = carpetaSalida.resolve("trab_" + n + ".txt"); // Ruta del archivo de salida generado por este trabajador.
            String contenido = Files.readString(archivoRes).strip(); // Lee "n;cuantos" y elimina espacios o saltos de línea finales.
            String[] partes = contenido.split(";"); // Divide en límite (índice 0) y cantidad de primos (índice 1).
            int cuantos = Integer.parseInt(partes[1]); // Extrae y convierte a entero el recuento de primos.
            sumaLote += cuantos; // Acumula este recuento válido en la suma del lote.
        }

        Path archivoTotal = Path.of("total_primos.txt"); // Archivo compartido donde se acumulará el total de primos válidos.
        try (RandomAccessFile raf = new RandomAccessFile(archivoTotal.toFile(), "rw"); // Abre el archivo en lectura/escritura y lo crea si no existe.
             FileChannel canal = raf.getChannel()) { // Obtiene el canal para solicitar el cerrojo exclusivo a nivel de SO.
            FileLock cerrojo = canal.lock(); // Espera hasta obtener el bloqueo exclusivo sobre el archivo compartido.
            try {
                raf.seek(0); // Coloca el puntero al inicio del archivo para leer el valor acumulado previo.
                String textoActual = raf.readLine(); // Lee la primera línea (null si el archivo está vacío o recién creado).
                long totalAnterior = (textoActual == null || textoActual.isBlank())
                        ? 0L : Long.parseLong(textoActual.strip()); // Convierte el total previo a long o empieza en 0 si está vacío.

                long nuevoTotal = totalAnterior + sumaLote; // Calcula el nuevo total acumulado sumando lo obtenido en esta ejecución.
                raf.seek(0); // Vuelve al byte 0 antes de sobrescribir.
                raf.setLength(0); // Trunca el archivo a 0 bytes para que no queden restos de un número más largo anterior.
                raf.write(String.valueOf(nuevoTotal).getBytes(StandardCharsets.UTF_8)); // Escribe el nuevo total acumulado en UTF-8.

                System.out.println("Total acumulado guardado en total_primos.txt: " + nuevoTotal); // Muestra por consola el total actualizado.
            } finally {
                cerrojo.release(); // Libera siempre el bloqueo en el finally para evitar interbloqueos si ocurre algún error.
            }
        }
    }
}
