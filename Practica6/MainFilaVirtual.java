import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que gestiona la fila virtual de venta de boletos.
 * Se encarga de la lectura de datos desde un archivo externo y de aplicar
 * los algoritmos de ordenamiento cuadrático para determinar el acceso.
 */
public class MainFilaVirtual {

    /**
     * Punto de entrada del programa. Lee el archivo txt, ordena a los usuarios
     * y distribuye los boletos disponibles.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        // Definimos un límite de boletos para el ejemplo
        int boletosDisponibles = 5; 
        String nombreArchivo = "usuarios.txt";

        // Carga de datos desde el archivo TXT
        try (Scanner lector = new Scanner(new File(nombreArchivo))) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split("_");
                if (datos.length == 3) {
                    String nombre = datos[0];
                    String nivel = datos[1];
                    int llegada = Integer.parseInt(datos[2]);
                    listaUsuarios.add(new Usuario(nombre, nivel, llegada));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se pudo encontrar el archivo " + nombreArchivo);
            return;
        } catch (Exception e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
            return;
        }

        // Conversión a arreglo para procesar con algoritmos genéricos
        Usuario[] usuarios = listaUsuarios.toArray(new Usuario[0]);

        /**
         * Aplicación del algoritmo de ordenamiento.
         * Se utiliza Selection Sort debido a que minimiza la cantidad de intercambios.
         */
        OrdenamientosCuadraticos.selectionSort(usuarios, false);

        imprimirResultados(usuarios, boletosDisponibles);
    }

    /**
     * Imprime en consola la lista de usuarios que obtuvieron boleto y los que no.
     * @param usuarios Arreglo de usuarios ya ordenado por prioridad.
     * @param limite Cantidad de boletos disponibles para la venta.
     */
    private static void imprimirResultados(Usuario[] usuarios, int limite) {
        System.out.println("============================================");
        System.out.println("      RESULTADOS DE LA FILA VIRTUAL         ");
        System.out.println("============================================");

        System.out.println("\n>>> USUARIOS QUE OBTUVIERON BOLETO:");
        for (int i = 0; i < usuarios.length; i++) {
            if (i < limite) {
                System.out.println("[BOLETO #" + (i + 1) + "] " + usuarios[i]);
            }
        }

        System.out.println("\n>>> USUARIOS QUE NO ALCANZARON BOLETO:");
        if (usuarios.length <= limite) {
            System.out.println("(No hay usuarios en lista de espera)");
        } else {
            for (int i = limite; i < usuarios.length; i++) {
                System.out.println("[ESPERA] " + usuarios[i]);
            }
        }
        System.out.println("============================================");
    }
}