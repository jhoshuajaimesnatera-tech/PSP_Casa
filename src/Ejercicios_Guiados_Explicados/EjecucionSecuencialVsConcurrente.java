package Ejercicios_Guiados_Explicados;

import java.util.ArrayList;
import java.util.List;

public class EjecucionSecuencialVsConcurrente { // Renombrado de Ej28: comparar el tiempo total al ejecutar tres procesos hijos uno tras otro frente a lanzarlos todos antes de esperar
	public static void main(String[] args) throws Exception {
		long t0 = System.currentTimeMillis(); // Marca el instante de inicio para medir la ejecución secuencial (~6 s si cada hijo duerme 2 s).
		for (int i = 0; i < 3; i++) { // Ejecuta los procesos de uno en uno: no lanza el siguiente hasta que termina el actual.
			Lanzador.para("ejercicios.cuaderno0.Duerme", "2").start().waitFor(); // Arranca Duerme con 2 segundos y bloquea hasta que finalice antes de continuar el bucle.
		}
		System.out.println("Secuencial: "
				+ (System.currentTimeMillis() - t0) + " ms"); // Muestra el tiempo total transcurrido ejecutando las tareas de forma secuencial.
		t0 = System.currentTimeMillis(); // Reinicia el cronómetro para medir ahora la ejecución concurrente (~2 s en paralelo).
		List<Process> hijos = new ArrayList<>(); // Guarda las referencias a todos los procesos hijos lanzados para esperarlos después.
		for (int i = 0; i < 3; i++) { // Lanza los tres procesos hijos sin esperar a que termine cada uno antes de iniciar el siguiente.
			hijos.add(Lanzador.para("ejercicios.cuaderno0.Duerme", "2").start()); // Inicia el proceso hijo y lo añade a la lista de procesos activos.
		}
		for (Process p : hijos) { // Espera a que terminen todos los procesos hijos ya en marcha (el tiempo total depende del más lento).
			p.waitFor(); // Bloquea hasta que el proceso 'p' actual finalice; los demás ya llevan corriendo en paralelo.
		}
		System.out.println("Concurrente: "
				+ (System.currentTimeMillis() - t0) + " ms"); // Muestra el tiempo total transcurrido ejecutando los tres procesos de forma concurrente.
	}
}
