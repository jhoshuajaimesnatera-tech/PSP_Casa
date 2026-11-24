package Ejercicios_Guiados_Explicados;

public class Saludo {
	public static void main(String[] args) {
		System.out.println("Hola, soy el hijo. Mi PID es "
				+ ProcessHandle.current().pid()); 
		// Obtiene y muestra por pantalla el identificador único (PID) propio de este proceso hijo en ejecución
	}
}