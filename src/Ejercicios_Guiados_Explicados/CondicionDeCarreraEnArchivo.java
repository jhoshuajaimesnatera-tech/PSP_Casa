package Ejercicios_Guiados_Explicados;

import java.nio.file.Files;
import java.nio.file.Path;

public class CondicionDeCarreraEnArchivo { // Renombrado de Ej26: ejecutar dos procesos SumaUno a la vez sobre el mismo archivo para evidenciar la condición de carrera (se pierden incrementos)
	public static void main(String[] args) throws Exception {
		Path contador = Path.of("contador.txt"); // Ruta del archivo compartido que actuarán como contador entre procesos.
		for (int intento = 1; intento <= 3; intento++) { // Repite la prueba 3 veces para observar distintos resultados según el planificador del SO.
			Files.writeString(contador, "0"); // Reinicia el contador a "0" antes de lanzar ambos procesos hijos (y crea el archivo si no existía).
			Process a = Lanzador.para("ejercicios.cuaderno0.SumaUno", "1000").start(); // Arranca el primer proceso hijo que incrementará el contador 1.000 veces.
			Process b = Lanzador.para("ejercicios.cuaderno0.SumaUno", "1000").start(); // Arranca el segundo proceso hijo en paralelo sobre el mismo archivo.
			a.waitFor(); // Espera a que termine el primer proceso hijo.
			b.waitFor(); // Espera a que termine el segundo proceso hijo.
			String fin = Files.readString(contador).strip(); // Lee el valor final almacenado en "contador.txt" tras ambos incrementos concurrentes.
			System.out.println("Intento " + intento
					+ ": esperaba 2000, hay " + fin); // Compara el valor esperado (1000 + 1000 = 2000) con el valor real obtenido (suele ser menor por lecturas/escrituras solapadas).
		}
	}
}
