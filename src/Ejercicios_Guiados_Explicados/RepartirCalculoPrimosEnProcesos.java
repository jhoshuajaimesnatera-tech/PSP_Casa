package Ejercicios_Guiados_Explicados;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepartirCalculoPrimosEnProcesos { // Renombrado de Ej30: lanzar varios procesos Trabajador en paralelo redirigiendo salida y error a archivos, esperar a que acaben y sumar los resultados
	public static void main(String[] args) throws Exception {
		int[] limites = {1_000_000, 2_000_000, 3_000_000, 4_000_000}; // Límites superiores (exclusivos) para cada proceso trabajador que calculará cuántos primos son menores que n.
		Files.createDirectories(Path.of("res")); // Crea el directorio "res" (y los intermedios si hiciera falta) sin fallar si ya existe.
		long t0 = System.currentTimeMillis(); // Marca el inicio para medir el tiempo total de ejecución concurrente de todos los trabajadores.
		List<Process> hijos = new ArrayList<>(); // Almacena los procesos hijos lanzados para esperar su finalización después.
		for (int n : limites) { // Lanza un proceso Trabajador por cada límite en paralelo.
			ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.Trabajador",
					String.valueOf(n)); // Configura el proceso hijo pasándole el límite n como argumento.
			pb.redirectOutput(new File("res", "t" + n + ".txt")); // Redirige la salida estándar del hijo ("n;cuantos") a un archivo propio para evitar mezclas y bloqueos de buffer.
			pb.redirectError(new File("res", "t" + n + ".err")); // Redirige la salida de error del hijo a un archivo separado para registrar posibles fallos o trazas.
			hijos.add(pb.start()); // Inicia el proceso hijo y lo añade a la lista.
		}
		for (Process p : hijos) { // Espera a que terminen todos los procesos trabajadores antes de leer los archivos de salida.
			p.waitFor(); // Bloquea hasta que el proceso hijo 'p' finalice su cálculo y cierre su archivo de salida.
		}
		int total = 0; // Acumulador con la suma de los recuentos de primos obtenidos de todos los archivos.
		for (int n : limites) { // Lee los resultados en el orden definido por 'limites' para mostrarlos ordenados.
			String linea = Files.readString(Path.of("res", "t" + n + ".txt"))
					.strip(); // Lee el contenido del archivo de salida del trabajador y quita espacios o saltos de línea finales (formato "n;cuantos").
			String[] partes = linea.split(";"); // Separa el límite n (partes[0]) y la cantidad de primos (partes[1]).
			int primos = Integer.parseInt(partes[1]); // Convierte el recuento de primos a entero.
			System.out.println("Primos menores que " + partes[0] + ": " + primos); // Muestra el resultado parcial de este trabajador.
			total += primos; // Suma el recuento de este trabajador al total general.
		}
		System.out.println("Suma de los recuentos: " + total); // Imprime la suma total de todos los recuentos individuales.
		System.out.println("Tiempo total: "
				+ (System.currentTimeMillis() - t0) + " ms"); // Muestra el tiempo total transcurrido desde el lanzamiento hasta procesar todos los resultados.
	}
}
