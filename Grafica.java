public interface Grafica<T> extends Iterable<T> {

    public void agregarVertice(T elemento);

    public void agregarArista(T e1, T e2);
    
    public void agregarAristaPonderada(T e1, T e2, int peso);

    public ListaDoblementeLigada<T> devolverBfs(T inicio);

    public ListaDoblementeLigada<T> devolverDfs(T inicio);

    public void eliminarVertice(T elemento);

    public void eliminarArista(T e1, T e2);

    public ListaDoblementeLigada<T> devolverRutaMasCortaNoPonderada(T inicio, T fin);
    
    public ListaDoblementeLigada<T> rutaMasCortaPonderada(T inicio, T fin);
    
    public int devolverNumeroDeVertices();
    
    public int devolverNumeroDeAristas(); 
    
}
