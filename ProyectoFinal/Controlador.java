import Colecciones.Listas.*;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    /**
     * Arranca la aplicación del metro y controla el menú principal.
     */
    public void iniciar() {
        // 1. Cargamos la red y los cierres desde el inicio
        modelo.cargarRed("lineas_metro.txt");
        modelo.aplicarCierreEstaciones("estaciones_cerradas.txt");
        modelo.aplicarCierreTramos("tramos_cerrados.txt");

        int opcion = 0;

        while (opcion != 5) {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case 1: // Ruta corta por número de estaciones (BFS)
                    procesarRutaNoPonderada();
                    break;

                case 2: // Ruta rápida por tiempo (Dijkstra)
                    procesarRutaPonderada();
                    break;

                case 3: // Ver estado de cierres
                    mostrarCierres();
                    break;

                case 4: // Ver estaciones operativas
                    vista.mostrarEstacionesOperativas(modelo.obtenerEstacionesActivas());
                    break;

                case 5:
                    System.out.println("¡Gracias por usar el simulador del Metro! ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private void procesarRutaNoPonderada() {
        String origen = vista.pedirEstacion("Introduce la estación de origen: ");
        String destino = vista.pedirEstacion("Introduce la estación de destino: ");
        ListaDoblementeLigada<String> ruta = modelo.obtenerRutaNoPonderada(origen, destino);
        vista.mostrarRuta(ruta);
    }

    private void procesarRutaPonderada() {
        String origen = vista.pedirEstacion("Introduce la estación de origen: ");
        String destino = vista.pedirEstacion("Introduce la estación de destino: ");
        
        ListaDoblementeLigada<String> ruta = modelo.obtenerRutaPonderada(origen, destino);
        vista.mostrarRuta(ruta);
    }

    private void mostrarCierres() {
        System.out.println("\nCONSULTA DE AFECTACIONES EN LA RED:");
        System.out.println("Estaciones fuera de servicio:");
        System.out.println(modelo.obtenerEstacionesCerradas());
        System.out.println("Tramos cerrados:");
        System.out.println(modelo.obtenerTramosCerrados());
    }
}