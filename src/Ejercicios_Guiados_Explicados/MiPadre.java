package Ejercicios_Guiados_Explicados;

import java.util.Optional;

public class MiPadre {

	public static void main(String[] args) {
		ProcessHandle yo = ProcessHandle.current(); // Obtiene el objeto que representa al proceso actual en ejecución
		System.out.println("Mi PID: " + yo.pid());
		// Consulta al sistema el proceso padre (PPID), devolviendo un Optional por si ya ha terminado o el sistema no permite verlo
		Optional<ProcessHandle> padre = yo.parent(); 
		if (padre.isPresent()) { // Comprueba si el proceso padre existe, sigue vivo y es accesible
			ProcessHandle p = padre.get();
			// Obtiene el identificador del padre, mostrando la relación jerárquica en el árbol de procesos
			System.out.println("PID de padre: " + p.pid()); 
			System.out.println("Orden padre: "
					+ p.info().command().orElse("(desconocida)")); 
			// Consulta los metadatos del bloque de control del sistema para ver la orden o ejecutable que lanzó al padre (como bash o java)
		} else {
			System.out.println("No puedo ver a mi padre"); 
			// Caso manejado por el Optional si el padre ya finalizó o el sistema operativo restringe el acceso
		}
	}
}
