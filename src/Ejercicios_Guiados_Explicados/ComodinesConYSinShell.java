package Ejercicios_Guiados_Explicados;

public class ComodinesConYSinShell { // Renombrado de Ej09: diferencia entre ejecutar un comando directo sin shell y ejecutarlo con sh -c
	public static void main(String[] args) throws Exception {
		System.out.println("--- Sin intérprete de órdenes");
		ProcessBuilder a = new ProcessBuilder("ls", "*.txt"); // Ejecuta 'ls' directo pasando "*.txt" literal como argumento (sin shell que expanda comodines).
		int ca = a.inheritIO().start().waitFor(); // Conecta la consola, lanza el proceso, espera a que termine y guarda su código de salida.
		System.out.println("Código: " + ca); // Muestra el código de salida (si no existe un archivo literal llamado "*.txt", suele devolver error).
		System.out.println("--- Con sh -c");
		ProcessBuilder b = new ProcessBuilder("sh", "-c", "ls *.txt"); // Lanza una shell ('sh') para que interprete el comando completo y expanda el comodín *.txt.
		int cb = b.inheritIO().start().waitFor(); // Conecta la consola, lanza el proceso, espera a que termine y guarda su código de salida.
		System.out.println("Código: " + cb); // Muestra el código de salida tras ejecutar el comando dentro de la shell.
	}
}
