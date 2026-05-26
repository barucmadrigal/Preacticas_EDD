package TablasHash;
public class Pokemon {
    String nombre;
    int nivel;
    String tipo;

    public Pokemon(String nombre, int nivel, String tipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nombre + " [Nivel: " + nivel + ", Tipo: " + tipo + "]";
    }

    // Podemos usar el nombre + tipo + nivel para el hash
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (nombre != null ? nombre.hashCode() : 0);
        hash = 31 * hash + nivel;
        hash = 31 * hash + (tipo != null ? tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;
        Pokemon p = (Pokemon) o;
        return nivel == p.nivel &&
               nombre.equals(p.nombre) &&
               tipo.equals(p.tipo);
    }
}