package Ejercicios_Guiados_Explicados;

import java.util.concurrent.TimeUnit;

public class EsperarConTimeoutYMatar { // Renombrado de Ej14: esperar un tiempo máximo al hijo y detenerlo si tarda demasiado
	public static void main(String[] args) throws Exception {
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Duerme", "10")
				.inheritIO().start(); // Lanza Duerme pidiéndole que espere 10 segundos, conecta su consola y lo arranca sin bloquear aún al padre.
		boolean termino = hijo.waitFor(2, TimeUnit.SECONDS); // Espera como máximo 2 segundos a que termine; devuelve true si acabó a tiempo o false si sigue vivo.
		if (termino) { // Entra aquí solo si el hijo terminó antes de que pasaran los 2 segundos.
			System.out.println("Padre: terminó a tiempo"); // Confirma que el proceso acabó dentro del límite de tiempo.
		} else { // Entra aquí si pasaron los 2 segundos y el hijo seguía durmiendo (le quedaban 8 segundos).
			System.out.println("Padre: tarda demasiado, lo detengo"); // Avisa de que se superó el tiempo de espera y se va a cortar el proceso.
			hijo.destroy(); // Solicita detener/terminar el proceso hijo.
			hijo.waitFor(); // Espera a que el sistema termine de cerrar el hijo por completo tras pedir destruirlo.
			System.out.println("Padre: vivo = " + hijo.isAlive()); // Comprueba si el hijo sigue vivo (debería imprimir false tras terminar).
		}
	}
}