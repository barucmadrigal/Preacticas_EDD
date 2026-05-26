import Colecciones.Listas.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Modelo {
    // Esta es la gráfica limpia con la red completa del Metro
    private GraficaListaAdyacencia<String> redCompleta;
    // Esta es la gráfica auxiliar donde aplicaremos los cierres
    private GraficaListaAdyacencia<String> redActiva;

    public Modelo() {
        this.redCompleta = new GraficaListaAdyacencia<>();
        this.redActiva = new GraficaListaAdyacencia<>();
    }

    /**
     * Lee el archivo de líneas y construye la gráfica base.
     */
    public void cargarRed(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Cada línea viene como: Origen,Destino,Peso
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String origen = datos[0].trim();
                    String destino = datos[1].trim();
                    int peso = Integer.parseInt(datos[2].trim());

                    // Aquí va la lógica de inserción en redCompleta
                    redCompleta.agregarVertice(origen);
                    redCompleta.agregarVertice(destino);
                    redCompleta.agregarAristaPonderada(origen, destino, peso);
                }
            }
            // Una vez cargada la red completa, inicializamos la red activa como una copia
            this.redActiva = this.redCompleta.clonar();
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de la red: " + e.getMessage());
        }
    }

    /**
     * Lee el archivo de estaciones cerradas y las elimina de la red activa.
     */
    public void aplicarCierreEstaciones(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String estacionCerrada = linea.trim();
                if (!estacionCerrada.isEmpty()) {
                    // 🛠️ Aplicamos tu método sobre la red de trabajo
                    redActiva.eliminarVertice(estacionCerrada);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer estaciones cerradas: " + e.getMessage());
        }
    }

    /**
     * Lee el archivo de tramos cerrados y quita esas conexiones de la red activa.
     */
    public void aplicarCierreTramos(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    String origen = datos[0].trim();
                    String destino = datos[1].trim();
                    redActiva.eliminarArista(origen, destino);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer tramos cerrados: " + e.getMessage());
        }
    }

    public ListaDoblementeLigada<String> obtenerRutaPonderada(String origen, String destino) {
        return redActiva.rutaMasCortaPonderada(origen, destino);
    }

    /**
     * Busca la ruta con menor número de estaciones usando el algoritmo BFS
     * sobre la red activa con cierres.
     */
    public ListaDoblementeLigada<String> obtenerRutaNoPonderada(String origen, String destino) {
        return redActiva.devolverRutaMasCortaNoPonderada(origen, destino);
    }

    /**
     * Devuelve una lista con las estaciones que fueron eliminadas de la red activa.
     */
    public ListaDoblementeLigada<String> obtenerEstacionesCerradas() {
        ListaDoblementeLigada<String> cerradas = new ListaDoblementeLigada<>();
        // Comparamos la red completa contra la activa para saber cuáles se quitaron
        for (String estacion : redCompleta) {
            if (!redActiva.buscarVertice(estacion)) {
                cerradas.agregar(estacion);
            }
        }
        return cerradas;
    }

    /**
     * Devuelve una lista con los nombres de todas las estaciones 
     * que siguen operativas en la red de trabajo.
     */
    public ListaDoblementeLigada<String> obtenerEstacionesActivas() {
        ListaDoblementeLigada<String> activas = new ListaDoblementeLigada<>();
        for (String estacion : redActiva) {
            activas.agregar(estacion);
        }
        return activas;
    }

    /**
     * Identifica cuáles tramos (aristas) fueron eliminados 
     * comparando la red completa contra la red activa.
     */
    public ListaDoblementeLigada<String> obtenerTramosCerrados() {
        ListaDoblementeLigada<String> tramosCerrados = new ListaDoblementeLigada<>();
        
        // Recorremos todas las estaciones de la red completa
        for (String origen : redCompleta) {
            // Solo revisamos si la estación de origen aún existe en la red activa
            if (redActiva.buscarVertice(origen)) {
                for (String destino : redCompleta) {
                    // Si eran vecinas originalmente, pero ya no lo son en la red activa...
                    if (redCompleta.sonVecinos(origen, destino) && !redActiva.sonVecinos(origen, destino)) {
                        String tramo = origen + " <-> " + destino;
                        // Evitamos agregar el tramo duplicado (A-B y B-A)
                        if (!tramosCerrados.buscar(destino + " <-> " + origen)) {
                            tramosCerrados.agregar(tramo);
                        }
                    }
                }
            }
        }
        return tramosCerrados;
    }
    
}