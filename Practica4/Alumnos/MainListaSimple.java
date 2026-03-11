public class MainListaSimple {
    public static void main(String[] args) {
        ListaLigadaSimple<Integer> lista = new ListaLigadaSimple<>();

        // Insertar elementos en la lista
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        // Imprimir los elementos de la lista
        System.out.println("Elementos de la lista:");
        System.out.println(lista);
        System.out.println();

        System.out.println("El índice del elemento 2 es: " + lista.devolverIndiceElemento(1));
        System.out.println();
        
        System.out.println("Está 3 en la lista : "+ lista.buscar(3));
        System.out.println("El accedido es: "+ lista.acceder(1));
        System.out.println();

        System.out.println("La longitud es: "+ lista.devolverLongitud());
        lista.eliminar(2);

        System.out.println("Elementos de la lista:");
        
        System.out.println(lista);
        
        System.out.println();

        System.out.println("La longitud es: "+ lista.devolverLongitud());
        System.out.println("El accedido es: "+ lista.acceder(1));
        System.out.println();

        Integer x = 3;
        lista.eliminar(x);
        System.out.println(lista);
    }
}