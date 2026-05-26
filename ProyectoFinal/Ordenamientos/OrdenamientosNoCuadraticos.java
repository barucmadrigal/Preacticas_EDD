package Ordenamientos;

/**
 * Clase utilitaria que proporciona implementaciones de algoritmos de ordenamiento
 * con complejidad temporal no cuadrática (ej. O(n log n) u O(n + k)).
 */
public class OrdenamientosNoCuadraticos {

    /**
     * Implementación del algoritmo Counting Sort para ordenar un arreglo de enteros.
     * Su complejidad temporal esperada es O(n + k), donde n es el número de elementos y k es el valor máximo.
     * Modifica el arreglo original in-place.
     *
     * @param arreglo Arreglo de tipo Integer a ordenar.
     */
    public static void countingSort(Integer[] arreglo) {
        if (arreglo == null || arreglo.length == 0) return;
        
        int max = obtenerMax(arreglo);
        int[] conteo = new int[max + 1];
        
        for (int i = 0; i < arreglo.length; i++) {
            conteo[arreglo[i]]++;
        }
        
        int indice = 0;
        for (int j = 0; j < conteo.length; j++) {
            while (conteo[j] > 0) {
                arreglo[indice] = j;
                indice++;
                conteo[j]--;
            }
        }
    }

    /**
     * Obtiene el valor máximo contenido en un arreglo de enteros.
     * Método auxiliar requerido por el algoritmo Counting Sort para determinar el tamaño del arreglo de frecuencias.
     *
     * @param arreglo Arreglo de enteros del cual se extraerá el valor máximo.
     * @return El elemento con el valor numérico más alto dentro del arreglo.
     */
    private static int obtenerMax(Integer[] arreglo) {
        int max = arreglo[0];
        for (int i = 1; i < arreglo.length; i++) {
            if (arreglo[i] > max) {
                max = arreglo[i];
            }
        }
        return max;
    }

    /**
     * Implementación del algoritmo Quick Sort para ordenar un arreglo de elementos genéricos.
     * Establece la llamada inicial al método recursivo auxiliar.
     *
     * @param <T>     Tipo de dato genérico comparable.
     * @param arreglo Arreglo de elementos a ordenar. Se modifica in-place.
     */
    public static <T extends Comparable<T>> void quickSort(T[] arreglo) {
        if (arreglo == null || arreglo.length == 0) return;
        quickSortAux(arreglo, 0, arreglo.length - 1);
    }

    /**
     * Método recursivo auxiliar para el algoritmo Quick Sort.
     * Divide el arreglo de acuerdo con un índice pivote y ordena los sub-arreglos resultantes.
     *
     * @param <T>     Tipo de dato genérico comparable.
     * @param arreglo Arreglo sobre el cual se realizan las particiones.
     * @param inicio  Índice inferior del sub-arreglo a procesar.
     * @param fin     Índice superior del sub-arreglo a procesar.
     */
    private static <T extends Comparable<T>> void quickSortAux(T[] arreglo, int inicio, int fin) {
        if (inicio >= fin) { 
            return;
        }
        
        int indicePivote = particionar(arreglo, inicio, fin);
        quickSortAux(arreglo, inicio, indicePivote - 1);
        quickSortAux(arreglo, indicePivote + 1, fin);
    }

    /**
     * Realiza la partición de un sub-arreglo tomando como pivote el elemento en la posición final.
     * Reubica los elementos menores o iguales al pivote a su izquierda, y los mayores a su derecha.
     *
     * @param <T>     Tipo de dato genérico comparable.
     * @param arreglo Arreglo original que contiene el sub-arreglo a particionar.
     * @param inicio  Índice de inicio de la partición.
     * @param fin     Índice final de la partición y posición original del elemento pivote.
     * @return El índice definitivo donde quedó posicionado el pivote tras la partición.
     */
    private static <T extends Comparable<T>> int particionar(T[] arreglo, int inicio, int fin) {
        T pivote = arreglo[fin];
        int i = inicio - 1;
        
        for (int j = inicio; j < fin; j++) {
            if (arreglo[j].compareTo(pivote) <= 0) {
                i++;
                intercambiar(arreglo, i, j);
            }
        }
        intercambiar(arreglo, i + 1, fin);
        return i + 1;
    }

    /**
     * Intercambia la posición de dos elementos dentro de un arreglo.
     * Método de utilidad utilizado durante la fase de partición de Quick Sort.
     *
     * @param <T>     Tipo de dato genérico.
     * @param arreglo Arreglo en el cual se realizará la mutación.
     * @param i       Índice del primer elemento a intercambiar.
     * @param j       Índice del segundo elemento a intercambiar.
     */
    private static <T> void intercambiar(T[] arreglo, int i, int j) {
        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
}