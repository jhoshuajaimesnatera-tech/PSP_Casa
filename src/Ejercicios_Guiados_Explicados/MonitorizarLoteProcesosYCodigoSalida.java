package Ejercicios_Guiados_Explicados;

import java.util.LinkedHashMap;
import java.util.Map;

public class MonitorizarLoteProcesosYCodigoSalida { // Renombrado de Ej29: lanzar varias tareas en paralelo, descartar su salida y revisar el código de salida de cada una para generar un resumen
	public static void main(String[] args) throws Exception {
		String[] codigos = {"0", "2", "0", "1"}; // Códigos de salida que simulará cada proceso hijo Sale (0 = éxito, distinto de 0 = error).
		Map<String, Process> tareas = new LinkedHashMap<>(); // Mapa ordenado por inserción para asociar cada nombre de tarea ("tarea-1", etc.) con su Process.
		for (int i = 0; i < codigos.length; i++) { // Lanza todos los procesos hijos de forma concurrente antes de esperar a ninguno.
			ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.Sale", codigos[i]); // Prepara la ejecución de Sale pasándole el código de salida correspondiente.
			pb.redirectOutput(ProcessBuilder.Redirect.DISCARD); // Descarta la salida estándar del hijo para no llenar el buffer ni ensuciar la consola del padre.
			tareas.put("tarea-" + (i + 1), pb.start()); // Inicia el proceso hijo y lo guarda en el mapa manteniendo el orden de lanzamiento.
		}
		int fallos = 0; // Contador de procesos hijos que terminaron con un código distinto de 0.
		for (Map.Entry<String, Process> e : tareas.entrySet()) { // Recorre las tareas en el orden en que se registraron para comprobar su resultado.
			int c = e.getValue().waitFor(); // Espera a que termine el proceso de esta tarea y obtiene su código de salida.
			if (c == 0) { // Comprueba si el proceso finalizó correctamente.
				System.out.println(e.getKey() + ": correcta"); // Informa de que la tarea terminó sin errores.
			} else {
				System.out.println(e.getKey() + ": FALLÓ con código " + c); // Informa del fallo mostrando el código devuelto por el hijo.
				fallos++; // Incrementa el contador de tareas fallidas.
			}
		}
		System.out.println("Resumen: " + (tareas.size() - fallos) + " bien, "
				+ fallos + " mal"); // Muestra el recuento total de tareas correctas y fallidas.
		System.exit(fallos == 0 ? 0 : 1); // Termina el proceso padre con código 0 si todas las tareas salieron bien, o con 1 si hubo algún fallo.
	}
}
