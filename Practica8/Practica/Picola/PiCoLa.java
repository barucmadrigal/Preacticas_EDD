package Picola;

public interface PiCoLa<T> {
    
    public void meter(T elemento);   
    
    public T sacar();                
    
    public T mira();            
    
    public boolean estaVacia();
    
    public int devolverTamanio();

    @Override
    public boolean equals(Object obj);

    @Override
    public String toString();
}