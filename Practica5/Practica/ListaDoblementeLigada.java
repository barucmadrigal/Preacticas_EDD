import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Estructura de datos que guarda elementos en nodos conectados en dos direcciones.
 * Cada nodo sabe cual es el anterior y cual es el siguiente.
 *
 * @param <T> El tipo de dato que va a guardar la lista.
 */
public class ListaDoblementeLigada<T> implements Lista<T> {

     /**
      * Clase interna que representa cada nodo o pieza de la lista.
      */
     private class Nodo{
          /** El dato que guarda el nodo. */
          public T elemento;
          /** La conexion al nodo que sigue. */
          public Nodo siguiente;
          /** La conexion al nodo anterior. */
          public Nodo anterior;

          /**
           * Construye un nodo nuevo con el elemento dado.
           *
           * @param e El elemento que se va a guardar.
           */
          public Nodo(T e) {
               this.elemento = e;
          }
     }

     /**
      * Clase interna que nos ayuda a recorrer la lista elemento por elemento.
      */
     private class IteradorDoubleLinkedList implements Iterator<T> {

          /** El nodo por el que acabamos de pasar. */
          public Nodo anterior;
          /** El nodo que vamos a visitar a continuacion. */
          public Nodo siguiente;

          /**
           * Construye un iterador que empieza desde la cabeza de la lista.
           */
          public IteradorDoubleLinkedList() {
               siguiente = cabeza;
          }

          /**
           * Nos dice si todavia hay elementos por recorrer en la lista.
           *
           * @return true si hay mas elementos, false si ya terminamos.
           */
          @Override
          public boolean hasNext() {
               return siguiente != null;
          }

          /**
           * Nos da el siguiente elemento de la lista y avanza un paso.
           *
           * @return El elemento del nodo actual.
           */
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

     /** El primer nodo de la lista. */
     private Nodo cabeza;
     /** El ultimo nodo de la lista. */
     private Nodo rabo;
     /** La cantidad de elementos que tiene la lista en total. */
     private int longitud;

     @Override
     /**
      * Crea un iterador para poder recorrer esta lista.
      *
      * @return Un nuevo iterador.
      */
     public Iterator<T> iterator() {
          return new IteradorDoubleLinkedList();
     }

     /**
      * Construye una lista doblemente ligada completamente vacia.
      */
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

     @Override
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