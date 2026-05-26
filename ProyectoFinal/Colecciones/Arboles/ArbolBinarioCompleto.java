package Colecciones.Arboles;
import Colecciones.Listas.ListaDoblementeLigada;
import Picolas.Cola; 
import Colecciones.Coleccion; 

/**
 * Clase que representa un Arbol Binario Completo.
 * Un arbol binario completo es un arbol binario en el cual todos los niveles
 * estan completamente llenos excepto posiblemente el ultimo nivel, que se llena
 * de izquierda a derecha.
 * @author
 * @param <T> Tipo generico del arbol.
 */
public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {
    
    private Vertice ultimoAgregado;

    /**
     * Constructor de un Arbol Binario Completo vacio.
     */
    public ArbolBinarioCompleto() {
        super();
        ultimoAgregado = null;
    }

    /**
     * Metodo que agrega un elemento al Arbol Binario Completo.
     * El elemento se agrega manteniendo la propiedad de completo del arbol.
     * @param elemento Elemento a agregar al arbol.
     * @throws IllegalArgumentException si el elemento es nulo.
     */
   @Override
    public void agregar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se pueden agregar elementos nulos al arbol.");
        }
        Vertice nuevo = new Vertice(elemento);
        if (this.estaVacio()){  
            this.raiz = nuevo; 
            this.ultimoAgregado = nuevo;
            this.tamanio++;
        }else{
            Vertice vi = this.ultimoAgregado;
            if (vi.esHijoIzquierdo()){
                vi.padre.agregaDerecho(nuevo);
                this.ultimoAgregado = nuevo;
                this.tamanio++;
            }else{
                // Subimos mientras seamos hijos derechos
                while (vi.esHijoDerecho()){
                    vi = vi.padre; 
                }
                
                // Si no llegamos a la raíz, saltamos al HERMANO DERECHO
                if (vi != this.raiz){
                    vi = vi.padre.derecho; // <-- CORRECCIÓN AQUÍ
                }
                
                // A partir de ahí (o desde la raíz si el árbol estaba lleno),
                // bajamos todo hacia la izquierda
                this.agregaEnPrimerEspacioIzquierdo(vi, nuevo);
            }
        }
    }

    /**
     * Metodo que elimina un elemento del Arbol Binario Completo (Algoritmo 1.9).
     * El metodo realiza una busqueda BFS del elemento, lo reemplaza con el ultimo
     * vertice del arbol y luego elimina el ultimo vertice.
     * @param elemento Elemento a eliminar del arbol.
     * @throws IllegalArgumentException si el elemento es nulo, el arbol esta vacio o el elemento no se encuentra.
     */
    @Override
    public void eliminar(T elemento) {
        if (elemento == null || this.estaVacio()) {
            throw new IllegalArgumentException("No se pueden eliminar elementos nulos del arbol o el arbol esta vacio.");
        }

        Vertice verticeAEliminar = null;
        Vertice ultimoVertice = null;
        Cola<Vertice> cola = new Cola<Vertice>();
        
        cola.meter(this.raiz);
        
        while (!cola.estaVacia()) {
            Vertice actual = cola.sacar();
            ultimoVertice = actual;
            
            if (actual.elemento.equals(elemento)) {
                verticeAEliminar = actual;
            }
            
            if (actual.izquierdo != null) {
                cola.meter(actual.izquierdo);
            }
            
            if (actual.derecho != null) {
                cola.meter(actual.derecho);
            }
        }
        
        if (verticeAEliminar == null) {
            throw new IllegalArgumentException("El elemento no se encuentra en el arbol.");
        }
        
        verticeAEliminar.elemento = ultimoVertice.elemento;
        
        if (ultimoVertice == this.raiz) {
            this.raiz = null;
        } else {
            Vertice padre = ultimoVertice.padre;
            
            if (padre.izquierdo == ultimoVertice) {
                padre.izquierdo = null;
            } else {
                padre.derecho = null;
            }
        }
        
        this.tamanio--;
        
        this.actualizarUltimoAgregado();
    }

    /**
     * Metodo privado que actualiza la referencia al ultimo vertice agregado del arbol (Algoritmo 1.10).
     * Realiza un recorrido BFS del arbol y actualiza ultimoAgregado al ultimo vertice visitado.
     */
    private void actualizarUltimoAgregado() {
        if (this.estaVacio()) {
            this.ultimoAgregado = null;
            return;
        }
        
        Cola<Vertice> cola = new Cola<Vertice>();
        
        cola.meter(this.raiz);
        
        Vertice ultimo = null;
        
        while (!cola.estaVacia()) {
            ultimo = cola.sacar();
            
            if (ultimo.izquierdo != null) {
                cola.meter(ultimo.izquierdo);
            }
            
            if (ultimo.derecho != null) {
                cola.meter(ultimo.derecho);
            }
        }
        
        this.ultimoAgregado = ultimo;
    }

    /**
     * Metodo que busca un elemento en el Arbol Binario Completo.
     * @param elemento Elemento a buscar en el arbol.
     * @return true si el elemento se encuentra en el arbol, false en caso contrario.
     */
    @Override
    public boolean buscar(T elemento) {
        return buscar(elemento, raiz);
    }

    /**
     * Metodo privado recursivo que realiza la busqueda de un elemento.
     * @param elemento Elemento a buscar.
     * @param v Vertice actual en el recorrido.
     * @return true si el elemento se encuentra en el subarbol, false en caso contrario.
     */
    private boolean buscar(T elemento, Vertice v){
       if (v == null){
        return false;
       }
       if(v.elemento.equals(elemento)){
        return true; 
       }
    return buscar(elemento, v.izquierdo) || buscar(elemento, v.derecho);
    }

    /**
     * Metodo que devuelve una lista con los elementos del arbol en recorrido BFS.
     * @return ListaDoblementeLigada con los elementos del arbol en orden BFS.
     */
    @Override
    public ListaDoblementeLigada<T> devolverRecorrido() {
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<>();
        if (this.estaVacio() == true){
            return recorrido; 
        }
        Cola<Vertice> cola = new Cola<Vertice>();
        cola.meter(this.raiz);
        while(!cola.estaVacia()){
            Vertice actual = cola.sacar();
            recorrido.agregarFinal(actual.elemento);                                                                                                                                                                

            if (actual.izquierdo != null){
                cola.meter(actual.izquierdo);
            }
            if (actual.derecho != null){
                cola.meter(actual.derecho); 
            }
        }
        return recorrido;
    }

    /**
     * Metodo privado que agrega un nuevo vertice en el primer espacio izquierdo disponible.
     * @param desde Vertice desde donde comienza la busqueda del primer espacio izquierdo.
     * @param nuevo Nuevo vertice a agregar.
     */
    private void agregaEnPrimerEspacioIzquierdo(Vertice desde, Vertice nuevo) {
        while(desde.izquierdo != null){
            desde = desde.izquierdo; 
        }
        desde.agregaIzquierdo(nuevo);
        nuevo.padre = desde;
        this.ultimoAgregado = nuevo;
        this.tamanio++;
    }

}
