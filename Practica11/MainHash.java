import java.util.Iterator;

public class MainHash {
    public static void main(String[] args) {
        Dispersor<Pokemon> dispersor = (Pokemon p) -> p.hashCode();

        TablaHash<Pokemon, String> pokedex = new TablaHashEncadenadora<>(dispersor);

        pokedex.agregar(new Pokemon("Pikachu", 25, "Eléctrico"), "Ash");
        pokedex.agregar(new Pokemon("Charmander", 15, "Fuego"), "Ash");
        pokedex.agregar(new Pokemon("Bulbasaur", 10, "Planta"), "Ash");
        pokedex.agregar(new Pokemon("Squirtle", 12, "Agua"), "Misty");
        pokedex.agregar(new Pokemon("Gyarados", 30, "Agua/Volador"), "Misty");
        pokedex.agregar(new Pokemon("Eevee", 8, "Normal"), "Brock");

        System.out.println(pokedex);
        
        Pokemon p = new Pokemon("Pikachu", 25, "Eléctrico");
        System.out.println("Entrenador de Pikachu: " + pokedex.obtenerValorLlave(p));

        System.out.println("Está Charmander en la TablaHash? " + pokedex.buscar(new Pokemon("Charmander", 15, "Fuego")));

        System.out.println("\nTodos los entrenadores:");
        for (String entrenador : pokedex) {
            System.out.println(entrenador);
        }

        System.out.println("\nTodos los pokemon:");
        Iterator<Pokemon> iterador = pokedex.iteradorLlaves();
        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
    }
}
