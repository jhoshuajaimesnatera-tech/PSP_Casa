package Ejercicios_Guiados_Explicados;

/** Tarda mucho en terminar, pero avisa si le piden que se cierre. */
public class Terco {
	public static void main(String[] args) throws InterruptedException {
		Runtime.getRuntime().addShutdownHook(new Thread() { // Registra un hilo especial ("shutdown hook") que se ejecuta justo cuando piden cerrar o apagar este proceso.
			@Override
			public void run() {
				System.out.println("Me piden que termine: recojo y salgo"); // Avisa y hace limpieza rápida antes de que el proceso se cierre del todo.
			}
		});
		System.out.println("Trabajando..."); // Indica que el proceso está en marcha.
		Thread.sleep(60_000); // Pausa el hilo principal durante 60.000 milisegundos (60 segundos), simulando una tarea larga.
	}
}
