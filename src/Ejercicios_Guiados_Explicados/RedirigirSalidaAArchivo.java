package Ejercicios_Guiados_Explicados;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class RedirigirSalidaAArchivo { // Renombrado de Ej12: guardar la salida del proceso hijo en un archivo y leerla desde el padre
	public static void main(String[] args) throws Exception {
		ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.Cuenta", "5"); // Prepara la orden para lanzar Cuenta pidiéndole que imprima 5 líneas.
		pb.redirectOutput(new File("salida.txt")); // Redirige lo que el hijo imprima por consola hacia el archivo "salida.txt" en vez de mostrarlo en pantalla.
		int codigo = pb.start().waitFor(); // Arranca el hijo y bloquea al padre hasta que termina, guardando su código de salida.
		System.out.println("Padre: el hijo terminó con código " + codigo); // Muestra en la consola del padre que el hijo acabó y con qué código.
		System.out.println("Padre: contenido de salida.txt:");
		for (String linea : Files.readAllLines(Path.of("salida.txt"))) { // Lee todas las líneas guardadas en "salida.txt" una por una.
			System.out.println(" " + linea); // Imprime cada línea leída desde el archivo en la consola del padre.
		}
	}
}
