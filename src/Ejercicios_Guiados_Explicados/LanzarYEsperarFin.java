package Ejercicios_Guiados_Explicados;

public class LanzarYEsperarFin {
 public static void main(String[] args) throws Exception {
 ProcessBuilder pb = Lanzador.para("Ejercicios_Guiados_Explicados.Saludo"); 
 pb.inheritIO(); // Permite compartir la pantalla padre e hijo
 Process hijo = pb.start(); // Arranca el proceso 
 System.out.println("Padre: espero a que termine el hijo..."); 
 int codigo = hijo.waitFor(); // Detiene y bloquea al proceso padre hasta que el hijo finaliza por completo, recuperando su código de salida
 System.out.println("Padre: el hijo ha terminado con código " + codigo);
 }
}
