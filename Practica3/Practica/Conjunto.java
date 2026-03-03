public abstract class Conjunto<T> implements Iterable<T>{
    
    public abstract boolean pertenece(T elemento);

    public abstract void agregarElemento(T elemento);
    
    public abstract void eliminarElemento(T elemento);

    public abstract boolean contieneConjunto(Conjunto<T> c);

    public abstract Conjunto<T> union(Conjunto<T> c);

    public abstract Conjunto<T> interseccion(Conjunto<T> c);

    public abstract boolean iguales(Conjunto<T> c);

    public abstract int obtenerCardinalidad();

    @Override
    public String toString() {
        if (this.obtenerCardinalidad() == 0){
            return "{}";
        }
        String resultado = "{";
        
        //Se iteran los elementos de un conjunto sin importar como se defina el iterador en cada implementación de la interfaz
        int i = 0;
        for (T e : this) { 
            resultado += e.toString();
            if (i < this.obtenerCardinalidad() - 1) {
                resultado += ", ";
            }
            i++;
        }
        resultado += "}";
        return resultado;
    } 
}
