public enum Cinta {
    KAIMUA(1), // Blanca
    MOLI(2),   // Naranja
    LUA(3),    // Morada
    MOANA(4),  // Azul
    ULAKUI(5); // Verde

    private final int nivel;

    Cinta(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public static Cinta obtenerCinta(String nombre) {
        for (Cinta c : values()) {
            if (c.name().equalsIgnoreCase(nombre.trim())) {
                return c;
            }
        }
        throw new IllegalArgumentException("Cinta no reconocida: " + nombre);
    }
}