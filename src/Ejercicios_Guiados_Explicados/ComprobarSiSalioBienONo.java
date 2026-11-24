package Ejercicios_Guiados_Explicados;

public class ComprobarSiSalioBienONo {
	public static void main(String[] args) throws Exception {
		for (String c : new String[] {"0", "3", "7"}) { // Bucle que recorre tres códigos distintos
			ProcessBuilder pb = Lanzador.para("eEjercicios_Guiados_Explicados.Sale", c); // Prepara la orden para ejecutar la clase Sale pasando el código correspondiente como argumento al hijo
			pb.inheritIO(); // compartir consola padre e hijo
			int codigo = pb.start().waitFor(); // Arranca el hijo mientras bloquea al padre
			String veredicto = (codigo == 0) ? "todo bien" : "algo falló"; // Evalúa si el código de salida indica éxito (0) o error (cualquier otro valor)
			System.out.println("Padre: código " + codigo + " -> " + veredicto); // Muestra el veredicto del padre según el código recibido
		}
	}
}
