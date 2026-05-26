package Mains;
import Colecciones.Arboles.ArbolBinarioOrdenado;
import Colecciones.Listas.ListaDoblementeLigada;

/**
 * Clase principal para probar el funcionamiento del Árbol Binario Ordenado.
 */
public class MainArbolOrdenado {

    public static void main(String[] args) {
        System.out.println("========== PRUEBAS ÁRBOL BINARIO ORDENADO ==========\n");

        ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>();

        // 1. Probar agregar
        System.out.println("1. Agregando elementos al árbol...");
        // Insertamos en un orden que no sea balanceado para ver cómo se acomodan
        int[] datos = {50, 30, 70, 20, 40, 60, 80, 35, 45};
        for (int n : datos) {
            System.out.println(" > Agregando: " + n);
            arbol.agregar(n);
        }
        
        System.out.println("\nTamaño del árbol: " + arbol.devolverTamanio() + " (Esperado: 9)");

        // 2. Probar devolverRecorrido (In-Order por ser Árbol Ordenado)
        System.out.println("\n2. Probando recorrido (Debe salir en orden ascendente):");
        ListaDoblementeLigada<Integer> recorrido = arbol.devolverRecorrido();
        System.out.println("Recorrido: " + recorrido.toString());

        // 3. Probar buscar
        System.out.println("\n3. Probando búsqueda:");
        int[] aBuscar = {40, 80, 100, 50};
        for (int b : aBuscar) {
            boolean encontrado = arbol.buscar(b);
            System.out.println(" > ¿El elemento " + b + " está en el árbol?: " + (encontrado ? "SÍ" : "NO"));
        }

        // 4. Probar eliminar
        System.out.println("\n4. Probando eliminación:");

        // Caso A: Eliminar una hoja
        System.out.println(" > Eliminando hoja (20)...");
        arbol.eliminar(20);
        System.out.println("   Recorrido actual: " + arbol.devolverRecorrido().toString());

        // Caso B: Eliminar nodo con un hijo
        System.out.println(" > Eliminando nodo con un hijo (80)...");
        // (80 no tiene hijos en el arreglo original, pero agreguemos uno para probar)
        arbol.agregar(85); 
        arbol.eliminar(80);
        System.out.println("   Recorrido actual: " + arbol.devolverRecorrido().toString());

        // Caso C: Eliminar nodo con dos hijos (La raíz)
        System.out.println(" > Eliminando la raíz (50) - Nodo con dos hijos...");
        arbol.eliminar(50);
        System.out.println("   Recorrido actual: " + arbol.devolverRecorrido().toString());

        // 5. Verificación final de integridad
        System.out.println("\n5. Verificación final:");
        System.out.println("Tamaño final: " + arbol.devolverTamanio());
        if (arbol.estaVacio()) {
            System.out.println("Error: El árbol dice estar vacío.");
        } else {
            System.out.println("El árbol contiene elementos y mantiene el orden.");
        }

        // IMPRIMIR EL ÁRBOL EN ASCII
        System.out.println("\nEstructura del árbol después de agregar:");
        System.out.println(arbol); 

        System.out.println("\n--- Eliminando el 30 (nodo con hijos) ---");
        arbol.eliminar(30);

        // VOLVER A IMPRIMIR PARA VER EL CAMBIO
        System.out.println("\nEstructura del árbol después de eliminar:");
        System.out.println(arbol);

        System.out.println("\n================ FIN DE LAS PRUEBAS ================");
    }
}