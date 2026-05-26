package Picola;

public class Pila<T> implements PiCoLa<T> {

    private class Nodo {
        public T elemento;
        public Nodo siguiente;

        public Nodo(T elemento) {
            this.elemento = elemento;
            this.siguiente = null;
        }
    }

    protected Nodo tope;
    private int tamanio;

    public Pila() {
        this.tope = tope;
        this.tamanio = 0;
    }

    @Override 
    public void meter(T elemento) {
       Nodo n = new Nodo(elemento);
         n.siguiente = tope;
         this.tope = n;
         this.tamanio++;
    }

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

    @Override
    public T mira() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return this.tope.elemento;
    }

    @Override
    public boolean estaVacia() {
        if (devolverTamanio() == 0) {
            return true;
        } else {
            return false;
       }
    }

    @Override
    public int devolverTamanio() {
        return this.tamanio;
    }

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
