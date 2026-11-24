package Ejercicios_Guiados_Explicados;

public class DestruirSuaveVsForzoso { // Renombrado de Ej15: comparar cierre suave (destroy) con cierre forzoso (destroyForcibly) sobre un proceso con shutdown hook
	public static void main(String[] args) throws Exception {
		System.out.println("--- destroy()");
		Process a = Lanzador.para("ejercicios.cuaderno0.Terco").inheritIO().start(); // Arranca el proceso Terco y conecta su consola para ver lo que imprime.
		Thread.sleep(1500); // Pausa 1,5 segundos para darle tiempo a arrancar y registrar su shutdown hook.
		a.destroy(); // Pide cerrar el proceso de forma ordenada/suave (en sistemas Unix envía SIGTERM, por lo que suele activar el shutdown hook de Terco).
		System.out.println("Código: " + a.waitFor()); // Espera a que termine el proceso 'a' y muestra su código de salida.
		System.out.println("--- destroyForcibly()");
		Process b = Lanzador.para("ejercicios.cuaderno0.Terco").inheritIO().start(); // Arranca una segunda instancia de Terco y conecta su consola.
		Thread.sleep(1500); // Espera 1,5 segundos a que arranque.
		b.destroyForcibly(); // Fuerza el cierre inmediato del proceso (en sistemas Unix envía SIGKILL, matándolo sin darle margen a ejecutar el shutdown hook).
		System.out.println("Código: " + b.waitFor()); // Espera a que termine el proceso 'b' y muestra su código de salida.
	}
}
