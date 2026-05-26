
/**
 * Clase que implementa un Árbol Binario de Búsqueda (BST) ordenado.
 * Los elementos se organizan de forma que cualquier elemento en el subárbol
 * izquierdo es menor que la raíz, y cualquier elemento en el subárbol derecho
 * es mayor que la raíz, permitiendo búsquedas eficientes.
 * 
 * @author
 * @param <T> Tipo genérico de los elementos (debe implementar Comparable).
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>> extends ArbolBinario<T> {

    @Override
    public void agregar(T elemento) {
        if (elemento == null) return;
        Vertice nuevo = new Vertice(elemento);
        tamanio++;
        if (raiz == null) {
            raiz = nuevo;
            return;
        }
        agregar(raiz, nuevo);
    }

    private void agregar(Vertice actual, Vertice nuevo) {
        if (nuevo.elemento.compareTo(actual.elemento) < 0) {
            if (actual.izquierdo == null) {
                actual.agregaIzquierdo(nuevo);
            } else {
                agregar(actual.izquierdo, nuevo);
            }
        } else if (nuevo.elemento.compareTo(actual.elemento) > 0) {
            if (actual.derecho == null) {
                actual.agregaDerecho(nuevo);
            } else {
                agregar(actual.derecho, nuevo);
            }
        } else {
            // No se permiten repetidos según la convención de la práctica [cite: 11]
            tamanio--;
        }
    }

    @Override
    public boolean buscar(T elemento) {
        return buscar(raiz, elemento) != null;
    }

    private Vertice buscar(Vertice actual, T elemento) {
        if (actual == null || elemento == null) return null;
        if (elemento.equals(actual.elemento)) return actual;
        if (elemento.compareTo(actual.elemento) < 0) {
            return buscar(actual.izquierdo, elemento);
        }
        return buscar(actual.derecho, elemento);
    }

    @Override
    public void eliminar(T elemento) {
        Vertice v = buscar(raiz, elemento);
        if (v == null) return;
        tamanio--;
        eliminarVertice(v);
    }

    private void eliminarVertice(Vertice v) {
        // Caso 1: El vértice no tiene hijos (es hoja)
        if (v.izquierdo == null && v.derecho == null) {
            if (v == raiz) raiz = null;
            else if (v.esHijoIzquierdo()) v.padre.izquierdo = null;
            else v.padre.derecho = null;
        }
        // Caso 2: Tiene un solo hijo
        else if (v.izquierdo == null || v.derecho == null) {
            Vertice hijo = (v.izquierdo != null) ? v.izquierdo : v.derecho;
            if (v == raiz) {
                raiz = hijo;
                hijo.padre = null;
            } else {
                hijo.padre = v.padre;
                if (v.esHijoIzquierdo()) v.padre.izquierdo = hijo;
                else v.padre.derecho = hijo;
            }
        }
        // Caso 3: Tiene dos hijos
        else {
            Vertice sucesor = intercambiar(v);
            eliminarVertice(sucesor);
        }
    }

    private Vertice intercambiar(Vertice v) {
        Vertice sucesor = v.derecho;
        while (sucesor.izquierdo != null) sucesor = sucesor.izquierdo;
        T temp = v.elemento;
        v.elemento = sucesor.elemento;
        sucesor.elemento = temp;
        return sucesor;
    }

    @Override
    protected ListaDoblementeLigada<T> devolverRecorrido() {
        ListaDoblementeLigada<T> lista = new ListaDoblementeLigada<>();
        inOrder(raiz, lista);
        return lista;
    }

    private void inOrder(Vertice v, ListaDoblementeLigada<T> lista) {
        if (v == null) return;
        inOrder(v.izquierdo, lista);
        lista.agregarFinal(v.elemento);
        inOrder(v.derecho, lista);
    }
}
