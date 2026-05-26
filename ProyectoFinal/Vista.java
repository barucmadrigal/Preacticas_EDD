import Colecciones.Listas.*;
import java.util.Scanner;

public class Vista {
    private Scanner entrada;

    public Vista() {
        this.entrada = new Scanner(System.in);
    }

    /**
     * Despliega las opciones disponibles en la consola.
     */
    public void mostrarMenu() {
        System.out.println("\n=== SIMULADOR DEL METRO CDMX ===");
        System.out.println("1. Mostrar ruta corta por número de estaciones");
        System.out.println("2. Mostrar ruta rápida por tiempo");
        System.out.println("3. Ver estaciones y tramos fuera de servicio");
        System.out.println("4. Ver lista de estaciones operativas");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }

    /**
     * Lee un número entero desde la consola.
     */
    public int leerOpcion() {
        try {
            return Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida si no introduce un número
        }
    }

    /**
     * Solicita al usuario el nombre de una estación.
     */
    public String pedirEstacion(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }

    /**
     * Imprime de forma estética el camino de estaciones.
     */
    public void mostrarRuta(ListaDoblementeLigada<String> ruta) {
        if (ruta == null || ruta.devolverLongitud() == 0) {
            System.out.println("No se pudo calcular la ruta con las condiciones actuales.");
            return;
        }

        System.out.println("\n RUTA ENCONTRADA:");
        // Nota: Como la ruta viene invertida desde el modelo, 
        // la recorremos o mostramos según el orden requerido
        for (String estacion : ruta) {
            System.out.print("[" + estacion + "] ");
        }
        System.out.println("\n¡Buen viaje!");
    } 

    public void mostrarEstacionesOperativas(ListaDoblementeLigada<String> estaciones) {
        System.out.println("\n ESTACIONES OPERATIVAS:");
        for (String estacion : estaciones) {
            System.out.println("- " + estacion);
        }
    }
}