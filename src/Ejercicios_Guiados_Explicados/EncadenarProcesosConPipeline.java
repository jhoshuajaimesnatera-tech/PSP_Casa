package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;
import java.util.List;

public class EncadenarProcesosConPipeline { // Renombrado de Ej24: conectar varios procesos en cadena (stdout de uno -> stdin del siguiente) con ProcessBuilder.startPipeline()
	public static void main(String[] args) throws Exception {
		ProcessBuilder cuenta = Lanzador.para("ejercicios.cuaderno0.Cuenta", "20"); // Prepara el primer proceso de la cadena para generar 20 líneas por su salida estándar.
		ProcessBuilder filtra = Lanzador.para("ejercicios.cuaderno0.Filtra", "1"); // Prepara el segundo proceso para leer por su entrada estándar y dejar pasar solo las líneas que contengan "1".
		List<Process> tuberia = ProcessBuilder.startPipeline(
				List.of(cuenta, filtra)); // Arranca ambos procesos conectando automáticamente la salida estándar de 'cuenta' con la entrada estándar de 'filtra'.
		Process ultimo = tuberia.get(tuberia.size() - 1); // Obtiene el último proceso de la tubería ('filtra'), cuya salida estándar contendrá el resultado final filtrado.
		try (BufferedReader r = ultimo.inputReader()) { // Abre un lector sobre la salida estándar del último proceso y lo cierra automáticamente al terminar.
			r.lines().forEach(System.out::println); // Lee y muestra en la consola del padre solo las líneas que superaron el filtro.
		}
		for (Process p : tuberia) { // Recorre todos los procesos que forman parte del pipeline (tanto intermedios como el último).
			System.out.println("PID " + p.pid() + " -> código " + p.waitFor()); // Espera a que cada proceso termine por completo y muestra su PID junto con su código de salida.
		}
	}
}
