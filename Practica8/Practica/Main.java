import Picola.Cola;
import Picola.PiCoLa;
import Picola.Pila;
import java.util.Scanner;

/**
 * Clase principal que contiene el método main para probar las estructuras Pila y Cola.
 * Permite al usuario interactuar mediante la consola para agregar, sacar o mirar elementos.
 */
public class Main {

    /**
     * Método principal que inicia la ejecución del programa.
     * Cumple con los requisitos de preguntar tipo de estructura, tipo de dato,
     * y mantener un ciclo de operaciones hasta que el usuario decida salir.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- INCISO B: Solicitar estructura y tipo de dato ---
        System.out.println("BIENVENIDO AL PROGRAMA DE PILAS Y COLAS");
        System.out.println("1. Cola");
        System.out.println("2. Pila");
        System.out.print("¿Qué estructura de datos desea utilizar?: ");
        int opcionEstructura = scanner.nextInt();

        System.out.println("\n1. Enteros");
        System.out.println("2. Cadenas de texto");
        System.out.print("¿Qué tipo de datos va a almacenar?: ");
        int opcionDato = scanner.nextInt();

        // Variable para controlar que el programa corra hasta que el usuario decida salir (Inciso d)
        boolean continuarPrograma = true;

        // Dividimos el programa en dos bloques principales: Enteros y Cadenas.
        // Esto es necesario por cómo funcionan los tipos genéricos (<Integer> o <String>) en Java.
        
        if (opcionDato == 1) {
            /* Logica para enteros */
             
            // Instanciamos la estructura dependiendo de lo que eligió el usuario
            PiCoLa<Integer> estructuraEnteros;
            if (opcionEstructura == 1) {
                estructuraEnteros = new Cola<>();
                System.out.println("\n--- Has creado una Cola de Enteros ---");
            } else {
                estructuraEnteros = new Pila<>();
                System.out.println("\n--- Has creado una Pila de Enteros ---");
            }

            // Bucle que se repetirá hasta que elija la opción 5 (Salir)
            while (continuarPrograma) {
                mostrarMenuOperaciones();
                int operacion = scanner.nextInt();

                // Inciso C: Ejecutar la operación solicitada
                switch (operacion) {
                    case 1:
                        System.out.print("Ingrese el número a meter: ");
                        int numero = scanner.nextInt();
                        estructuraEnteros.meter(numero);
                        System.out.println(">> Resultado: Se agregó el número " + numero);
                        break;
                    case 2:
                        System.out.println(">> Resultado: Elemento sacado -> " + estructuraEnteros.sacar());
                        break;
                    case 3:
                        System.out.println(">> Resultado: Elemento en la puerta/cima -> " + estructuraEnteros.mira());
                        break;
                    case 4:
                        System.out.println(">> Resultado: El tamaño actual es " + estructuraEnteros.devolverTamanio());
                        break;
                    case 5:
                        System.out.println("Finalizando el programa... ¡Adiós!");
                        continuarPrograma = false; // Rompe el ciclo
                        break;
                    default:
                        System.out.println("Operación no válida. Intente de nuevo.");
                }

                // Inciso C: Mostrar el estado final de la estructura después de la operación
                // Usamos toString(). Asegúrate de que tus clases Cola y Pila tengan programado el método toString().
                if (continuarPrograma) {
                    System.out.println(">> Estado actual de la estructura: " + estructuraEnteros.toString());
                }
            }

        } else if (opcionDato == 2) {
            /* Logica para cadenas de texto */
             
            PiCoLa<String> estructuraCadenas;
            if (opcionEstructura == 1) {
                estructuraCadenas = new Cola<>();
                System.out.println("\n--- Has creado una Cola de Cadenas ---");
            } else {
                estructuraCadenas = new Pila<>();
                System.out.println("\n--- Has creado una Pila de Cadenas ---");
            }

            while (continuarPrograma) {
                mostrarMenuOperaciones();
                int operacion = scanner.nextInt();

                switch (operacion) {
                    case 1:
                        System.out.print("Ingrese el texto a meter: ");
                        String texto = scanner.next();
                        estructuraCadenas.meter(texto);
                        System.out.println(">> Resultado: Se agregó '" + texto + "'");
                        break;
                    case 2:
                        System.out.println(">> Resultado: Cadena sacada -> " + estructuraCadenas.sacar());
                        break;
                    case 3:
                        System.out.println(">> Resultado: Cadena en la puerta/cima -> " + estructuraCadenas.mira());
                        break;
                    case 4:
                        System.out.println(">> Resultado: El tamaño actual es " + estructuraCadenas.devolverTamanio());
                        break;
                    case 5:
                        System.out.println("Finalizando el programa... ¡Adiós!");
                        continuarPrograma = false;
                        break;
                    default:
                        System.out.println("Operación no válida. Intente de nuevo.");
                }

                if (continuarPrograma) {
                    System.out.println(">> Estado actual de la estructura: " + estructuraCadenas.toString());
                }
            }
        } else {
            System.out.println("Opción no válida. Por favor, reinicie el programa.");
        }
        
        scanner.close(); // Siempre es buena práctica cerrar el scanner al final
    }

    /**
     * Método auxiliar (helper) para imprimir el menú de operaciones en pantalla.
     * Se separó aquí abajo simplemente para que el código de arriba (main) 
     * se vea más limpio y no repetir estos prints tantas veces.
     */
    private static void mostrarMenuOperaciones() {
        System.out.println("\n--- MENÚ DE OPERACIONES ---");
        System.out.println("1. Meter elemento");
        System.out.println("2. Sacar elemento");
        System.out.println("3. Mirar elemento (sin sacarlo)");
        System.out.println("4. Ver tamaño");
        System.out.println("5. Salir");
        System.out.print("¿Qué desea hacer?: ");
    }
}