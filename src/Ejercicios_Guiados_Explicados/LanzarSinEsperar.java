package Ejercicios_Guiados_Explicados;

public class LanzarSinEsperar {
	 public static void main(String[] args) throws Exception {
	 ProcessBuilder pb = Lanzador.para("Ejercicios_Guiados_Explicados.Saludo"); // Prepara la orden para ejecutar la clase Saludo como proceso hijo
	 pb.inheritIO(); // Hace que el hijo use la misma consola del padre para que su salida aparezca por pantalla
	 Process hijo = pb.start(); // Arranca el proceso hijo inmediatamente de forma concurrente,
	 System.out.println("Padre: he lanzado un hijo con PID " + hijo.pid());
	 System.out.println("Padre: yo termino ya"); 
	 }
	}
