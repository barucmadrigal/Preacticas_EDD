/**
 * Clase que representa un producto en el sistema.
 * Cada producto contiene información sobre su nombre, precio, categoría, marca y existencia.
 * Los productos se comparan por precio para su ordenamiento en estructuras como árboles.
 * 
 * @author
 */
public class Producto implements Comparable<Producto> {
    private String nombre;
    private double precio;
    private String categoria;
    private String marca;
    private int existencia;

    public Producto(String nombre, double precio, String categoria, String marca, int existencia) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
        this.existencia = existencia;
    }

    public double getPrecio() { return precio; }
    public String getNombre() { return nombre; }

    // ¡ESTE ES EL MÉTODO QUE TE MARCABA ERROR POR FALTAR!
    @Override
    public int compareTo(Producto otro) {
        return Double.compare(this.precio, otro.precio);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Producto)) return false;
        Producto p = (Producto) obj;
        return this.nombre.equalsIgnoreCase(p.nombre);
    }

    @Override
    public String toString() {
        return String.format("%s ($%.2f) - %s", nombre, precio, marca);
    }
}