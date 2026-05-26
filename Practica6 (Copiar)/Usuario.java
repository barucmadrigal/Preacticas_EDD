/**
 * Representa un usuario en la fila virtual para la compra de boletos.
 * Implementa la interfaz Comparable para definir prioridades basadas en
 * el nivel de membresía y el orden de llegada.
 */
public class Usuario implements Comparable<Usuario> {
    private String nombre;
    private String nivel;
    private int llegada;

    /**
     * Constructor de la clase Usuario.
     * @param nombre Nombre completo del usuario.
     * @param nivel Categoría del usuario (VIP, PREMIUM, REGULAR).
     * @param llegada Número correlativo de llegada a la fila virtual.
     */
    public Usuario(String nombre, String nivel, int llegada) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.llegada = llegada;
    }

    /**
     * Asigna un valor numérico de prioridad según el nivel de membresía.
     * A menor valor retornado, mayor es la prioridad.
     * @return 1 para VIP, 2 para PREMIUM, 3 para REGULAR.
     */
    private int prioridadNivel() {
        if (nivel.equalsIgnoreCase("VIP")) return 1;
        if (nivel.equalsIgnoreCase("PREMIUM")) return 2;
        return 3;
    }

    /**
     * Compara este usuario con otro para determinar el orden en la fila.
     * El criterio de ordenamiento es:
     * 1. Nivel de prioridad (VIP > PREMIUM > REGULAR).
     * 2. Número de llegada (menor número tiene prioridad).
     * @param otro El otro usuario con el que se va a comparar.
     * @return Un entero negativo si este usuario va antes, positivo si va después, 
     * o cero si son iguales.
     */
    @Override
    public int compareTo(Usuario otro) {
        int miPrioridad = this.prioridadNivel();
        int otraPrioridad = otro.prioridadNivel();

        if (miPrioridad != otraPrioridad) {
            return Integer.compare(miPrioridad, otraPrioridad);
        }
        return Integer.compare(this.llegada, otro.llegada);
    }

    /**
     * Genera una representación en cadena del usuario.
     * @return String con el formato: Nombre (Nivel, llegada: X).
     */
    @Override
    public String toString() {
        return String.format("%-10s | Nivel: %-8s | Turno: %d", nombre, nivel, llegada);
    }
}