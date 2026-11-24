package Ejercicios_Guiados_Explicados;

/** Termina con el código de salida que recibe como argumento. */
public class Sale {
	public static void main(String[] args) {
		int codigo = Integer.parseInt(args[0]); // Convierte a un número entero para usarlo como código de salida
		System.out.println("Voy a terminar con código " + codigo); 
		System.exit(codigo); // Termina la ejecución del proceso devolviendo el número como código de salida al sistema operativo
	}
}
