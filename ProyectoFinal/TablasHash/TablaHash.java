package TablasHash;
import java.lang.reflect.Array;
import java.util.Iterator;

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

    public abstract double devolverCarga();

    public abstract void agregar(K llave, V valor);

    public abstract void eliminar(K llave);

    public abstract V obtenerValorLlave(K llave);

    public abstract boolean buscar(K llave);

    protected abstract void redimencionaArreglo();

    public abstract Iterator<K> iteradorLlaves();

    @Override
    public abstract Iterator<V> iterator();
}
