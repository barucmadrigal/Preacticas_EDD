/**
 * Clase que representa una página web con su nombre y la última fecha de visita.
 */
public class Pagina {

    /** Nombre de la página (URL o identificador). */
    private String nombre;

    /** Última fecha en que se visitó la página, en formato "dd/mm/aaaa". */
    private String ultimaFecha;

    /**
     * Crea una nueva instancia de Pagina con el nombre y la fecha proporcionados.
     *
     * @param nombre      el nombre o URL de la página
     * @param ultimaFecha la última fecha de visita en formato "dd/mm/aaaa"
     */
    public Pagina(String nombre, String ultimaFecha) {
        this.nombre = nombre;
        this.ultimaFecha = ultimaFecha;
    }

    /**
     * Devuelve la última fecha en que se visitó la página.
     *
     * @return la fecha en formato "dd/mm/aaaa"
     */
    public String getFecha() {
        return this.ultimaFecha;
    }

    /**
     * Devuelve el nombre de la página.
     *
     * @return el nombre o URL de la página
     */
    public String getNombre() {
       return this.nombre; 
    }

    /**
     * Compara esta página con otra por nombre (ignora la fecha).
     * @param p la página con la que se compara
     * @return true si tienen el mismo nombre
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Pagina pagina = (Pagina) o;
        return this.nombre.equals(pagina.nombre);
    }

    /**
     * Devuelve una representación en cadena de la página,
     * con el formato "nombre : fecha".
     *
     * @return una cadena con el nombre y la fecha de la página
     */
    @Override
    public String toString() {
        return nombre + " : " + ultimaFecha;
    }
}