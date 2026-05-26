package Picolas;

public class Cola<T> implements PiCoLa<T> {

    
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
    protected Nodo fondo; 

    public Cola() {
        this.tope = tope;
        this.tamanio = 0;
        this.fondo = fondo;
    }

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

    @Override
    public T mira() {
        if(estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
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
        Cola<Integer> c = new Cola<>();
        int s = 0;
        for(int i = 0; i <= 10; i++){
            c.meter(s * i*i);
        }
        
        System.out.println(c); // Imprime: [1, 2, 3]
        System.out.println(c.sacar()); // Imprime: 1
        System.out.println(c); // Imprime: [2, 3]
        System.out.println(c.mira()); // Imprime: 2
        System.out.println(c.estaVacia());
        System.out.println(c.devolverTamanio());
    }
    
}
