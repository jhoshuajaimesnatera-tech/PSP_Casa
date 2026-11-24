package Ejercicios_Guiados_Explicados;

import java.io.BufferedReader;

public class UnirSalidaYErrorDelHijo { // Renombrado de Ej23: comparar leer solo stdout (getInputStream) frente a fusionar stdout y stderr con redirectErrorStream(true)
	public static void main(String[] args) throws Exception {
		System.out.println("--- Solo getInputStream, sin unir");
		Process a = Lanzador.para("ejercicios.cuaderno0.Mixto").start(); // Arranca Mixto con stdout y stderr en tuberías separadas (por defecto redirectErrorStream es false).
		try (BufferedReader r = a.inputReader()) { // Lee únicamente desde la salida estándar (stdout) del proceso 'a'; stderr queda en otro flujo sin leer aquí.
			r.lines().forEach(l -> System.out.println("leído: " + l)); // Muestra solo las líneas que llegaron por stdout (la línea de System.err no saldrá por aquí).
		}
		a.waitFor(); // Espera a que el proceso hijo 'a' termine por completo.
		System.out.println("--- Con redirectErrorStream(true)");
		ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.Mixto"); // Prepara el ProcessBuilder para lanzar Mixto.
		pb.redirectErrorStream(true); // Redirige stderr hacia el mismo flujo que stdout, de modo que ambos se puedan leer juntos desde inputReader() / getInputStream().
		Process b = pb.start(); // Arranca el proceso hijo 'b' con los flujos de salida y error ya fusionados.
		try (BufferedReader r = b.inputReader()) { // Lee todas las líneas combinadas (tanto stdout como stderr) desde el mismo lector.
			r.lines().forEach(l -> System.out.println("leído: " + l)); // Imprime cada línea recibida, incluyendo lo escrito con System.err (aunque el orden con stdout puede variar según el buffer/SO).
		}
		b.waitFor(); // Espera a que el proceso hijo 'b' termine del todo.
	}
}
