package Ejercicios_Guiados_Explicados;

import java.nio.file.Files;
import java.nio.file.Path;

public class ComunicarPorArchivoConControlDeError { // Renombrado de Ej25: limpiar archivo previo, ejecutar el hijo comprobando su código de salida y leer el resultado generado en disco
	public static void main(String[] args) throws Exception {
		Files.deleteIfExists(Path.of("resultado.txt")); // Borra "resultado.txt" si ya existía para evitar leer datos viejos si el hijo falla o no llega a escribir.
		int codigo = Lanzador.para("ejercicios.cuaderno0.Escribe", "100")
				.inheritIO().start().waitFor(); // Arranca Escribe con n = 100 heredando E/S del padre y espera a que termine para obtener su código de salida.
		if (codigo != 0) { // Comprueba si el proceso hijo terminó con error (cualquier código distinto de 0).
			System.out.println("El hijo falló; no leo nada"); // Avisa del fallo y aborta la lectura para no procesar un archivo inexistente o corrupto.
			return; // Finaliza el programa padre antes de intentar leer o parsear "resultado.txt".
		}
		long suma = Long.parseLong(Files.readString(Path.of("resultado.txt"))); // Lee el contenido completo de "resultado.txt" y lo convierte a long.
		System.out.println("Padre: el hijo calculó " + suma); // Muestra el valor calculado y guardado por el proceso hijo.
		System.out.println("Padre: el doble es " + suma * 2); // Realiza un cálculo adicional en el padre a partir del dato recibido por archivo.
	}
}