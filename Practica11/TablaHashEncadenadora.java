import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TablaHashEncadenadora<K, V> extends TablaHash<K, V>{

    /* Clase privada para iteradores de TablaHash. */
    private class Iterador {

        /* En qué lista estamos. */
        private int indice;
        /* Iterador auxiliar. */
        private Iterator<Entrada> iterador;

        /* Construye un nuevo iterador, auxiliándose de las listas de la * TablaHash. */
        public Iterador() {
            ListaDoblementeLigada<Entrada> listini = new ListaDoblementeLigada<Entrada>();
            for (int i = 0; i < entradas.length; i++){
                if (entradas[i] != null){
                    for (Entrada entrada : entradas[i]){
                        listini.agregar(entrada);
                    }
                }
            }
            this.iterador = listini.iterator();
        }

        /* Nos dice si hay una siguiente entrada. */
        public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa la siguiente entrada. */
        public Entrada siguiente() { 
            return iterador.next();
        }
    }

    /* Clase privada para iteradores de llaves de TablaHash. */
    private class IteradorLlaves extends Iterador implements Iterator<K> {

        /* Construye un nuevo iterador de llaves del TablaHash. */
        public IteradorLlaves() {
            super();
        }

        /* Regresa el siguiente elemento. */
        @Override public K next() {
            return siguiente().llave;
        }
    }

    /* Clase privada para iteradores de valores de una TablaHash. */
    private class IteradorValores extends Iterador implements Iterator<V> {

        /* Construye un nuevo iterador de llaves de la TablaHash. */
        public IteradorValores() {
            super();
        }

        /* Regresa el siguiente elemento. */
        @Override public V next() {
            return siguiente().valor;
        }
    }

    /* Arreglo de listas que representa la tabla hash que utiliza el método de encadenamiento para manejar coliciones. */
    private ListaDoblementeLigada<Entrada>[] entradas;

    /* Truco para crear un arreglo genérico. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked")
    private ListaDoblementeLigada<Entrada>[] crearNuevoArreglo(int n) {
        return (ListaDoblementeLigada<Entrada>[])Array.newInstance(ListaDoblementeLigada.class, n);
    }

    /**
     * Construye una TablaHash con una capacidad inicial y dispersor
     * predeterminados.
     */
    public TablaHashEncadenadora() {
        this(MINIMA_CAPACIDAD, (K p) -> p.hashCode());
    }

    /**
     * Construye una TablaHash con una capacidad inicial definida por el
     * usuario, y un dispersor predeterminado.
     * @param capacidad la capacidad a utilizar.
     */
    public TablaHashEncadenadora(int capacidad) {
        this(capacidad, (K p) -> p.hashCode());
    }

    /**
     * Construye una TablaHash con una capacidad inicial predeterminada, y un
     * dispersor definido por el usuario.
     * @param dispersor el dispersor a utilizar.
     */
    public TablaHashEncadenadora(Dispersor<K> dispersor) {
        entradas = crearNuevoArreglo(MINIMA_CAPACIDAD);
        this.dispersor = dispersor;
    }

    /**
     * Construye una TablaHash con una capacidad inicial y un método de
     * dispersor definidos por el usuario.
     * @param capacidad la capacidad inicial de una TablaHash.
     * @param dispersor el dispersor a utilizar.
     */
    public TablaHashEncadenadora(int capacidad, Dispersor<K> dispersor) {
        this.dispersor = dispersor;
        if(capacidad < MINIMA_CAPACIDAD){
            entradas = crearNuevoArreglo(MINIMA_CAPACIDAD);
        }else{
            capacidad = calcularNuevoTamanio(capacidad);
            entradas = crearNuevoArreglo(capacidad);
        }
        elementos = 0;
    }

    /**
     * Agrega un nuevo elemento a la TablaHash. Si la llave ya existe, se reemplaza 
     * el valor asociado a esa llave por el nuevo valor. Si la llave o el valor son null, se lanza una excepción.  
     */
        @Override
        public void agregar(K llave, V valor) {
        if (llave == null || valor == null) {
            throw new NoSuchElementException("El elemento es null");
        }
        
        int i = Math.abs(dispersor.dispersa(llave)) % this.entradas.length;
        
        if (this.entradas[i] == null) {
            this.entradas[i] = new ListaDoblementeLigada<Entrada>();
        }

        for (Entrada e : this.entradas[i]) {
            if (e.llave.equals(llave)) {
                e.valor = valor;
                return; 
            }
        }

        this.entradas[i].agregar(new Entrada(llave, valor));
        this.elementos++; 

        if (this.devolverCarga() >= MAXIMA_CARGA) {
            this.redimencionaArreglo();
        }
    }

    /* Obtiene el valor asociado a una llave. */
    @Override
    public V obtenerValorLlave(K llave) throws IllegalArgumentException{
        // 1. Manejo de casos nulos
    if (llave == null) {
        return null; 
    }

    int i = dispersor.dispersa(llave) % this.entradas.length;

    if (this.entradas[i] == null) {
        return null;
    }

    for (Entrada e : this.entradas[i]) {
        if (e.llave.equals(llave)) {
            return e.valor; // Encontramos la llave, regresamos su valor
        }
    }

    return null;
    }

    /**
     * Busca si una llave existe en la TablaHash. Si la llave es null, se regresa false. Si la llave no existe, se regresa false. Si la llave existe, se regresa true.
     */
    @Override
    public boolean buscar(K llave) {
        if(llave == null){
            return false; 
        }
        else {
            int i = dispersor.dispersa(llave) % (this.entradas.length);
            if(this.entradas[i] == null){
            return false; 
        }
        if(this.entradas[i] == null){
            return false; 
        }
        for(Entrada e: this.entradas[i]){
            if(e.llave == llave){
                return false; 
            }
        }
            return false; 
        }  
    }

    /**
     * Elimina una llave de la TablaHash. Si la llave es null, se lanza una excepción. 
     * Si la llave no existe, se lanza una excepción. Si la llave existe, se elimina de la TablaHash.
     */
    @Override
    public void eliminar(K llave) {
    // Usar buscar para validar
    if (llave == null || !this.buscar(llave)) {
        throw new NoSuchElementException("No se encontró la llave");
    }
    
    int i = Math.abs(dispersor.dispersa(llave)) % this.entradas.length;
    
    for (Entrada e : this.entradas[i]) {
        if (e.llave.equals(llave)) {
            this.entradas[i].eliminar(e);
            this.elementos--; // Restar solo una vez
            return; // Salir de inmediato
        }
    }
}

    /**
     * Devuelve el número de elementos almacenados en la TablaHash.
     * @return el número de elementos almacenados en la TablaHash.
     */
    public int devolverElementos() {
       return this.elementos; 
    }

    /**
     * Devuelve un iterador de las llaves almacenadas en la TablaHash.
     * @return un iterador de las llaves almacenadas en la TablaHash.
     */
    public Iterator<K> iteradorLlaves() {
        return new IteradorLlaves();
    }

    /**
     * Devuelve un iterador de los valores almacenados en la TablaHash.
     */
    @Override public Iterator<V> iterator() {
        return new IteradorValores();
    }  

    /**
     * Devuelve la carga actual de la TablaHash, que es el número de elementos almacenados dividido entre la capacidad de la TablaHash.
      * @return la carga actual de la TablaHash.
     */
    @Override
    public double devolverCarga() {
        return (double)this.elementos/this.entradas.length; 
    }

    @Override
    protected void redimencionaArreglo(){
        int tamaño = this.calcularNuevoTamanio(elementos);
        ListaDoblementeLigada<Entrada>[] viejasEntradas = this.entradas;
        ListaDoblementeLigada<Entrada>[] ArregloNuevo = crearNuevoArreglo(tamaño);
        this.entradas = ArregloNuevo; 
        this.elementos = 0; 
        for(ListaDoblementeLigada<Entrada> lista : viejasEntradas){
            if(lista != null){
                for(Entrada e : lista){
                    this.agregar(e.llave, e.valor);
                }
            }
        return; 
        }
    }

    /**
     * Metodo toString para imprimir la TablaHash. Recorre el arreglo de listas y concatena las entradas en una cadena de texto.
     *@return una cadena de texto con las entradas de la TablaHash.
     */
    @Override
    public String toString() {
        String cadena = "";
        for(int i = 0; i < this.entradas.length;i++){
            cadena += "entradas[" + i + "] = " + this.entradas[i] + "\n";
        }
        return cadena;
    }
}
