import java.util.Random;
import java.util.Scanner;

public class MainOrdsCuadraticos {

    private static final int NUMERO_MAXIMO = 100;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Tamaño del arreglo: ");
            int tam = scanner.nextInt();
            
            Integer[] arreglo = generarArreglo(tam);
            
            int algoritmo = 0;
            
            while (algoritmo < 1 || algoritmo > 3) {
                System.out.println("Elige el algoritmo que quieres probar: ");
                System.out.println("1: selectionSort");
                System.out.println("2: bubbleSort");
                System.out.println("3: insertionSort");
                
                algoritmo = scanner.nextInt();
                
                if (algoritmo < 1 || algoritmo > 3) {
                    System.out.println("Entrada inválida");
                }
            }
            
            System.out.print("¿Deseas imprimir los pasos del algoritmo? (true/false): ");
            boolean imprimir = scanner.nextBoolean();
            
            System.out.println("\nArreglo original: " +
                    OrdenamientosCuadraticos.imprimeArreglo(arreglo));
            
            OrdenamientosCuadraticos.ordenar(algoritmo, arreglo, imprimir);
            
            System.out.println("\nArreglo ordenado: " +
                    OrdenamientosCuadraticos.imprimeArreglo(arreglo));
        }
    }

    public static Integer[] generarArreglo(int tam) {

        Random random = new Random();
        Integer[] arreglo = new Integer[tam];

        for (int i = 0; i < tam; i++) {
            arreglo[i] = random.nextInt(NUMERO_MAXIMO);
        }

        return arreglo;
    }
}
