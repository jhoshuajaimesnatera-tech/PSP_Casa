package Ejercicios_Guiados_Explicados;

/** Duerme los segundos indicados y termina. */
public class Duerme {
	public static void main(String[] args) throws InterruptedException {
		int segundos = Integer.parseInt(args[0]); // Convierte el primer argumento recibido a entero para saber cuántos segundos debe pausarse el proceso.
		System.out.println("Duermo " + segundos + " s"); // Imprime un mensaje indicando que empieza la pausa.
		Thread.sleep(segundos * 1000L); // Pausa la ejecución del hilo actual durante 'segundos' (multiplicado por 1000L para pasar de segundos a milisegundos).
		System.out.println("Me despierto"); // Imprime el mensaje final cuando termina la espera.
	}
}