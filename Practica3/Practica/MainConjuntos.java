public class MainConjuntos {
    public static void main(String[] args) {
        // Prueba con Strings
        String[] frutas = {"Manzana", "Platano", "Naranja"};
        String[] otrasFrutas = {"Platano", "Kiwi"};

        Conjunto<String> conjunto1 = new ConjuntoArreglo<String>(frutas);
        Conjunto<String> conjunto2 = new ConjuntoArreglo<String>(otrasFrutas);

        System.out.println("Conjunto 1 tiene como elemento a 'Platano': " + conjunto1.pertenece("Platano"));
        System.out.println("Conjunto 1 contiene a 'Kiwi': " + conjunto1.pertenece("Kiwi"));
		
		System.out.println("____________________________________________________________");
		
        conjunto1.agregarElemento("Kiwi");
        System.out.println("Después de agregar 'Kiwi', conjunto 1 contiene a 'Kiwi': " + conjunto1.pertenece("Kiwi"));
        System.out.println("Conjunto 1 contiene a Conjunto 2: " + conjunto1.contieneConjunto(conjunto2));
        
        System.out.println("____________________________________________________________");

        System.out.println("Unión de conjunto1 y conjunto2:");
        System.out.println(conjunto1.union(conjunto2));

		System.out.println("____________________________________________________________");		
		
        System.out.println("Intersección de conjunto1 y conjunto2:");
        System.out.println(conjunto1.interseccion(conjunto2));
		
		System.out.println("____________________________________________________________");
		
        System.out.println("¿conjunto1 es igual a conjunto2?: " + conjunto1.iguales(conjunto2));

        // Prueba con enteros
        Integer[] nums1 = {1, 2, 3};
        Integer[] nums2 = {3, 4, 5};

        Conjunto<Integer> intConj1 = new ConjuntoArreglo<Integer>(nums1);
        Conjunto<Integer> intConj2 = new ConjuntoArreglo<Integer>(nums2);

        System.out.println("Unión de enteros:");
        System.out.println(intConj1.union(intConj2));
		
		System.out.println("____________________________________________________________");
		
        System.out.println("Intersección de enteros:");
        System.out.println(intConj1.interseccion(intConj2));

		System.out.println("____________________________________________________________");		
		
        System.out.println("¿Los conjuntos de enteros son iguales?: " + intConj1.iguales(intConj2));
        
        System.out.println("____________________________________________________________");	
        
        // Probando eliminar
        
        Conjunto<String> union = conjunto1.union(conjunto2);
        union.eliminarElemento("Manzana");
        System.out.println("Unión de frutas después de eliminar 'Manzana': " + union);
    }
}
