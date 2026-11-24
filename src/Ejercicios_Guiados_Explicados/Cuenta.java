package Ejercicios_Guiados_Explicados;

/** Escribe tantas líneas numeradas como se le indique. */
public class Cuenta {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); // Convierte el primer argumento recibido por consola (texto) a un número entero para saber cuántas líneas imprimir.
		for (int i = 1; i <= n; i++) { // Recorre desde la línea 1 hasta la línea n.
			System.out.println("Línea " + i); // Imprime cada línea numerada por la salida estándar.
		}
	}
}
