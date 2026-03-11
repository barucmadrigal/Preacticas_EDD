import java.util.Iterator;

/**
 * Implementación de una lista enlazada simple genérica.
 *
 * @param <T> El tipo de elementos almacenados en la lista.
 */
public class ListaLigadaSimple<T> implements Lista<T> {

    private class Nodo{
        /**
         * Elemento almacenado en el nodo.
         */
        public T elemento;

        /**
         * Apuntador al siguiente nodo en la lista.
         */
        public Nodo siguiente;

        /**
         * Crea un nuevo nodo con el elemento proporcionado.
         *
         * @param elemento El elemento a almacenar en el nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /**
    * Un iterador para recorrer la lista enlazada simple.
    */
    private class IteradorListaSimple implements Iterator<T> {

        /**
         * El nodo siguiente al que se moverá el iterador.
         */
        private Nodo iteradorLista;

        /**
         * Crea un nuevo iterador y lo inicializa en el primer nodo de la lista.
         */
        public IteradorListaSimple() {
            iteradorLista = new Nodo(null);
            iteradorLista.siguiente = cabeza;
        }

        /**
         * Verifica si hay un siguiente elemento en la lista.
         *
         * @return true si hay un siguiente elemento, false de lo contrario.
         */
        public boolean hasNext() {
            return iteradorLista.siguiente != null;
        }

        /**
         * Obtiene el siguiente elemento en la lista y mueve el iterador al siguiente nodo.
         *
         * @return El siguiente elemento en la lista.
         */
        public T next() {
            iteradorLista = iteradorLista.siguiente;
            return iteradorLista.elemento;
        }
    }

    private Nodo cabeza;

    private int longitud;

    public ListaLigadaSimple(){
      this.cabeza = null;
      this.longitud = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void agregar(T elemento) throws IllegalArgumentException {
        if(elemento == null){
           throw new IllegalArgumentException("El elemento no puede ser vacio");
        }

        else if(this.longitud == 0){
            Nodo cabezaN = new Nodo(elemento);
            this.cabeza = cabezaN; 
            this.longitud = longitud + 1; 
        }

        else{
            Nodo nuevoNodo = new Nodo(elemento);
            nuevoNodo.siguiente = this.cabeza; 
            this.cabeza = nuevoNodo;
            this.longitud = longitud + 1; 
        }
    }
    /**
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buscar(T elemento) {
       for(T x: this){
        if(x.equals(elemento)){
            return true; 
        }
       }
       return false; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T acceder(int i) throws IllegalArgumentException {
        // Verifica que el índice dado sea válido
        if (i < 0 || i >= this.longitud) {
            throw new IllegalArgumentException("El índice es inválido");
        }

        // Recorre la lista y cuenta los nodos
        Nodo actual = this.cabeza;
        int contador = 0;
        
        while (contador < i) {
            actual = actual.siguiente;
            contador++;
        }
        
        // Devuelve el elemento almacenado en el último nodo contado
        return actual.elemento; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(int i) {
        // Verifica que el índice dado sea válido
        if (i < 0 || i >= this.longitud) {
            throw new IllegalArgumentException("El índice es inválido");
        }

        // Caso especial: eliminar el primer elemento (índice 0)
        if (i == 0) {
            this.cabeza = this.cabeza.siguiente;
            this.longitud--;
            return;
        }

        // Recorre la lista hasta la posición anterior a la indicada
        Nodo anterior = this.cabeza;
        int contador = 0;
        while (contador < i - 1) {
            anterior = anterior.siguiente;
            contador++;
        }

        // Cambia las referencias de los nodos adyacentes
        Nodo aEliminar = anterior.siguiente;
        anterior.siguiente = aEliminar.siguiente;
        aEliminar.siguiente = null; // Desconecta el nodo por completo
        
        // Disminuye la longitud en uno
        this.longitud--;
    }

    /**
     * {@inheritDoc}
     */
    public void eliminar(T elemento){
      // Línea 1-2: Si l.longitud es igual a 0 entonces devolver l
        if (this.longitud == 0) {
            return;
        }

        // Línea 3-6: Si e es igual a l.cabeza.elemento entonces
        if (elemento.equals(this.cabeza.elemento)) {
            this.cabeza = this.cabeza.siguiente;
            this.longitud--;
            return;
        }

        // Línea 7 y 8: Sea anterior <- null, Sea aEliminar <- l.cabeza
        Nodo anterior = null;
        Nodo aEliminar = this.cabeza;

        // Línea 9: Mientras aEliminar sea distinto de null hacer
        while (aEliminar != null) {
            // Línea 10: Si aEliminar.elemento es igual a e entonces
            if (elemento.equals(aEliminar.elemento)) {
                // Líneas 11-14: Cambiar referencias y disminuir longitud
                anterior.siguiente = aEliminar.siguiente;
                aEliminar.siguiente = null;
                this.longitud--;
                return;
            }
            // Líneas 15-16: Avanzar los apuntadores
            anterior = aEliminar;
            aEliminar = aEliminar.siguiente;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int devolverIndiceElemento(T elemento) throws IllegalArgumentException{
        if (buscar(elemento) == false){
            throw new IllegalArgumentException("El elemeno no se encuentra en la lista");
        }

        int contador = 0; 
        for(T aux: this){
            if(elemento.equals(aux)){
                return contador; 
            }
            contador = contador + 1; 
        }
        return contador; 
    }

    /**
     * Obtiene la longitud actual de la lista.
     *
     * @return La longitud de la lista.
     */
    public int devolverLongitud() {
        return this.longitud + 1;
    }

    /**
     * Obtiene el primer nodo de la lista.
     *
     * @return El primer nodo de la lista.
     */
    public Nodo darCabeza() {
        return this.cabeza;
    }

    /**
     * Devuelve una representación en cadena de la lista ligada.
     * Los elementos se muestran en orden, separados por comas y encerrados entre corchetes.
     * 
     * @return una cadena con los elementos de la lista.
     */
    
    @Override
    public String toString() {
        String resultado = "[";
        boolean primero = true;
        for (T elemento : this) {
            if (!primero) {
                resultado += ", ";
            } else {
                primero = false;
            }
            resultado += elemento.toString();
        }

        resultado += "]";
        return resultado;
    }

}