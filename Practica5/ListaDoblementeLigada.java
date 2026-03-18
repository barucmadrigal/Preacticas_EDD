import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoblementeLigada<T> implements Lista<T> {

     private class Nodo{
          public T elemento;

          public Nodo siguiente;

          public Nodo anterior;

          public Nodo(T e) {
               this.elemento = e;
          }
     }

     private class IteradorDoubleLinkedList implements Iterator<T> {

          public Nodo anterior;

          public Nodo siguiente;

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
                    throw new NoSuchElementException("El elemento es null");
               }
               anterior = siguiente;
               siguiente = siguiente.siguiente;
               return anterior.elemento;
          }
     }

     private Nodo cabeza;

     private Nodo rabo;

     private int longitud;

     public Iterator<T> iterator() {
          return new IteradorDoubleLinkedList();
     }

     public ListaDoblementeLigada(){
          this.cabeza = null;
          this.rabo = null;
          this.longitud = 0;
     }
/**
      * Agrega un nuevo elemento al inicio de la lista.
      *
      * @param elemento El dato que queremos agregar.
      * @throws IllegalArgumentException Si el elemento a agregar es nulo.
      */
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

     /**
      * Busca un elemento en la lista y lo elimina si lo encuentra.
      *
      * @param elemento El dato que queremos borrar.
      */
     @Override
     public void eliminar(T elemento){
          Nodo actual = cabeza;
          while (actual != null) {
               if (actual.elemento.equals(elemento)) {
                    if (actual == cabeza) {
                         cabeza = actual.siguiente;
                         if (cabeza != null) cabeza.anterior = null;
                         else rabo = null;
                    } else if (actual == rabo) {
                         rabo = actual.anterior;
                         if (rabo != null) rabo.siguiente = null;
                         else cabeza = null;
                    } else {
                         actual.anterior.siguiente = actual.siguiente;
                         actual.siguiente.anterior = actual.anterior;
                    }
                    longitud--;
                    return;
               }
               actual = actual.siguiente;
          }
     }

     /**
      * Revisa si un elemento especifico existe dentro de la lista.
      *
      * @param elemento El dato que estamos buscando.
      * @return true si el elemento esta en la lista, false si no esta.
      */
     @Override
     public boolean buscar(T elemento) {
          Nodo actual = cabeza;
          while (actual != null) {
               if (actual.elemento.equals(elemento)) return true;
               actual = actual.siguiente;
          }
          return false;
     }

     /**
      * Borra el elemento que se encuentra en una posicion exacta.
      *
      * @param i El numero de la posicion que queremos borrar (empezando desde 0).
      */
     @Override
     public void eliminar(int i) {
          if (i < 0 || i >= longitud) return;
          Nodo actual = accederNodo(i);
          if (actual == cabeza) {
               cabeza = actual.siguiente;
               if (cabeza != null) cabeza.anterior = null;
               else rabo = null;
          } else if (actual == rabo) {
               rabo = actual.anterior;
               if (rabo != null) rabo.siguiente = null;
               else cabeza = null;
          } else {
               actual.anterior.siguiente = actual.siguiente;
               actual.siguiente.anterior = actual.anterior;
          }
          longitud--;
     }

     /**
      * Obtiene el elemento guardado en una posicion exacta sin borrarlo.
      *
      * @param i La posicion del elemento que queremos ver.
      * @return El elemento que esta en esa posicion.
      * @throws IllegalArgumentException Si la posicion no existe en la lista.
      */
     @Override
     public T acceder(int i) throws IllegalArgumentException {
          return accederNodo(i).elemento;
     }

     /**
      * Nos dice en que posicion de la lista se encuentra un elemento.
      *
      * @param elemento El dato que estamos buscando.
      * @return La posicion del elemento, o -1 si no lo encuentra.
      */
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

     /**
      * Nos dice cuantos elementos hay en total en la lista.
      *
      * @return El tamaño o longitud de la lista.
      */
     @Override
     public int devolverLongitud(){
          return longitud;
     }

     /**
      * Agrega un nuevo elemento hasta el final de la lista.
      *
      * @param elemento El dato que queremos agregar al final.
      * @throws IllegalArgumentException Si el elemento a agregar es nulo.
      */
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
      * Crea una copia de la lista pero con todos los elementos al reves.
      *
      * @return Una nueva lista volteada.
      */
     public ListaDoblementeLigada<T> reversa(){
          ListaDoblementeLigada<T> listaReversa = new ListaDoblementeLigada<>();
          Nodo actual = rabo;
          while (actual != null) {
               listaReversa.agregarFinal(actual.elemento);
               actual = actual.anterior;
          }
          return listaReversa;
     }

     /**
      * Busca y entrega el nodo completo que esta en cierta posicion.
      *
      * @param i La posicion del nodo que queremos sacar.
      * @return El nodo en la posicion indicada.
      * @throws IllegalArgumentException Si la posicion es menor a 0 o mayor al tamaño de la lista.
      */
     private Nodo accederNodo(int i) {
          if (i < 0 || i >= longitud) throw new IllegalArgumentException("Índice fuera de rango");
          Nodo actual = cabeza;
          for (int j = 0; j < i; j++) {
               actual = actual.siguiente;
          }
          return actual;
     }

     /**
      * Convierte toda la lista en un texto para poder leerla facilmente.
      *
      * @return Un texto con todos los elementos separados por comas y entre corchetes.
      */
     public String toString(){
          String s = "[";
          int cont = 0;
          for(T elem : this){
               if(cont == 0){
                    s = s + elem.toString();
                    cont++;
               }else{
                    s = s + ", " + elem.toString();
               }
          }
          s = s + "]";
          return s;
     }
}