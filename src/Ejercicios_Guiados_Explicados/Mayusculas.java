package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** Lee líneas hasta que se acaba la entrada y las repite en mayúsculas. */
public class Mayusculas {
	public static void main(String[] args) throws IOException {
		BufferedReader entrada = new BufferedReader(
				new InputStreamReader(System.in, StandardCharsets.UTF_8)); // Envuelve System.in en UTF-8 y un buffer para poder leer líneas completas con readLine().
		String linea; // Almacena cada línea leída desde la entrada estándar.
		while ((linea = entrada.readLine()) != null) { // Lee línea a línea hasta que se cierra el flujo de entrada (EOF -> devuelve null).
			System.out.println(linea.toUpperCase()); // Convierte la línea a mayúsculas y la imprime por la salida estándar.
		}
		System.out.println("(fin de la entrada)"); // Avisa de que el flujo de entrada ha llegado a su fin y el bucle ha terminado.
	}
}
