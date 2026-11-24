package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/** Responde a cada línea en cuanto la recibe. FIN termina. */
public class Eco {
	public static void main(String[] args) throws IOException {
		BufferedReader entrada = new BufferedReader(
				new InputStreamReader(System.in, StandardCharsets.UTF_8)); // Prepara un lector en UTF-8 con buffer sobre la entrada estándar (System.in) para leer líneas completas.
		PrintWriter salida = new PrintWriter(System.out, true,
				StandardCharsets.UTF_8); // Crea un escritor en UTF-8 sobre System.out con autoFlush activado (true), haciendo flush automático en cada println().
		String linea; // Almacena cada línea recibida por la entrada estándar.
		while ((linea = entrada.readLine()) != null) { // Lee líneas hasta que se cierre el flujo de entrada (EOF) o el programa salga con "FIN".
			if (linea.equals("FIN")) { // Comprueba si el texto recibido (sin salto de línea) es exactamente "FIN".
				salida.println("ADIOS"); // Envía la respuesta final y hace flush automático gracias a autoFlush = true.
				return; // Termina el método main y finaliza el proceso inmediatamente sin esperar a que se cierre la entrada.
			}
			salida.println("Has dicho: " + linea); // Responde al instante ecoando la línea recibida y vaciando el buffer al momento.
		}
	}
}
