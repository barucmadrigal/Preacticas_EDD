package Mains;
import Colecciones.Arboles.ArbolBinarioCompleto;
public class MainArbolCompleto {
    public static void main(String[] args) {
        ArbolBinarioCompleto<Integer> arbol = new ArbolBinarioCompleto<>();

        //insertar elementos
        for (int i = 1; i <= 10; i++) {
            arbol.agregar(i);
        }

        System.out.println("recorrido en amplitud después de insertar los elementos");
        System.out.println(arbol.toString());

        //probando buscar 
        System.out.println("\nel árbol tiene como elemento al 5? " + arbol.buscar(5));
        System.out.println("el árbol tiene como elemento al 11? " + arbol.buscar(11));

        //probando eliminar
        System.out.println("\neliminado al 4");
        arbol.eliminar(4);
        System.out.println("recorrido después de eliminar al 4");
        System.out.println(arbol.devolverRecorrido());
    }

}
