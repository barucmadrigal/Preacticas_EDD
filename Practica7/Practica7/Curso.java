import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que gestiona la asignación de lugares en una asignatura específica.
 * Controla el proceso de inscripción basándose en el cupo disponible y el orden de mérito.
 */
public class Curso {

    /** Nombre identificador de la asignatura. */
    private String nombreCurso;
    /** Límite máximo de inscripciones permitidas. */
    private int capacidad;
    /** Estructura de datos que almacena a los alumnos aceptados. */
    private ListaDoblementeLigada<Estudiante> inscritos;

    /**
     * Inicializa un nuevo curso con un nombre y una capacidad definida.
     *
     * @param nombreCurso Nombre de la materia.
     * @param capacidad   Número máximo de estudiantes que admite el curso.
     */
    public Curso(String nombreCurso, int capacidad) {
        this.nombreCurso = nombreCurso;
        this.capacidad = capacidad;
        this.inscritos = new ListaDoblementeLigada<>();
    }

    /**
     * Procesa una lista de aspirantes y asigna los lugares disponibles 
     * respetando el orden establecido en la lista recibida.
     *
     * @param aspirantesOrdenados Lista de estudiantes previamente organizada por prioridad.
     */
    public void inscribirAceptados(ListaDoblementeLigada<Estudiante> aspirantesOrdenados) {
        int contador = 0;
        for (Estudiante e : aspirantesOrdenados) {
            if (contador < capacidad) {
                this.inscritos.agregarFinal(e);
                contador++;
            } else {
                break;
            }
        }
    }

    /**
     * Imprime en la salida estándar el listado de los alumnos que obtuvieron
     * un lugar en el curso tras el proceso de asignación.
     */
    public void mostrarResultados() {
        System.out.println("========================================================================================");
        System.out.println("   ALUMNOS ACEPTADOS EN EL CURSO: " + nombreCurso.toUpperCase() + " (Cupo: " + capacidad + ")");
        System.out.println("========================================================================================");
        int lugar = 1;
        for (Estudiante e : inscritos) {
            System.out.println(lugar + ".- " + e.toString());
            lugar++;
        }
        System.out.println("========================================================================================");
    }

    /**
     * Punto de entrada principal del sistema de asignación.
     * Realiza la lectura del archivo fuente, ejecuta el ordenamiento y gestiona la inscripción.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        ListaDoblementeLigada<Estudiante> aspirantes = new ListaDoblementeLigada<>();
        String rutaArchivo = "Alumnos.txt"; 

        // Bloque de lectura y parseo del archivo de texto
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = br.readLine(); // Salto de encabezados
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    aspirantes.agregarFinal(new Estudiante(
                        datos[0].trim(), 
                        datos[1].trim(), 
                        Integer.parseInt(datos[2].trim()), 
                        Integer.parseInt(datos[3].trim()), 
                        Double.parseDouble(datos[4].trim()), 
                        Boolean.parseBoolean(datos[5].trim())
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la lectura del archivo: " + e.getMessage());
            return;
        }

        // Aplicación del algoritmo de ordenamiento eficiente O(n log n)
        OrdenamientosNoCuadraticos.mergeSort(aspirantes);

        // Instanciación del curso y ejecución de la lógica de negocio
        Curso cursoBeta = new Curso("Estructuras de Datos", 10);
        cursoBeta.inscribirAceptados(aspirantes);
        cursoBeta.mostrarResultados();
    }
}