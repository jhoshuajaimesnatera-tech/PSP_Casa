package Ejercicios_Guiados_Explicados;

import java.util.concurrent.TimeUnit;

public class BloqueoPorBufferTuberiaSinLeer { // Renombrado de Ej18: demostrar cómo un hijo puede bloquearse si nadie consume el buffer de su salida estándar
	public static void main(String[] args) throws Exception {
		for (int n : new int[] {100, 100_000}) { // Prueba el mismo proceso primero con 100 líneas y luego con 100.000 líneas.
			Process hijo = Lanzador.para("ejercicios.cuaderno0.Cuenta",
					String.valueOf(n)).start(); // Arranca Cuenta con salida por tubería (pipe) sin heredar consola ni leer su salida desde el padre.
			boolean termino = hijo.waitFor(5, TimeUnit.SECONDS); // Espera hasta 5 segundos; con 100.000 líneas el buffer del SO puede llenarse y bloquear al hijo antes de acabar.
			System.out.println(n + " líneas -> ¿terminó? " + termino); // Indica si el proceso logró terminar dentro de los 5 segundos o si se quedó bloqueado/tardó más.
			if (!termino) { // Si no terminó a tiempo (por ejemplo, bloqueado esperando que se vacíe el buffer de salida).
				hijo.destroyForcibly(); // Mata el proceso hijo a la fuerza para no dejarlo colgado.
			}
		}
	}
}
