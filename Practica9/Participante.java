public class Participante implements Comparable<Participante> {
    private String nombre;
    private int medallas;
    private int participaciones;
    private Cinta cinta;

    public Participante(String nombre, int medallas, int participaciones, Cinta cinta) {
        this.nombre = nombre;
        this.medallas = medallas;
        this.participaciones = participaciones;
        this.cinta = cinta;
    }

    public String getNombre() {
        return nombre;
    }

    public double getIndiceVictoria() {
        return (double) medallas / participaciones;
    }

    @Override
    public int compareTo(Participante otro) {
        // Ordenamos de mayor a menor (descendente) por índice de victoria
        int comparacionIndice = Double.compare(otro.getIndiceVictoria(), this.getIndiceVictoria());
        
        if (comparacionIndice != 0) {
            return comparacionIndice;
        }
        
        // Criterio de desempate: Nivel de la cinta (descendente)
        return Integer.compare(otro.cinta.getNivel(), this.cinta.getNivel());
    }

    @Override
    public String toString() {
        // Solo retornamos el nombre para que el árbol no se imprima demasiado ancho
        return nombre;
    }
}