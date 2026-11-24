package Ejercicios_Guiados_Explicados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SumaUno {
	public static void main(String[] args) throws IOException {
		int veces = Integer.parseInt(args[0]); // Número de veces que este proceso incrementará el contador en el archivo.
		Path fichero = Path.of("contador.txt"); // Ruta relativa a "contador.txt" en el directorio de trabajo del proceso.
		for (int i = 0; i < veces; i++) { // Repite el ciclo de lectura-incremento-escritura el número de veces indicado.
			String texto = Files.readString(fichero).strip(); // Lee todo el contenido del archivo y elimina espacios/saltos de línea sobrantes (ojo: falla con NoSuchFileException si no existe).
			int valor = texto.isEmpty() ? 0 : Integer.parseInt(texto); // Convierte el texto a entero, o usa 0 si el archivo está vacío.
			Files.writeString(fichero, String.valueOf(valor + 1)); // Sobrescribe el archivo con el valor incrementado (sin sincronización: si corren varios procesos a la vez hay condición de carrera).
		}
	}
}
