package Ejercicios_Guiados_Explicados;

public class Quien_Soy {

	public static void main(String[] args) throws InterruptedException {
		long pid = ProcessHandle.current().pid(); 
		// Obtiene del sistema operativo el identificador único (PID) que identifica a este proceso en ejecución
		System.out.println("Soy un proceso y mi PID es " + pid); 
		Thread.sleep(30_000); // Hace que se detenga por 30 segundos
		System.out.println("Termino");
	}
}