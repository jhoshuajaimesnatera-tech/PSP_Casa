package Ejercicios_Guiados_Explicados;

import java.io.File;

public class CambiarDirectorioTrabajo { 
	public static void main(String[] args) throws Exception {
		System.out.println("--- Sin directory()");
		Lanzador.para("ejercicios.cuaderno0.DondeEstoy").inheritIO().start().waitFor(); // Lanza DondeEstoy usando el directorio de trabajo actual del padre, conecta consola, arranca y espera.
		System.out.println("--- Con directory(new File(\"datos\"))");
		ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.DondeEstoy").directory(new File("datos")); // Prepara el proceso hijo indicando que su carpeta de trabajo será "datos" (se corrige el ';' sobrante que había antes del punto).
		pb.inheritIO().start().waitFor(); // Conecta consola, arranca el hijo dentro de "datos" y espera a que termine.
	}
}
