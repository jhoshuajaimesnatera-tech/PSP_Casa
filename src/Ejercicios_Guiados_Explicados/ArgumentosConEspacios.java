package Ejercicios_Guiados_Explicados;

public class ArgumentosConEspacios { 
	public static void main(String[] args) throws Exception {
		System.out.println("--- Versión A: un argumento por elemento");
		ProcessBuilder a = Lanzador.para("ejercicios.cuaderno0.MuestraArgs",
				"Hola mundo", "adiós"); // Envía 2 argumentos al proceso hijo: "Hola mundo" como un solo texto y "adiós" como otro.
		a.inheritIO().start().waitFor(); // Conecta la consola, arranca el hijo y espera a que termine.
		System.out.println("--- Versión B: cada palabra por separado");
		ProcessBuilder b = Lanzador.para("ejercicios.cuaderno0.MuestraArgs",
				"Hola", "mundo", "adiós"); // Envía 3 argumentos separados al proceso hijo ("Hola", "mundo" y "adiós").
		b.inheritIO().start().waitFor(); // Conecta la consola, arranca el hijo y espera a que termine.
	}
}
