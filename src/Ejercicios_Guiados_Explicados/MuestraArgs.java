package Ejercicios_Guiados_Explicados;

/** Muestra cada argumento recibido entre corchetes. */
public class MuestraArgs {
	public static void main(String[] args) {
		System.out.println("He recibido " + args.length + " argumentos:");
		for (String a : args) { // Recorre cada argumento que le han pasado al proceso al arrancarlo.
			System.out.println(" [" + a + "]"); // Imprime el argumento entre corchetes para ver exactamente qué texto llegó y si tiene espacios.
		}
	}
}
