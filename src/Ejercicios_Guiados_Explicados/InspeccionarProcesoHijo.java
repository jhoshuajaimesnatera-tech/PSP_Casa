package Ejercicios_Guiados_Explicados;

import java.time.Instant;

public class InspeccionarProcesoHijo { // Renombrado de Ej16: consultar datos del proceso hijo (PID, estado, comando, usuario, inicio y código de salida)
	public static void main(String[] args) throws Exception {
		Process hijo = Lanzador.para("ejercicios.cuaderno0.Duerme", "3").start(); // Arranca el proceso hijo Duerme (3 segundos) sin bloquear al padre.
		ProcessHandle.Info info = hijo.info(); // Obtiene un objeto con información del sistema sobre el proceso hijo.
		System.out.println("PID: " + hijo.pid()); // Muestra el identificador único del proceso (PID) asignado por el sistema operativo.
		System.out.println("¿Vivo? " + hijo.isAlive()); // Comprueba si el proceso sigue en ejecución en este momento (debería ser true).
		System.out.println("Orden: " + info.command().orElse("?")); // Muestra la ruta/comando ejecutable del proceso, o "?" si el sistema no lo facilita.
		System.out.println("Usuario: " + info.user().orElse("?")); // Muestra el usuario del sistema que lanzó el proceso, o "?" si no está disponible.
		System.out.println("Inicio: " + info.startInstant().orElse(null)); // Muestra la fecha y hora exacta en que arrancó el proceso, o null si no se puede leer.
		hijo.waitFor(); // Espera a que el proceso hijo termine sus 3 segundos de ejecución.
		System.out.println("¿Vivo? " + hijo.isAlive()); // Vuelve a comprobar si sigue vivo tras terminar (debería ser false).
		System.out.println("Código: " + hijo.exitValue()); // Muestra el código de salida del proceso (solo se debe pedir cuando ya ha terminado).
	}
}