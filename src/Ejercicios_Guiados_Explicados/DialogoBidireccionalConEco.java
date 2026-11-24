package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class DialogoBidireccionalConEco { // Renombrado de Ej22: enviar mensajes de uno en uno al hijo y leer su respuesta inmediata antes de enviar el siguiente
	public static void main(String[] args) throws Exception {
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Eco").start(); // Arranca el proceso hijo Eco con tuberías separadas para entrada (stdin) y salida (stdout).
		try (PrintWriter aHijo = new PrintWriter(hijo.outputWriter(), true);
				BufferedReader deHijo = hijo.inputReader()) { // Abre un escritor con autoFlush hacia la entrada del hijo y un lector hacia su salida; ambos se cierran solos al salir del try.
			for (String msg : new String[] {"uno", "dos", "FIN"}) { // Recorre la secuencia de mensajes de prueba en orden.
				aHijo.println(msg); // Envía la línea con salto de línea y hace flush inmediato gracias a autoFlush = true para que Eco la reciba sin esperar a llenar el buffer.
				String respuesta = deHijo.readLine(); // Bloquea hasta recibir la línea de respuesta de Eco antes de pasar al siguiente mensaje (evitando así enviar todo de golpe sin sincronizar).
				System.out.println(msg + " -> " + respuesta); // Muestra en la consola del padre el mensaje enviado y la respuesta devuelta por el hijo.
			}
		} // Al salir del try se cierran deHijo y aHijo; en este caso Eco ya habría terminado al procesar "FIN".
		System.out.println("Código: " + hijo.waitFor()); // Espera a que el proceso hijo termine por completo y muestra su código de salida.
	}
}
