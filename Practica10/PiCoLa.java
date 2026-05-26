/**
 * Interfaz que define el contrato para estructuras de datos tipo Pila (Stack) y Cola (Queue).
 * PiCoLa es una abreviatura que combina "Pila" y "Cola", representando las operaciones
 * basicas comunes a ambas estructuras de datos.
 * 
 * @author
 * @param <T> Tipo genérico de los elementos almacenados en la estructura.
 */
public interface PiCoLa<T> {
    
    /**
     * Metodo que inserta un elemento en la estructura.
     * Para una pila, se inserta en el tope.
     * Para una cola, se inserta al final.
     * 
     * @param elemento Elemento a insertar en la estructura.
     */
    public void meter(T elemento);   
    
    /**
     * Metodo que extrae y devuelve un elemento de la estructura.
     * Para una pila, se extrae del tope (LIFO).
     * Para una cola, se extrae del frente (FIFO).
     * 
     * @return El elemento removido de la estructura.
     * @throws IllegalStateException si la estructura esta vacia.
     */
    public T sacar();                
    
    /**
     * Metodo que devuelve el elemento accesible sin removerlo de la estructura.
     * Para una pila, devuelve el elemento del tope.
     * Para una cola, devuelve el elemento del frente.
     * 
     * @return El elemento accesible sin remover de la estructura.
     * @throws IllegalStateException si la estructura esta vacia.
     */
    public T mira();            
    
    /**
     * Metodo que verifica si la estructura esta vacia.
     * 
     * @return true si la estructura no contiene elementos, false en caso contrario.
     */
    public boolean estaVacia();
    
    /**
     * Metodo que devuelve el numero total de elementos en la estructura.
     * 
     * @return El tamanio de la estructura.
     */
    public int devolverTamanio();

    /**
     * Metodo que compara esta estructura con otro objeto por igualdad.
     * Dos estructuras son iguales si contienen los mismos elementos en el mismo orden.
     * 
     * @param obj Objeto a comparar con esta estructura.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj);

    /**
     * Metodo que devuelve una representacion en cadena de la estructura.
     * 
     * @return Cadena que representa los elementos de la estructura.
     */
    @Override
    public String toString();
}