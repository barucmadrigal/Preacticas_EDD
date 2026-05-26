package Picolas;


/**
 * Clase que implementa una Pila (Stack) genérica mediante una lista ligada.
 * Una pila es una estructura de datos de tipo LIFO (Last In, First Out) donde
 * el ultimo elemento insertado es el primero en ser removido.
 * 
 * @author
 * @param <T> Tipo genérico de los elementos almacenados en la pila.
 */
public class Pila<T> implements PiCoLa<T> {
    
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

    /**
     * Constructor que crea una pila vacia.
     */
    public Pila() {
        this.tope = tope;
        this.tamanio = 0;
    }

    /**
     * Metodo que inserta un elemento en la parte superior de la pila.
     * @param elemento Elemento a meter en la pila.
     */
    @Override 
    public void meter(T elemento) {
       Nodo n = new Nodo(elemento);
         n.siguiente = tope;
         this.tope = n;
         this.tamanio++;
    }

    /**
     * Metodo que extrae y devuelve el elemento en la parte superior de la pila.
     * @return El elemento removido de la parte superior de la pila.
     * @throws IllegalStateException si la pila esta vacia.
     */
    @Override
    public T sacar() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T elemento = this.tope.elemento;
        this.tope = this.tope.siguiente;
        this.tamanio--;
        return elemento;
    }

    /**
     * Metodo que devuelve el elemento en la parte superior de la pila sin removerlo.
     * @return El elemento en la parte superior de la pila.
     * @throws IllegalStateException si la pila esta vacia.
     */
    @Override
    public T mira() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return this.tope.elemento;
    }

    /**
     * Metodo que verifica si la pila esta vacia.
     * @return true si la pila esta vacia, false en caso contrario.
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
     * Metodo que devuelve el numero de elementos en la pila.
     * @return El tamanio de la pila.
     */
    @Override
    public int devolverTamanio() {
        return this.tamanio;
    }

    /**
     * Metodo que compara dos pilas por igualdad de contenido.
     * @param o Objeto a comparar con esta pila.
     * @return true si ambas pilas contienen los mismos elementos en el mismo orden, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") Pila<T> m = (Pila<T>)o;
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
     * Metodo que devuelve una representacion en cadena de la pila.
     * Los elementos se muestran del tope a la base, separados por comas.
     * @return Cadena que representa la pila.
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

    /**
     * Metodo principal para pruebas de la clase Pila.
     * @param args Argumentos de linea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>();
        pila.meter(1);
        pila.meter(2);
        pila.meter(3);
        System.out.println(pila); // Imprime: [3, 2, 1]

        System.out.println(pila.sacar()); // Imprime: 3
        System.out.println(pila.mira()); // Imprime: 2
        System.out.println(pila.estaVacia()); // Imprime: false
        System.out.println(pila.devolverTamanio()); // Imprime: 2
    }

}
