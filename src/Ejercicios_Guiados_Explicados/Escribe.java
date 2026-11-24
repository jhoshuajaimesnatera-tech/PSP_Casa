package Ejercicios_Guiados_Explicados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Calcula la suma de 1 a n y la deja en resultado.txt. */
public class Escribe {
	public static void main(String[] args) throws IOException {
		long n = Long.parseLong(args[0]); // Convierte el primer argumento de línea de comandos a long para fijar el límite superior de la suma.
		long suma = 0; // Acumulador inicializado en 0; se usa long para evitar desbordamientos rápidos con valores grandes de n.
		for (long i = 1; i <= n; i++) { // Recorre todos los enteros desde 1 hasta n inclusive.
			suma += i; // Acumula el valor actual i en la suma total.
		}
		Files.writeString(Path.of("resultado.txt"), String.valueOf(suma)); // Escribe el resultado en "resultado.txt" dentro del directorio de trabajo actual del proceso (creándolo o sobrescribiéndolo).
	}
}
