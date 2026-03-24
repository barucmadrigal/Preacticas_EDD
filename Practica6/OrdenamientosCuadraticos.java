public class OrdenamientosCuadraticos {
	
	private static final int SELECTION_SORT = 1;
	
	private static final int BUBBLE_SORT = 2;
	
	private static final int INSERTION_SORT = 3;

    /**
     * Metodo que sirve para intercambiar dos elementos dentro de un arreglo proporcionando el arrelo y dos indices.
     * @param <T> El metodo podra intercambiar elementos sin importar su tipo. 
     * @param arreglo Arreglo que se proporcina para intercambiarle elementos.
     * @param i Indice inicial del que se quiere cambiar la posicion.
     * @param j Indice con el que se cambiara la posicion. 
     */
    private static <T> void intercambio(T[] arreglo, int i, int j) {
        T temporal = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temporal; 
    }

    /**
     * Metodo para localizar el indice del menor elemento dentro del arreglo.
     * @param <T> El metodo puede emcontrar el indice de cualquier elemento sin importar su tipo.
     * @param arreglo
     * @param i
     * @param j
     * @return
     */
    private static <T extends Comparable<T>> int encontrarIndiceMenor(T[] arreglo, int i, int j){
        int indiceMenor = i; 
        for(int k = i; k <= j; k++){
            if(arreglo[indiceMenor].compareTo(arreglo[k]) > 0){
                indiceMenor = k; 
            }
        }
        return indiceMenor; 
    }  

    /**
     * Merodo que implementa selectionsort.
     * @param <T>
     * @param arreglo arreglo al que se le aplicara el algoritmo de ordenamiento.
     * @param imprimir atributo para imprimir cada elemento de cada iteracion. 
     */
    public static <T extends Comparable<T>> void selectionSort(T[] arreglo, boolean imprimir) {
        for(int i = 0; i < arreglo.length; i++){
            int min = encontrarIndiceMenor(arreglo, i, arreglo.length - 1);
            intercambio(arreglo, i, min);
        }
    }

    /**
     * Metodo que implementa bubbleSort.
     * @param <T>
     * @param arreglo arreglo al que se le aplica bubbleSort.
     * @param imprimir  atributo para imprimir cada elemento de cada iteracion. 
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arreglo,boolean imprimir) {
        for(int i = 0; i < arreglo.length; i++){
            for(int j = 0; j < arreglo.length -1 -i; j++){
                if(arreglo[j].compareTo(arreglo[j + 1]) > 0){
                    intercambio(arreglo, j, j+1);
                }
            }
        }
    }

    /**
     * Metodo para implementar insertionSort.
     * @param <T>
     * @param arreglo arreglo al que se le aplicara el algoritmo de insertionSort. 
     * @param imprimir atributo para imprimir cada elemento de cada iteracion. 
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arreglo, boolean imprimir) {
        for(int i = 1; i < arreglo.length; i++){
            T aInsertar = arreglo[i];
            int j = i - 1; 
            while(j >= 0 && arreglo[j].compareTo(aInsertar) > 0){
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = aInsertar;
        }
    }

    /**
     * Metodo para elegir el algoritmo de ordenamiento.
     * @param <T>
     * @param algoritmo
     * @param arreglo   
     * @param imprimir
     */
    public static <T extends Comparable<T>> void ordenar(int algoritmo, T[] arreglo, boolean imprimir) {
        switch (algoritmo) {
            case SELECTION_SORT:
            
                selectionSort(arreglo,imprimir);
                break;
                
            case BUBBLE_SORT:
                bubbleSort(arreglo,imprimir);
                break;
            
            case INSERTION_SORT:
                insertionSort(arreglo,imprimir);
                break;
                
            default:
                System.out.println("Opción de ordenamiento no válida.");
        }
    }

    public static <T> String imprimeArreglo(T[] arreglo) {
        String s = "{";
        for (int i = 0; i < arreglo.length; i++) {
            if (i == arreglo.length - 1) {
                s = s + arreglo[i];
            } else {
                s = s + arreglo[i] + ",";
            }
        }
        s = s + "}";
        return s;
    }
}
