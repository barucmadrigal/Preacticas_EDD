
/**
 * Clase que representa un historial de páginas web,
 * almacenadas en una lista ligada simple.
 */
public class Historial {

    /**
     * Lista ligada simple que almacena las páginas del historial.
     */
    private Lista<Pagina> historial;

    /**
     * Crea un historial nuevo con la lista ligada simple proporcionada.
     *
     * @param historial la lista ligada simple de páginas inicial
     */
    public Historial(Lista<Pagina> historial) {
        this.historial = historial;
    }

    /**
     * Agrega una página al historial 
      	Si la página ya existe elimina la existente y agrega la nueva al inicio, 
      	en otro caso, simplemente la agrega al inicio.
     *
     * @param p la página a agregar
     */
    public void agregarPagina(Pagina p) {
       if (historial.buscar(p)) {
            historial.eliminar(p);
        }
        historial.agregar(p);
    }

    /**
     * Devuelve una representación en cadena del historial,
     * que corresponde a la representación en cadena
     * de la lista ligada simple de páginas.
     *
     * @return una cadena con el historial completo
     */
    @Override
    public String toString() {
       return historial.toString();
    }
}
