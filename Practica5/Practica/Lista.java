
public interface Lista<T> extends Coleccion<T>{

    public void eliminar(int indice);

    public T acceder(int indice);

    public int devolverIndiceElemento(T elemento);

    public int devolverLongitud();

}