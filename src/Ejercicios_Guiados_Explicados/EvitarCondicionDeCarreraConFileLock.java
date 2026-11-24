package Ejercicios_Guiados_Explicados;

import java.nio.file.Files;
import java.nio.file.Path;

public class EvitarCondicionDeCarreraConFileLock { // Renombrado de Ej27: ejecutar dos procesos SumaUnoBloqueo a la vez sobre el mismo archivo y comprobar que ahora sí se conservan los 2000 incrementos
	public static void main(String[] args) throws Exception {
		Path contador = Path.of("contador.txt"); // Ruta del archivo compartido que actúa como contador entre procesos.
		for (int intento = 1; intento <= 3; intento++) { // Repite la prueba 3 veces para verificar la consistencia del bloqueo en distintas ejecuciones.
			Files.writeString(contador, "0"); // Reinicia el contador a "0" antes de lanzar ambos procesos hijos.
			Process a = Lanzador.para("ejercicios.cuaderno0.SumaUnoBloqueo", "1000")
					.start(); // Arranca el primer proceso hijo que incrementará el contador 1.000 veces usando FileLock.
			Process b = Lanzador.para("ejercicios.cuaderno0.SumaUnoBloqueo", "1000")
					.start(); // Arranca el segundo proceso hijo en paralelo sobre el mismo archivo, coordinándose mediante el cerrojo del SO.
			a.waitFor(); // Espera a que termine por completo el primer proceso hijo.
			b.waitFor(); // Espera a que termine por completo el segundo proceso hijo.
			String fin = Files.readString(contador).strip(); // Lee el valor final del contador una vez que ambos procesos han terminado.
			System.out.println("Intento " + intento
					+ ": esperaba 2000, hay " + fin); // Comprueba el resultado: con el bloqueo exclusivo por iteración, debería mostrar 2000 en todos los intentos.
		}
	}
}