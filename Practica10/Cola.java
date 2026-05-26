/**
 * Clase que implementa una Cola (Queue) genérica mediante una lista ligada.
 * Una cola es una estructura de datos de tipo FIFO (First In, First Out) donde
 * el primer elemento insertado es el primero en ser removido.
 * 
 * @author
 * @param <T> Tipo genérico de los elementos almacenados en la cola.
 */
public class Cola<T> implements PiCoLa<T> {

    
    /**
     * Clase privada interna que representa un nodo en la lista ligada.
     */
    private class Nodo {
        public T elemento;
        public Nodo siguiente;

        /**
         * Constructor de un Nodo.
         * @param elemento Elemento a almacenar en el nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
            this.siguiente = null;
        }
    }

    protected Nodo tope;
    private int tamanio;
    protected Nodo fondo; 

    /**
     * Constructor que crea una cola vacia.
     */
    public Cola() {
        this.tope = tope;
        this.tamanio = 0;
        this.fondo = fondo;
    }

    /**
     * Metodo que agrega un elemento al final de la cola.
     * @param elemento El elemento a agregar a la cola.
     */
    @Override 
    public void meter(T elemento) {
        Nodo n = new Nodo(elemento);
        if (estaVacia()){
            this.tope = n; 
            this.fondo = n; 
        } else {
            this.fondo.siguiente = n;
        }
        this.fondo = n;
        this.tamanio++;
    }

    /**
     * Metodo que elimina y devuelve el elemento al frente de la cola.
     * @return El elemento al frente de la cola.
     * @throws IllegalAccessError Si la cola está vacía.
     */
    @Override
    public T sacar() {
        if(estaVacia()){
            throw new IllegalAccessError("La cola está vacía");
        }

        T elemento = this.tope.elemento;
        this.tope = this.tope.siguiente;
        this.tamanio--;
        return elemento; 
    }

    /**
     * Metodo que devuelve el elemento al frente de la cola sin eliminarlo.
     * @return El elemento al frente de la cola.
     * @throws IllegalStateException Si la cola está vacía.
     */
    @Override
    public T mira() {
        if(estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return this.tope.elemento;
    }

    /**
     * Metodo que devuelve un booleano indicando si la cola está vacía o no.
     * @return Booleano indicando si la cola está vacía o no.
     */
    @Override
    public boolean estaVacia() {
        if (devolverTamanio() == 0) {
            return true;
        } else {
            return false;
       }
    }

    /**
     * Metodo que devuelve el numero de elementos en la cola.
     * @return El numero de elementos en la cola.
     */
    @Override
    public int devolverTamanio() {
        return this.tamanio;
    }

    /**
     * Metodo que compara esta cola con otro objeto para determinar si son iguales.
     * Dos colas son iguales si contienen los mismos elementos en el mismo orden.
     * @param o El objeto a comparar con esta cola.
     * @return true si las colas son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") Cola<T> m = (Cola<T>)o;
        Nodo n1 = this.tope;
        Nodo n2 = m.tope;
        while (n1 !=null && n2 != null){
            if (!n1.elemento.equals(n2.elemento))
                return false;
            n1 = n1.siguiente;
            n2 = n2.siguiente;
        }
        return (n1 == null && n2 == null);
    }

    /**
     * Metodo que devuelve una representacion en cadena de la cola.
     * Los elementos se muestran del frente al final, separados por comas.
     * @return Cadena que representa la cola.
     */
    @Override
    public String toString() {
        String resultado = "[";
        Nodo actual = tope;

        while (actual != null) {
            resultado += actual.elemento;
            if (actual.siguiente != null) {
                resultado += ",\n ";
            }
            actual = actual.siguiente;
        }

        resultado += "]";
        return resultado;
    }
}
