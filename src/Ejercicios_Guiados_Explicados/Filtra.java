package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Deja pasar solo las líneas que contienen el texto indicado. */
public class Filtra {
	public static void main(String[] args) throws IOException {
		BufferedReader entrada = new BufferedReader(
				new InputStreamReader(System.in)); // Envuelve System.in con un buffer para leer la entrada estándar línea a línea.
		String linea; // Guarda cada línea leída desde la entrada estándar.
		while ((linea = entrada.readLine()) != null) { // Lee hasta que se cierre el flujo de entrada (EOF -> devuelve null).
			if (linea.contains(args[0])) { // Comprueba si la línea contiene la subcadena pasada como primer argumento (args[0]).
				System.out.println(linea); // Imprime la línea por la salida estándar solo si cumple el filtro.
			}
		}
	}
}
