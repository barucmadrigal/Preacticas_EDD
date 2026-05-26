package Colecciones.Listas;
import Colecciones.Coleccion;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Estructura de datos que guarda elementos en nodos conectados en dos direcciones.
 *
 * @param <T> El tipo de dato que va a guardar la lista.
 */
public class ListaDoblementeLigada<T> implements Lista<T> {

    /**
     * Clase interna que representa cada nodo o pieza de la lista.
     */
    public static class Nodo<T> {
        public T elemento;
        public Nodo<T> siguiente;
        public Nodo<T> anterior;

        public Nodo(T e) {
            this.elemento = e;
        }
    }

    /**
     * Clase interna para el iterador de la lista.
     */
    private class IteradorDoubleLinkedList implements Iterator<T> {
        public Nodo<T> anterior;
        public Nodo<T> siguiente;

        public IteradorDoubleLinkedList() {
            siguiente = cabeza;
        }

        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        @Override
        public T next() {
            if (siguiente == null) {
                throw new NoSuchElementException("No hay más elementos");
            }
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return anterior.elemento;
        }
    }

    private Nodo<T> cabeza;
    private Nodo<T> rabo;
    private int longitud;

    public ListaDoblementeLigada() {
        this.cabeza = null;
        this.rabo = null;
        this.longitud = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorDoubleLinkedList();
    }

    @Override
    public void agregar(T elemento) throws IllegalArgumentException {
        if (elemento == null) throw new IllegalArgumentException("Elemento nulo");
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            cabeza = rabo = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        longitud++;
    }

    public void agregarFinal(T elemento) throws IllegalArgumentException {
        if (elemento == null) throw new IllegalArgumentException("Elemento nulo");
        Nodo nuevo = new Nodo(elemento);
        if (rabo == null) {
            cabeza = rabo = nuevo;
        } else {
            rabo.siguiente = nuevo;
            nuevo.anterior = rabo;
            rabo = nuevo;
        }
        longitud++;
    }

    /**
     * Elimina el primer elemento de la lista (Cabeza).
     * Requerido para MergeSort.
     * @return El elemento eliminado.
     */
    public T eliminarCabeza() {
        if (cabeza == null) throw new NoSuchElementException("Lista vacía");
        T elemento = (T) cabeza.elemento;
        if (longitud == 1) {
            cabeza = rabo = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        longitud--;
        return elemento;
    }

    @Override
    public void eliminar(T elemento) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.elemento.equals(elemento)) {
                eliminarNodo(actual);
                return;
            }
            actual = actual.siguiente;
        }
    }

    @Override
    public void eliminar(int i) {
        if (i < 0 || i >= longitud) return;
        eliminarNodo(accederNodo(i));
    }

    /**
     * Método auxiliar para desconectar un nodo de la lista.
     */
    private void eliminarNodo(Nodo nodo) {
        if (nodo == cabeza) {
            eliminarCabeza();
        } else if (nodo == rabo) {
            rabo = nodo.anterior;
            rabo.siguiente = null;
            longitud--;
        } else {
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;
            longitud--;
        }
    }

    @Override
    public boolean buscar(T elemento) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.elemento.equals(elemento)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public T acceder(int i) throws IllegalArgumentException {
        return accederNodo(i).elemento;
    }

    private Nodo<T> accederNodo(int i) {
        if (i < 0 || i >= longitud) throw new IllegalArgumentException("Índice fuera de rango");
        Nodo<T> actual = cabeza;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    @Override
    public int devolverIndiceElemento(T elemento) {
        Nodo actual = cabeza;
        int indice = 0;
        while (actual != null) {
            if (actual.elemento.equals(elemento)) return indice;
            actual = actual.siguiente;
            indice++;
        }
        return -1;
    }

    @Override
    public int devolverLongitud() {
        return longitud;
    }

    /**
     * Versión iterativa corregida de DevolverIndice para evitar errores de memoria.
     */
    public Nodo DevolverIndice(int i) {
        return accederNodo(i);
    }


    public static <T extends Comparable<T>> void mergeSort(ListaDoblementeLigada<T> lista) {
        if (lista == null || lista.devolverLongitud() < 2) return;

        ListaDoblementeLigada<T> izq = new ListaDoblementeLigada<>();
        ListaDoblementeLigada<T> der = new ListaDoblementeLigada<>();
        int mitad = lista.devolverLongitud() / 2;
        int i = 0;

        for (T elemento : lista) {
            if (i < mitad) izq.agregarFinal(elemento);
            else der.agregarFinal(elemento);
            i++;
        }

        mergeSort(izq);
        mergeSort(der);

        ListaDoblementeLigada<T> resultado = mezclar(izq, der);

        // Limpiamos la lista original y rellenamos
        while (lista.devolverLongitud() > 0) lista.eliminarCabeza();
        for (T elemento : resultado) lista.agregarFinal(elemento);
    }

    private static <T extends Comparable<T>> ListaDoblementeLigada<T> mezclar(ListaDoblementeLigada<T> li, ListaDoblementeLigada<T> ld) {
        ListaDoblementeLigada<T> res = new ListaDoblementeLigada<>();
        Nodo<T> ni = li.cabeza;
        Nodo<T> nd = ld.cabeza;

        while (ni != null && nd != null) {
            if (ni.elemento.compareTo(nd.elemento) <= 0) {
                res.agregarFinal(ni.elemento);
                ni = ni.siguiente;
            } else {
                res.agregarFinal(nd.elemento);
                nd = nd.siguiente;
            }
        }

        while (ni != null) { res.agregarFinal(ni.elemento); ni = ni.siguiente; }
        while (nd != null) { res.agregarFinal(nd.elemento); nd = nd.siguiente; }

        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo actual = cabeza;
        while (actual != null) {
            sb.append(actual.elemento);
            if (actual.siguiente != null) sb.append(", ");
            actual = actual.siguiente;
        }
        return sb.append("]").toString();
    }
}