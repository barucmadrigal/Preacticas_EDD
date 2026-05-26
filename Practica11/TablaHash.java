import java.util.Iterator;
/**
 * Clase abstracta que define la estructura básica de una TablaHash.
 * @param <K> Tipo de las llaves.
 * @param <V> Tipo de los valores asociados a las llaves.
 */
public abstract class TablaHash<K, V> implements Iterable<V> {

    protected class Entrada {

        public K llave;

        public V valor;

        public Entrada(K llave, V valor) {
            this.llave = llave;
            this.valor = valor;
        }

        @Override
        public String toString(){
            return "(" + this.llave + " , " + this.valor + ")";
        }
    }

    /** Máxima carga permitida antes de redimensionar. */
    protected static final double MAXIMA_CARGA = 0.72;

    /** Capacidad mínima inicial (potencia de 2). */
    protected static final int MINIMA_CAPACIDAD = 64;

    /** Dispersor utilizado por la tabla. */
    protected Dispersor<K> dispersor;

    /** Número actual de elementos almacenados. */
    protected int elementos;


    protected int calcularNuevoTamanio(int x) {
        x = Math.max(x, MINIMA_CAPACIDAD);
        int log2 = (int)(Math.log(x) / Math.log(2));
        return (int)(Math.pow(2, log2 + 1));
    }
    
    /**
     * Devuelve la carga actual de la TablaHash, que es el número de elementos almacenados dividido entre la capacidad de la TablaHash.
     * @return la carga actual de la TablaHash.
     */
    public abstract double devolverCarga();

    /**
     * Agrega una nueva entrada a la TablaHash con la llave y el valor proporcionados. Si la llave ya existe, se actualiza su valor.
      * @param llave la llave de la entrada a agregar o actualizar.
     */
    public abstract void agregar(K llave, V valor);

    /**
     * Elimina la entrada asociada a la llave proporcionada de la TablaHash. Si la llave no existe, no se realiza ninguna acción.
      * @param llave la llave de la entrada a eliminar.
     */
    public abstract void eliminar(K llave);

    /**
     * Obtiene el valor asociado a la llave proporcionada. Si la llave no existe, se lanza una excepción.
      * @param llave la llave de la entrada cuyo valor se desea obtener.
     * @return el valor asociado a la llave proporcionada.
     */
    public abstract V obtenerValorLlave(K llave);

    /**
     * Busca si la llave proporcionada existe en la TablaHash. Devuelve true si la llave existe, false en caso contrario.
      * @param llave la llave a buscar en la TablaHash.
     * @return true si la llave existe en la TablaHash, false en caso contrario.
     */
    public abstract boolean buscar(K llave);

    /* Redimensiona el arreglo interno de la TablaHash cuando la carga supera el límite máximo permitido. Este método debe ser implementado por las subclases para definir cómo se redimensiona el arreglo y cómo se reubican las entradas existentes en la nueva estructura. */

    protected abstract void redimencionaArreglo();

    /**
     * Devuelve un iterador que recorre las llaves almacenadas en la TablaHash. Este método debe ser implementado por las subclases para definir cómo se itera sobre las llaves de la TablaHash.
      * @return un iterador que recorre las llaves almacenadas en la TablaHash.
     */
    public abstract Iterator<K> iteradorLlaves();

    @Override
    public abstract Iterator<V> iterator();
}
