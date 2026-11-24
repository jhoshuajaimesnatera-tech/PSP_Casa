package Ejercicios_Guiados_Explicados;

import java.nio.file.Files;
import java.nio.file.Path;

public class DondeEstoy {
	public static void main(String[] args) {
		System.out.println("Mi directorio de trabajo: "
				+ System.getProperty("user.dir")); // Muestra la carpeta actual (directorio de trabajo) desde la que está corriendo este proceso.
		System.out.println("¿Veo datos.txt? "
				+ Files.exists(Path.of("datos.txt"))); // Comprueba si existe el archivo "datos.txt" dentro de esa carpeta actual y muestra true o false.
	}
}
