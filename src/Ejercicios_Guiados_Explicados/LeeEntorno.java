package Ejercicios_Guiados_Explicados;

public class LeeEntorno {
	public static void main(String[] args) {
		System.out.println("SALUDO = " + System.getenv("SALUDO")); // Lee la variable de entorno "SALUDO" del proceso actual y la muestra por consola (si no existe, imprime null).
		System.out.println("MODO = " + System.getenv("MODO")); // Lee la variable de entorno "MODO" del proceso actual y la muestra por consola (si no existe, imprime null).
	}
}
