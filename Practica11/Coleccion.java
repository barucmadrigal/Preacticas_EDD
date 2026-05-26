/**
 * Interfaz Coleccion
 *
 * Interfaz que define los métodos que debe implementar una colección
 * genérica.
 */
public interface Coleccion<T> extends Iterable<T>{

    /**
     * Método que inserta un elemento en la colección.
     * @param elemento
     */
    public void agregar(T elemento);

    /**
     * Método que elimina un elemento de la lista.
     * @param elemento
     */
    public void eliminar(T elemento);

    /**
     * Método que busca un elemento en la colección.
     * @param elemento
     * @return Boolean que indica si se ha encontrado el elemento.
     */
    public boolean buscar(T elemento);

}