/**
 * Esta clase representa a un luchador o alumno que participa en el torneo. //retomada de la practica 2 
 * Guarda su nombre, su experiencia (medallas y participaciones) y la cinta que posee.
 */
public class Participante {

    /** Nombre completo del alumno */
    public String nombre; 
    
    /** Cantidad de medallas ganadas hasta ahora */
    public int numeroDeMedallas; 
    
    /** Total de veces que ha competido en torneos */
    public int numeroDeParticipaciones; 
    
    /** El objeto Cinta que representa su rango y color */
    public Cinta cinta;

    /**
     * Crea un nuevo Participante desde cero.
     * Al crearlo, se genera automáticamente su objeto Cinta usando el nombre recibido.
     * @param nombre Nombre del alumno.
     * @param m Cantidad de medallas iniciales.
     * @param p Cantidad de participaciones iniciales.
     * @param NombreCinta El nombre del rango (ej: "Moli").
     */
    public Participante(String nombre, int m, int p, String NombreCinta) {
        this.nombre = nombre; 
        this.numeroDeMedallas = m;
        this.numeroDeParticipaciones = p;
        this.cinta = new Cinta(NombreCinta);
    }

    /** @return El nombre del alumno. */
    public String getNombre() {
        return nombre;  
    }

    /** @return Cuántas medallas tiene el alumno. */
    public int getnumeroDeMedallas() {
        return numeroDeMedallas; 
    }

    /** @return En cuántos torneos ha estado. */
    public int getnumeroDeParticipaciones() {
        return numeroDeParticipaciones;
    }

    /** @return El objeto Cinta completo del alumno. */
    public Cinta getCinta() {
        return cinta; 
    }

    /**
     * Calcula la efectividad del alumno en sus combates.
     * Importante: Se asegura de no dividir por cero si el alumno nunca ha participado.
     * @return Un número decimal que representa el promedio de medallas por torneo.
     */
    public double indiceDeVictoria() {
        if (this.numeroDeParticipaciones == 0) {
            return 0.0; 
        }
        // Nota: se recomienda (double) para no perder decimales en la división
        return (double) this.numeroDeMedallas / this.numeroDeParticipaciones;
    }

    /**
     * Muestra toda la información del alumno en una sola línea de texto.
     * @return Un texto con nombre, medallas, participaciones y los datos de su cinta.
     */
    @Override
    public String toString() {
        return "Participante: " + this.nombre + " ,m: " + this.numeroDeMedallas + " ,p: " + this.numeroDeParticipaciones + " ,c: " + this.cinta;
    }
}