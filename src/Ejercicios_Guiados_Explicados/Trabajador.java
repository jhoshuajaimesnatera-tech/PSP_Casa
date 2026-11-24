package Ejercicios_Guiados_Explicados;

/** Cuenta los números primos menores que n y escribe "n;cuantos". */
public class Trabajador {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); // Convierte el primer argumento de línea de comandos a int para establecer el límite superior exclusivo (menores que n).
		int cuantos = 0; // Contador de números primos encontrados en el rango [2, n).
		for (int i = 2; i < n; i++) { // Recorre todos los enteros a partir de 2 hasta n - 1 (0 y 1 no son primos).
			if (esPrimo(i)) { // Comprueba si el candidato i es un número primo.
				cuantos++; // Incrementa el contador cuando i resulta ser primo.
			}
		}
		System.out.println(n + ";" + cuantos); // Imprime en la salida estándar el límite n y el total de primos menores que n separados por punto y coma.
	}

	static boolean esPrimo(int x) {
		for (int d = 2; (long) d * d <= x; d++) { // Prueba divisores desde 2 hasta la raíz cuadrada de x; se usa long en d * d para evitar desbordamiento con int grandes.
			if (x % d == 0) { // Si x es divisible exactamente por d, tiene un divisor distinto de 1 y de sí mismo.
				return false; // Descarta x inmediatamente como no primo.
			}
		}
		return true; // Si no se encontró ningún divisor hasta raíz de x, x es primo.
	}
}
