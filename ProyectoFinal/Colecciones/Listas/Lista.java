package Colecciones.Listas;
import Colecciones.Coleccion;

/**
 * Interfaz que define el contrato para estructuras de datos de tipo Lista.
 * Una lista es una coleccion ordenada de elementos accesibles por su posicion (indice).
 * Esta interfaz extiende Coleccion para heredar sus operaciones basicas.
 * 
 * @author
 * @param <T> Tipo genérico de los elementos almacenados en la lista.
 */
public interface Lista<T> extends Coleccion<T>{

    /**
     * Metodo que elimina un elemento en la lista segun su indice.
     * 
     * @param indice Posicion del elemento a eliminar en la lista (basado en 0).
     * @throws IndexOutOfBoundsException si el indice esta fuera de los limites de la lista.
     */
    public void eliminar(int indice);

    /**
     * Metodo que accede y devuelve el elemento en una posicion especifica de la lista.
     * 
     * @param indice Posicion del elemento a acceder en la lista (basado en 0).
     * @return El elemento ubicado en la posicion especificada.
     * @throws IndexOutOfBoundsException si el indice esta fuera de los limites de la lista.
     */
    public T acceder(int indice);

    /**
     * Metodo que devuelve el indice (posicion) de la primera ocurrencia de un elemento en la lista.
     * 
     * @param elemento Elemento cuya posicion se desea encontrar.
     * @return El indice del elemento si se encuentra, -1 si no se encuentra en la lista.
     */
    public int devolverIndiceElemento(T elemento);

    /**
     * Metodo que devuelve la cantidad de elementos presentes en la lista.
     * 
     * @return El numero de elementos en la lista.
     */
    public int devolverLongitud();

}