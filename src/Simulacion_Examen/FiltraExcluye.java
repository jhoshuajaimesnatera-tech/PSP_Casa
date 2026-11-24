package Simulacion_Examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiltraExcluye { // Filtro auxiliar para pipeline: imprime solo las líneas de entrada que NO contengan la subcadena indicada
    public static void main(String[] args) throws IOException {
        String excluir = args.length > 0 ? args[0] : ""; // Texto prohibido; si no se pasa argumento, queda vacío para no excluir nada por error.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) { // Lee línea a línea desde la entrada estándar conectada al proceso anterior del pipeline.
            String linea; // Almacena cada línea leída de la tubería.
            while ((linea = br.readLine()) != null) { // Continúa mientras el proceso anterior siga enviando líneas y no haya cerrado su salida (EOF).
                if (!linea.contains(excluir)) { // Solo deja pasar la línea si NO contiene el texto a excluir.
                    System.out.println(linea); // Escribe la línea válida en su salida estándar para el siguiente eslabón o el padre.
                }
            }
        }
    }
}