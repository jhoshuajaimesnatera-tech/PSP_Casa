package Ejercicios_Guiados_Explicados;

/** Escribe por la salida normal y por la de errores. */
public class Mixto {
	public static void main(String[] args) {
		System.out.println("Esto va por la salida normal"); // Escribe una línea en la salida estándar (stdout).
		System.err.println("Esto va por la salida de errores"); // Escribe una línea en la salida de error estándar (stderr), un flujo distinto al de stdout.
		System.out.println("Otra línea normal"); // Escribe otra línea en la salida estándar (stdout).
	}
}
