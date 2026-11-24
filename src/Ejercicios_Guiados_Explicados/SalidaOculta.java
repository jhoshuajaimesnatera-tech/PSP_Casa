package Ejercicios_Guiados_Explicados;


public class SalidaOculta {
	public static void main(String[] args) throws Exception {
		ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.Saludo"); 
		// Prepara la orden para ejecutar la clase Saludo como proceso hijo
		// sin inheritIO()
		int codigo = pb.start().waitFor(); 
		// Arranca el hijo y bloquea al padre hasta que termina; al no usar inheritIO(), 
		// la salida del hijo va a una tubería por defecto que nadie lee y se pierde
		System.out.println("Padre: el hijo terminó con código " + codigo);
	}
}
