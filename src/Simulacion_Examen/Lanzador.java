package Simulacion_Examen;

import java.nio.file.Path; // Importa la clase Path para manejar rutas de archivos y directorios de forma multiplataforma[cite: 7].
import java.util.ArrayList;
import java.util.List; 

public final class Lanzador { 
	private Lanzador() { 
	}
	public static ProcessBuilder para(String clase, String... argumentos) { 
		String java = Path.of(System.getProperty("java.home"), "bin", "java").toString(); 
		// Obtiene la ruta absoluta del ejecutable de Java basándose en el JDK que ejecuta al padre para asegurar la misma versión[cite: 8].
		String cp = Path.of(System.getProperty("java.class.path")).toAbsolutePath().toString();
		// Obtiene y convierte en absoluta la ruta del classpath (carpeta bin) para que el hijo la encuentre aunque cambie de directorio
		List<String> orden = new ArrayList<>(); 
		orden.add(java); // Añade la ruta del ejecutable de Java como primer elemento de la orden
		orden.add("-cp"); // Añade el indicador de classpath a la lista de argumentos
		orden.add(cp); // Añade la ruta absoluta del classpath recopilada previamente
		orden.add(clase); // Añade el nombre completo de la clase principal que se quiere ejecutar con su paquete[
		orden.addAll(List.of(argumentos)); // Añade todos los argumentos adicionales que se hayan pasado de forma variable al método
		return new ProcessBuilder(orden); // Construye y devuelve un nuevo objeto ProcessBuilder con la lista completa de comandos lista para ser ejecutada con start()[cite: 8].
	}
}