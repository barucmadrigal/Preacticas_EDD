import java.util.Random;

/**
 * Clase que representa la entidad Estudiante dentro del sistema de la facultad.
 * Implementa la interfaz Comparable para establecer un orden jerárquico de prioridad 
 * basado en criterios académicos y administrativos.
 */
public class Estudiante implements Comparable<Estudiante> {
    
    /** Identificador único del alumno. */
    private String numeroCuenta;
    /** Nombre completo del estudiante. */
    private String nombre;
    /** Total de asignaturas cursadas en el periodo inmediato anterior. */
    private int materiasInscritas;
    /** Total de asignaturas con registro de aprobación en el periodo anterior. */
    private int materiasAprobadas;
    /** Valor decimal que representa el avance en el plan de estudios. */
    private double porcentajeCreditos;
    /** Estado administrativo que indica si el alumno no adeuda materias de ciclos previos. */
    private boolean regular;
    /** Valor entero aleatorio asignado en tiempo de ejecución para resolución de empates técnicos. */
    private int valorAleatorioTieBreaker;

    /**
     * Constructor de la clase Estudiante.
     * Inicializa los atributos y genera un valor aleatorio para el criterio de desempate final.
     *
     * @param numeroCuenta      Cadena con el número de cuenta.
     * @param nombre            Nombre del alumno.
     * @param materiasInscritas Cantidad de materias inscritas previamente.
     * @param materiasAprobadas Cantidad de materias aprobadas previamente.
     * @param porcentajeCreditos Avance actual de créditos (0-100).
     * @param regular           Estatus de regularidad del alumno.
     */
    public Estudiante(String numeroCuenta, String nombre, int materiasInscritas, 
                      int materiasAprobadas, double porcentajeCreditos, boolean regular) {
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.materiasInscritas = materiasInscritas;
        this.materiasAprobadas = materiasAprobadas;
        this.porcentajeCreditos = porcentajeCreditos;
        this.regular = regular;
        this.valorAleatorioTieBreaker = new Random().nextInt();
    }

    /**
     * Calcula el cociente de aprobación del estudiante.
     * Define la relación entre materias aprobadas y materias inscritas.
     *
     * @return Valor double resultante de la división; 0.0 si no hubo materias inscritas.
     */
    public double getIndiceAprobacion() {
        if (materiasInscritas == 0) return 0.0;
        return (double) materiasAprobadas / materiasInscritas;
    }

    /**
     * Define la lógica de comparación para el ordenamiento de prioridad.
     * Los criterios de evaluación son:
     * 1. Índice de aprobación (Descendente).
     * 2. Avance de créditos (Descendente).
     * 3. Estatus de regularidad (Prioriza 'true').
     * 4. Valor aleatorio (Desempate final).
     *
     * @param otro Objeto Estudiante con el cual comparar.
     * @return Entero negativo si this tiene mayor prioridad, positivo si es menor, y 0 si son idénticos.
     */
    @Override
    public int compareTo(Estudiante otro) {
        // Se ut_iliza la comparación inversa (otro vs this) para obtener orden descendente
        int cmpIndice = Double.compare(otro.getIndiceAprobacion(), this.getIndiceAprobacion());
        if (cmpIndice != 0) return cmpIndice;

        int cmpCreditos = Double.compare(otro.porcentajeCreditos, this.porcentajeCreditos);
        if (cmpCreditos != 0) return cmpCreditos;

        int cmpRegular = Boolean.compare(otro.regular, this.regular);
        if (cmpRegular != 0) return cmpRegular;

        return Integer.compare(otro.valorAleatorioTieBreaker, this.valorAleatorioTieBreaker);
    }

    /**
     * Devuelve una representación en cadena de los datos relevantes del estudiante.
     *
     * @return String con formato de los atributos del alumno.
     */
    @Override
    public String toString() {
        return String.format("Cuenta: %s | Nombre: %-20s | Índice: %.2f | Créditos: %.2f%% | Regular: %b",
                numeroCuenta, nombre, getIndiceAprobacion(), porcentajeCreditos, regular);
    }
}