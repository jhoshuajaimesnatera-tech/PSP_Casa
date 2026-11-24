package Ejercicios_Guiados_Explicados;

import java.nio.charset.StandardCharsets;

public class CapturarSalidaDeHijo { // Renombrado de Ej13: leer lo que imprime el proceso hijo desde el padre usando getInputStream()
	public static void main(String[] args) throws Exception {
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Saludo").start(); // Arranca el proceso hijo Saludo y devuelve su objeto Process para comunicarnos con él.
		byte[] bytes = hijo.getInputStream().readAllBytes(); // Lee toda la salida estándar del hijo (lo que él hace con System.out) hasta que termina.
		String texto = new String(bytes, StandardCharsets.UTF_8).strip(); // Convierte los bytes recibidos a texto UTF-8 y quita espacios o saltos de línea sobrantes al principio y final.
		int codigo = hijo.waitFor(); // Espera a que el proceso hijo termine por completo y obtiene su código de salida.
		System.out.println("Padre: el hijo dijo «" + texto + "»"); // Muestra en la consola del padre el texto que envió el hijo.
		System.out.println("Padre: y terminó con código " + codigo); // Muestra el código de salida final del hijo.
	}
}