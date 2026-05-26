/**
 * Clase que gestiona un inventario de productos.
 * Utiliza un Árbol Binario Ordenado para mantener los productos organizados por precio,
 * permitiendo operaciones eficientes de búsqueda, inserción y eliminación.
 * 
 * @author
 */
public class Inventario {
    private ArbolBinarioOrdenado<Producto> productos;

    public Inventario() {
        this.productos = new ArbolBinarioOrdenado<>();
    }

    public void agregarProducto(Producto p) {
        productos.agregar(p);
    }

    public void eliminarProducto(Producto p) {
        productos.eliminar(p);
    }

    public boolean existeProducto(Producto p) {
        return productos.buscar(p);
    }

    public ListaDoblementeLigada<Producto> obtenerRangoPrecios(double min, double max) {
        ListaDoblementeLigada<Producto> resultado = new ListaDoblementeLigada<>();
        for (Producto p : productos.devolverRecorrido()) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) {
                resultado.agregarFinal(p);
            }
        }
        return resultado;
    }

    public void mostrarTodo() {
        System.out.println(productos.devolverRecorrido().toString());
    }
}