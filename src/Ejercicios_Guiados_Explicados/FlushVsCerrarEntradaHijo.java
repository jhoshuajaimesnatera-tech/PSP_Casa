package Ejercicios_Guiados_Explicados;

import java.io.BufferedWriter;
import java.util.concurrent.TimeUnit;

public class FlushVsCerrarEntradaHijo { // Renombrado de Ej21: comprobar que flush() envía la línea pero no cierra la entrada (EOF), por lo que el hijo sigue esperando más líneas
	public static void main(String[] args) throws Exception {
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Mayusculas")
				.redirectOutput(ProcessBuilder.Redirect.INHERIT)
				.start(); // Arranca Mayusculas heredando la salida estándar de la consola para ver su respuesta al instante y deja su entrada conectada a una tubería.
		BufferedWriter w = hijo.outputWriter(); // Abre el escritor hacia la entrada estándar del hijo (su System.in).
		w.write("hola"); // Escribe "hola" en el buffer del writer.
		w.newLine(); // Añade el salto de línea necesario para que readLine() del hijo pueda leer la línea.
		w.flush(); // Fuerza el envío inmediato de los bytes hacia la tubería del hijo, pero mantiene el flujo abierto (sin EOF).
		boolean termino = hijo.waitFor(3, TimeUnit.SECONDS); // Espera hasta 3 segundos; como el flujo sigue abierto, Mayusculas imprime "HOLA" y sigue esperando otra línea (devolverá false).
		System.out.println("Padre: ¿terminó? " + termino); // Muestra si el hijo terminó tras esos 3 segundos (normalmente false).
		w.close(); // Cierra la tubería de entrada del hijo, enviando EOF para que readLine() devuelva null y el hijo salga de su bucle.
		System.out.println("Padre: tras cerrar, código " + hijo.waitFor()); // Espera a que el hijo termine tras recibir EOF y muestra su código de salida.
	}
}
