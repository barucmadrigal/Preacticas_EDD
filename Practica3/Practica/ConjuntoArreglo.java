import java.util.Iterator;
import java.util.NoSuchElementException;
import jdk.jshell.execution.Util;

public class ConjuntoArreglo<T> extends Conjunto<T>{

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indice = 0;

            @Override
            public boolean hasNext() {
                return indice < ConjuntoArreglo.this.elementos.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elementos[indice++];
            }
        };
    }

    private T[] elementos;

    public ConjuntoArreglo() {
        this.elementos = (T[]) new Object[0];
    }

    public ConjuntoArreglo(T[] elementos) {
        if (elementos == null) {
            this.elementos = (T[]) new Object[0];
        } else if (UtilArreglos.tieneDuplicados(elementos)) {
            throw new IllegalArgumentException("El arreglo contiene elementos duplicados.");
        } else if (UtilArreglos.contieneNull(elementos)) {
            throw new IllegalArgumentException("El arreglo contiene elementos nulos.");
        } else {
            this.elementos = UtilArreglos.copiaArregloGenerico(elementos);
        }
    }

    @Override
    // Algoritmo 2.1: pertenece
    public boolean pertenece(T elemento) {
        //Para cada auxiliar en c.elementos
        for (T aux : this.elementos) {
            if (aux.equals(elemento)) { //Si auxiliar es igual a elemento es verdadero
                return true; 
            }
        }
        return false; // 4. Devolver falso
    }

    // Algoritmo 2.2: agregarElemento
    public void agregarElemento(T elemento) {
        if (elemento == null) return; // Validación para evitar agregar null

        if (pertenece(elemento)) { // 1. Si e pertenece a c devolver c sin cambios
            return; 
        }

        int n = this.elementos.length; // 3. Sea n <- longitud

        // 4. Crear arreglo de longitud n + 1 usando la función auxiliar crearArregloGenerico
        T[] elementosNuevo = UtilArreglos.crearArregloGenerico(this.elementos, n + 1);

        for (int i = 0; i < n; i++) { // 5. Para i desde 0 hasta n - 1 hacer
            elementosNuevo[i] = this.elementos[i]; // 6. elementosNuevo[i] <- c.elementos[i]
        }

        elementosNuevo[n] = elemento; // 7. Agregar el elemento e en la última posición
        this.elementos = elementosNuevo; // 8. Devolver c
    }

    // Algoritmo 2.3: eliminarElemento (Basado en la descripción)
    public void eliminarElemento(T elemento) {
        if (!pertenece(elemento)) { // Si no pertenece
            return; // Devolver c sin cambios
        }

        int n = obtenerCardinalidad();
        // Crear arreglo de longitud n - 1
        T[] elementosNuevo = UtilArreglos.crearArregloGenerico(this.elementos, n - 1);

        int j = 0;
        for (T aux : this.elementos) {
            if (!aux.equals(elemento)) { // Copiar si no es el elemento a eliminar
                elementosNuevo[j++] = aux;
            }
        }
        this.elementos = elementosNuevo;
    }

    // Algoritmo 2.4: contieneConjunto
    public boolean contieneConjunto(Conjunto<T> c2) {
        for (T aux : c2) { // 1. Para cada aux en c2
            if (!this.pertenece(aux)) { // 2. Si aux no pertenece a c devolver falso
                return false; // 3. Devolver falso
            }
        }
        return true; // 4. Devolver verdadero
    }

    // Algoritmo 2.5: obtenerCardinalidad
    public int obtenerCardinalidad() {
        return this.elementos.length; // 1. Devolver la longitud del arreglo c.elementos
    }

    // Algoritmo 2.6: union
    public Conjunto<T> union(Conjunto<T> c2) {
        ConjuntoArreglo<T> c3 = new ConjuntoArreglo<>(); // 1. Crear c3 como un nuevo conjunto vacío
        
        for (T aux : this.elementos) { // 2. Para cada auxiliar  en c
            c3.agregarElemento(aux); // 3. c3 <- agregarElemento
        }

        for (T aux2 : c2) { // 4. Para cada auxiliar 2 en c2
            c3.agregarElemento(aux2); // 5. c3 <- agregarElemento (evita duplicados)
        }
        return c3; // 6. Devolver c3
    }

    // Algoritmo 2.7: interseccion (Basado en la descripción)
    public Conjunto<T> interseccion(Conjunto<T> c2) {
        ConjuntoArreglo<T> nuevo = new ConjuntoArreglo<>();
        for (T aux : this.elementos) { // Recorre el conjunto c (this)
            if (c2.pertenece(aux)) { // Verifica si pertenece al conjunto c2
                nuevo.agregarElemento(aux); // Agrega si pertenece a ambos
            }
        }
        return nuevo;
    }

    // Algoritmo 2.8: iguales (Basado en la descripción)
    public boolean iguales(Conjunto<T> c2) {
        // Si c contenido en c2 Y c2 contenido en c
        // Nota: Como Conjunto<T> es la interfaz, usamos la lógica de contención mutua
        return this.contieneConjunto(c2) && c2.contieneConjunto(this);
    }
}
