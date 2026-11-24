package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class EnviarEntradaYCerrarTuberia { // Renombrado de Ej20: enviar texto por la entrada estándar del hijo, cerrar el flujo (EOF) y leer su respuesta
	public static void main(String[] args) throws Exception {
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Mayusculas").start(); // Arranca el proceso hijo Mayusculas con entrada y salida conectadas por tuberías.
		try (BufferedWriter w = hijo.outputWriter()) { // Abre un escritor conectado a la entrada estándar del hijo (su System.in) y lo cierra automáticamente al salir del try.
			w.write("hola"); // Envía la primera línea de texto al flujo de entrada del hijo.
			w.newLine(); // Escribe el separador de línea para que readLine() del hijo detecte el fin de esa primera línea.
			w.write("procesos en java"); // Envía la segunda línea de texto al flujo de entrada del hijo.
			w.newLine(); // Escribe el salto de línea correspondiente a la segunda línea.
		} // Al salir del try-with-resources se cierra el writer (y la tubería de entrada), lo que envía EOF al hijo para que salga de su bucle while.
		try (BufferedReader r = hijo.inputReader()) { // Abre un lector sobre la salida estándar del hijo (su System.out).
			r.lines().forEach(l -> System.out.println("Hijo: " + l)); // Lee todas las líneas devueltas por el hijo y las muestra prefijadas con "Hijo: ".
		}
		System.out.println("Código: " + hijo.waitFor()); // Espera a que el hijo termine por completo y muestra su código de salida.
	}
}