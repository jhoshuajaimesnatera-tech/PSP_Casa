package Ejercicios_Guiados_Explicados;

import java.util.Map;

public class ModificarEntornoHijo { // Renombrado de Ej11: cambiar variables de entorno solo para el proceso hijo sin afectar al padre
	public static void main(String[] args) throws Exception {
		ProcessBuilder pb = Lanzador.para("ejercicios.cuaderno0.LeeEntorno"); // Prepara la orden para ejecutar LeeEntorno como proceso hijo.
		Map<String, String> entorno = pb.environment(); // Obtiene una copia editable de las variables de entorno que heredará el hijo (no modifica las del padre).
		entorno.put("SALUDO", "Buenos días"); // Define o sobrescribe la variable SALUDO para el proceso hijo.
		entorno.put("MODO", "pruebas"); // Define o sobrescribe la variable MODO para el proceso hijo.
		pb.inheritIO().start().waitFor(); // Conecta la consola, arranca el hijo con esas variables y espera a que termine.
		System.out.println("Padre: mi SALUDO = " + System.getenv("SALUDO")); // Comprueba que la variable SALUDO del padre sigue intacta (normalmente null si no existía antes).
	}
}
