import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorTorneo {

public static void main(String[] args) {
        List<Participante> competidores = leerParticipantes("participantes.txt");

        if (competidores.isEmpty()) {
            System.out.println("No se encontraron competidores en el archivo.");
            return;
        }

        // Ordenar competidores de mayor a menor índice (y cinta)
        Collections.sort(competidores);

        // ====================================================================
        // NUEVA MODIFICACIÓN: Rellenar hasta la siguiente potencia de 2 (32)
        // ====================================================================
        int n = competidores.size();
        int potenciaDe2 = 1;
        while (potenciaDe2 < n) {
            potenciaDe2 *= 2;
        }
        
        int lugaresVacios = potenciaDe2 - n; // Para 24, faltan 8 lugares para 32
        
        // Agregamos participantes "Fantasma" para que el árbol quede parejo
        for (int i = 0; i < lugaresVacios; i++) {
            // Se asume que "Kaimua" o alguna otra cinta básica existe en tu Enum
            Cinta cintaVacia = Cinta.obtenerCinta("Kaimua");
            competidores.add(new Participante("--- Pase Directo ---", 0, 0, cintaVacia));
        }
        // ====================================================================

        // Crear la gráfica del torneo
        ArbolBinarioCompleto<Object> arbolTorneo = new ArbolBinarioCompleto<>();

        // 1. Agregar N-1 nodos internos marcados como "Pendiente"
        // Ahora numCompetidores será exactamente 32, por lo que habrá 31 "Pendientes"
        int numCompetidores = competidores.size();
        for (int i = 0; i < numCompetidores - 1; i++) {
            arbolTorneo.agregar("Pendiente");
        }

        // 2. Agregar las hojas (Los participantes reales + los Pases Directos)
        for (Participante p : competidores) {
            arbolTorneo.agregar(p);
        }

        // 3. Imprimir el árbol resultante en la terminal
        System.out.println("====== GRÁFICA INICIAL DEL TORNEO ======\n");
        System.out.println(arbolTorneo.toString());
    }
    /**
     * Método auxiliar para leer el archivo de texto y convertirlo en objetos Participante
     */
    public static List<Participante> leerParticipantes(String rutaArchivo) {
        List<Participante> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Limpiar espacios extra y evitar líneas vacías
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                // El formato esperado es: Nombre,M:5,P:10,C:Kaimua
                String[] partes = linea.split(",");
                
                if (partes.length == 4) {
                    String nombre = partes[0].trim();
                    
                    // Extraer los valores numéricos y la cinta separando por ":"
                    int medallas = Integer.parseInt(partes[1].split(":")[1].trim());
                    int participaciones = Integer.parseInt(partes[2].split(":")[1].trim());
                    String nombreCinta = partes[3].split(":")[1].trim();
                    
                    // Obtener el Enum correspondiente a la cinta
                    Cinta cinta = Cinta.obtenerCinta(nombreCinta);

                    // Crear y añadir el participante a la lista
                    lista.add(new Participante(nombre, medallas, participaciones, cinta));
                } else {
                    System.err.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error al intentar leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir un número en el archivo de texto: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error con los datos leídos: " + e.getMessage());
        }

        return lista;
    }
}