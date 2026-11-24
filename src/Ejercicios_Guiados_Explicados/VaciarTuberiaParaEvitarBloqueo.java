package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;

public class VaciarTuberiaParaEvitarBloqueo { // Renombrado de Ej19: consumir toda la salida del proceso hijo para vaciar el buffer de la tubería y evitar bloqueos
	public static void main(String[] args) throws Exception {
		long t0 = System.currentTimeMillis(); // Guarda el instante inicial en milisegundos para medir cuánto tarda todo el proceso.
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Cuenta", "100000").start(); // Arranca Cuenta pidiéndole 100.000 líneas con salida conectada a una tubería (pipe).
		long lineas = 0; // Guardará el número total de líneas leídas desde la salida del hijo.
		try (BufferedReader r = hijo.inputReader()) { // Abre un lector de texto sobre la tubería de salida del hijo y lo cierra automáticamente al salir del try.
			lineas = r.lines().count(); // Lee todas las líneas hasta el final, vaciando el buffer de la tubería para que el hijo no se quede bloqueado, y cuenta cuántas son.
		}
		int codigo = hijo.waitFor(); // Espera a que el proceso hijo termine por completo (una vez vaciada la tubería puede avanzar y cerrar sin atascarse) y obtiene su código de salida.
		long ms = System.currentTimeMillis() - t0; // Calcula el tiempo total transcurrido en milisegundos.
		System.out.println("Líneas: " + lineas + ", código " + codigo
				+ ", " + ms + " ms"); // Muestra el total de líneas contadas, el código de salida del hijo y el tiempo tardado.
	}
}
