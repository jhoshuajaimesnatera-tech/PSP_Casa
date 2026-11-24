package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;

public class LeerSalidaLineaPorLinea { // Renombrado de Ej17: leer lo que imprime el proceso hijo línea a línea mientras se ejecuta
	public static void main(String[] args) throws Exception {
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Cuenta", "4").start(); // Arranca el proceso hijo Cuenta pidiéndole que imprima 4 líneas.
		int n = 0; // Contador para numerar cada línea que recibe el padre.
		try (BufferedReader r = hijo.inputReader()) { // Abre un lector de texto conectado a la salida estándar del hijo y lo cierra automáticamente al terminar el bloque.
			String linea; // Guardará cada línea de texto leída.
			while ((linea = r.readLine()) != null) { // Lee una línea cada vez; devuelve null cuando el hijo cierra su salida (al terminar).
				n++; // Suma 1 al contador de líneas recibidas.
				System.out.println("Padre recibe (" + n + "): " + linea); // Muestra en la consola del padre el número de línea y el texto enviado por el hijo.
			}
		}
		System.out.println("Código: " + hijo.waitFor()); // Espera a que el proceso hijo termine del todo y muestra su código de salida.
	}
}
