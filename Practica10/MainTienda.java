import java.io.File;
import java.util.Scanner;

/**
 * Clase principal que demuestra el funcionamiento del sistema de inventario.
 * Lee productos desde un archivo de texto, los organiza en un árbol binario,
 * y realiza operaciones como búsqueda, filtrado por rango de precios y eliminación.
 * 
 * @author
 */
public class MainTienda {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        try (Scanner sc = new Scanner(new File("Productos.txt"))) {
            if (sc.hasNextLine()) sc.nextLine(); // Saltar encabezado
            while (sc.hasNextLine()) {
                String[] datos = sc.nextLine().split(",");
                if (datos.length >= 5) {
                    Producto p = new Producto(datos[0], Double.parseDouble(datos[1]), 
                                              datos[2], datos[3], Integer.parseInt(datos[4]));
                    inventario.agregarProducto(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        System.out.println("=== Inventario Inicial (Ordenado por Precio) ===");
        inventario.mostrarTodo();

        System.out.println("\n=== Productos entre $100 y $300 ===");
        System.out.println(inventario.obtenerRangoPrecios(100, 300));
        
        // Prueba de búsqueda y eliminación
        Producto dummy = new Producto("Apple iPhone 14", 799, "", "", 0);
        System.out.println("\n¿Existe el iPhone 14?: " + inventario.existeProducto(dummy));
        
        inventario.eliminarProducto(dummy);
        System.out.println("iPhone eliminado. ¿Sigue existiendo?: " + inventario.existeProducto(dummy));
    }
}