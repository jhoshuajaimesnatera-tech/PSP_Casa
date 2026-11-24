package Ejercicios_Guiados_Explicados;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;

/** Como SumaUno, pero con un bloqueo de fichero en cada incremento. */
public class SumaUnoBloqueo {
	public static void main(String[] args) throws IOException {
		int veces = Integer.parseInt(args[0]); // Número de veces que este proceso incrementará el contador de forma segura.
		try (RandomAccessFile f = new RandomAccessFile("contador.txt", "rw"); // Abre el archivo en modo lectura/escritura ("rw") y lo cierra automáticamente al salir del try.
				FileChannel canal = f.getChannel()) { // Obtiene el canal del archivo para poder solicitar bloqueos (FileLock) a nivel de sistema operativo.
			for (int i = 0; i < veces; i++) { // Repite el ciclo de lectura-incremento-escritura protegendo cada iteración.
				FileLock cerrojo = canal.lock(); // Solicita un bloqueo exclusivo sobre el archivo y espera (bloquea) hasta que otro proceso lo libere.
				try {
					f.seek(0); // Vuelve al inicio del archivo para leer desde el byte 0.
					String texto = f.readLine(); // Lee la primera línea del archivo (devuelve null si está vacío).
					int valor = (texto == null || texto.isBlank())
							? 0 : Integer.parseInt(texto.strip()); // Convierte el contenido a entero, o usa 0 si está vacío o en blanco.
					f.setLength(0); // Trunca el archivo a 0 bytes para evitar que queden restos si el nuevo número tiene menos dígitos.
					f.write(String.valueOf(valor + 1)
							.getBytes(StandardCharsets.UTF_8)); // Escribe el nuevo valor incrementado codificado en UTF-8.
				} finally {
					cerrojo.release(); // Libera siempre el bloqueo para que el siguiente proceso pueda entrar a la sección crítica.
				}
			}
		}
	}
}