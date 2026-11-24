package Ejercicios_Guiados_Explicados;

public class LanzarHijoYEsperar {
	
 public static void main(String[] args) throws Exception {
 System.out.println("Padre: mi PID es " + ProcessHandle.current().pid());
//Prepara la orden completa con Java y el classpath para ejecutar la clase MiPadre como proceso hijo
 ProcessBuilder pb = Lanzador.para("Ejercicios_Guiados_Explicados.MiPadre"); 
 pb.inheritIO(); // Hace que el hijo comparta la misma consola del padre para que su salida aparezca por pantalla
 pb.start().waitFor(); // Inicia el proceso hijo en paralelo y bloquea al padre hasta que el hijo termine su ejecución
 }
}
