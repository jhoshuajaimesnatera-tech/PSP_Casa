package Simulacion_Examen;

import java.io.BufferedReader;
import java.util.List;

public class PipelineConFiltroDoble { // Encadena Cuenta -> Filtra -> FiltraExcluye con startPipeline, muestra la salida final y revisa PID y códigos de salida
    public static void main(String[] args) throws Exception {
        if (args.length != 2) { // Comprueba que se hayan pasado exactamente los dos filtros requeridos (incluir y excluir).
            System.err.println("Uso: PipelineConFiltroDoble <contiene> <noContiene>"); // Informa de cómo ejecutar el programa si faltan o sobran argumentos.
            System.exit(1); // Termina el proceso padre con código de error 1 tal como pide el enunciado.
        }

        String incluir = args[0]; // Primer filtro: la línea debe contener este texto.
        String excluir = args[1]; // Segundo filtro: la línea NO debe contener este texto.

        ProcessBuilder pbCuenta = Lanzador.para("ejercicios.cuaderno0.Cuenta", "30"); // Genera las líneas del 1 al 30 (o según la implementación de Cuenta en el cuaderno).
        ProcessBuilder pbFiltra = Lanzador.para("ejercicios.cuaderno0.Filtra", incluir); // Filtra quedándose solo con las líneas que contienen 'incluir'.
        ProcessBuilder pbExcluye = Lanzador.para("ejercicios.cuaderno0.FiltraExcluye", excluir); // Filtra descartando las líneas que contienen 'excluir'.

        List<Process> tuberia = ProcessBuilder.startPipeline(List.of(pbCuenta, pbFiltra, pbExcluye)); // Conecta stdout -> stdin en orden y arranca todos los procesos de la tubería.

        Process ultimo = tuberia.get(tuberia.size() - 1); // Obtiene el último proceso de la cadena, que es quien produce la salida ya filtrada.
        try (BufferedReader br = ultimo.inputReader()) { // Abre un lector sobre la salida estándar del último proceso.
            String linea; // Guarda cada línea final resultante de pasar por ambos filtros.
            while ((linea = br.readLine()) != null) { // Lee hasta que el último proceso termine y cierre su salida.
                System.out.println(linea); // Muestra por consola el resultado final del pipeline.
            }
        }

        boolean todosOk = true; // Bandera para saber si todos los procesos de la tubería terminaron con código 0.
        for (Process p : tuberia) { // Recorre cada proceso del pipeline para esperar su cierre y registrar su estado.
            int codigo = p.waitFor(); // Espera a que el proceso termine y recoge su código de salida.
            System.out.println("Proceso PID=" + p.pid() + " -> código " + codigo); // Muestra el PID y el código de salida de este eslabón.
            if (codigo != 0) { // Si algún proceso devuelve un código distinto de 0, marca que no todo fue correcto.
                todosOk = false; // Actualiza el estado general a falso.
            }
        }

        System.out.println("¿Todos terminaron con código 0?: " + todosOk); // Resume si toda la tubería finalizó sin errores.
    }
}
