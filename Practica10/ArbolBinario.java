import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase abstracta que representa un Árbol Binario genérico.
 * Un árbol binario es una estructura de datos jerárquica donde cada nodo tiene
 * a lo máximo dos hijos (izquierdo y derecho).
 * 
 * @author
 * @param <T> Tipo genérico de los elementos almacenados en el árbol.
 */
public abstract class ArbolBinario<T> implements Coleccion<T> {

    /**
     * Clase interna que representa un vértice o nodo en el árbol binario.
     * Cada vértice contiene un elemento y referencias a sus hijos y padre.
     */
    protected class Vertice {

        public T elemento;

        public Vertice izquierdo;

        public Vertice derecho;

        public Vertice padre;

        public Vertice(T elemento) {
            this.elemento = elemento;
            this.izquierdo = null;
            this.derecho = null;
            this.padre = null;
        }

        public boolean esHijoIzquierdo() {
            return padre != null && padre.izquierdo == this;
        }

        public boolean esHijoDerecho() {
            return padre != null && padre.derecho == this;
        }

        public void agregaIzquierdo(Vertice hijo) {
            izquierdo = hijo;
            if (hijo != null){ 
                hijo.padre = this;
            }
        }

        public void agregaDerecho(Vertice hijo) {
            derecho = hijo;
            if (hijo != null) hijo.padre = this;
        }
        
       public String toString(){
            String cadena = AsciiBox.addAccents(elemento.toString());
            cadena = AsciiBox.asciiBox(cadena, false, AsciiBox.length(cadena));
            int type = 2;
            if(izquierdo == null && derecho == null){
                type = 3;
            } else if(padre == null){
                type = 1;
            }
            return AsciiBox.nodify(cadena,type);
        }       
       
    }

    protected Vertice raiz;

    protected int tamanio;

    public ArbolBinario() {
        this.raiz = null;
        this.tamanio = 0;
    }

    @Override
    public abstract boolean buscar(T elemento);

    /**
     * Metodo que accede a un elemento del arbol dado su indice en el recorrido BFS.
     * @param indice Indice del elemento a acceder en el recorrido BFS del arbol.
     * @return El elemento ubicado en el indice dado en el recorrido BFS del arbol.
     */
    public T acceder(int indice) {
        ListaDoblementeLigada<T> recorrido = devolverRecorrido();
        T elemento = recorrido.acceder(indice);
        return elemento; 
    }

    /**
     * Metodo que devuelve el numero de elementos en el arbol.
     * @return El numero de elementos en el arbol.
     */
    public int devolverTamanio() {
        return this.tamanio; 
    }

    /** 
     * Metodo que devuelve un booleano indicando si el arbol esta vacio o no.
     * @return Booleano indicando si el arbol esta vacio o no.
     */
    public boolean estaVacio() {
        if (this.tamanio == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public abstract void agregar(T elemento);

    @Override
    public abstract void eliminar(T elemento);

    protected abstract ListaDoblementeLigada<T> devolverRecorrido();

    @Override
    public Iterator<T> iterator() {
        return devolverRecorrido().iterator();
    }

     /**
     * Método que convierte un árbol a su representación en ascii art
     * @param root Vértice raíz del árbol.
     * @return Cadena con la representación en ascii art de un árbol n-ario
     */
    public String asciiTree(Vertice root){
        if(root.izquierdo == null && root.derecho == null){
            return root.toString();
        } else {
            String concatVertex = "";
            String cadenaIzquierda = root.izquierdo != null ? asciiTree(root.izquierdo) : "";
            String cadenaDerecha = root.derecho != null ? asciiTree(root.derecho) : "";
            concatVertex = AsciiBox.concatAscii(cadenaIzquierda,cadenaDerecha,1,true);
            return AsciiBox.treeConcat(root.toString(),AsciiBox.addTreeEdge(concatVertex));
        }
    }     

    public String toString(){
        if(estaVacio()){
            return "No hay árbol";
        } else if(raiz.izquierdo == null && raiz.derecho == null) {
            return AsciiBox.asciiBox(raiz.elemento,false,AsciiBox.length(raiz.elemento.toString()));
        } else {
            return asciiTree(raiz);
        }
    }
}
