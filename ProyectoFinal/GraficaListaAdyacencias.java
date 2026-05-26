import Colecciones.Listas.*;
import Picolas.Cola;
import Picolas.Pila;

class GraficaListaAdyacencia<T> implements Grafica<T> {

    public ListaDoblementeLigada<Vertice<T>> vertices;
    public int numeroDeAristas;

    private class Vertice<E> { 
        public E elemento;
        public ListaDoblementeLigada<Arista> adyacentes; 
        public boolean visitado;
        public Vertice<E> padre;
        public int distancia;

        private Vertice(E elemento) {
            this.elemento = elemento;
            this.adyacentes = new ListaDoblementeLigada<>();
            this.visitado = false;
            this.padre = null;
            this.distancia = 0;
        }
    } 

    private class Arista{
        private Vertice<T> vertice; 
        private int peso;

        private Arista(Vertice<T> vertice, int peso) {
            this.vertice = vertice;
            this.peso = peso;
        }
    }

    public GraficaListaAdyacencia() {
        this.vertices = new ListaDoblementeLigada<>();
        this.numeroDeAristas = 0;
    }

    public boolean buscarVertice(T elemento){
        for (Vertice<T> vertice : vertices) {
            if (vertice.elemento.equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public Vertice<T> devolverVertice(T elemento){
        for (Vertice<T> vertice : vertices) {
            if (vertice.elemento.equals(elemento)) {
                return vertice;
            }
        }
        return null;
    }

    @Override
    public void agregarVertice(T elemento){
        if(elemento == null || buscarVertice(elemento)){
           return; // No se permiten vértices nulos o duplicados
        } else {
            Vertice<T> nuevoVertice = new Vertice<>(elemento);
            vertices.agregar(nuevoVertice);
        }
    }

    @Override
    public void agregarArista(T e1, T e2){
        agregarAristaPonderada(e1, e2, 0);
    }

    public boolean sonVecinos(T e1, T e2){
        Vertice<T> vertice1 = devolverVertice(e1);
        Vertice<T> vertice2 = devolverVertice(e2);

        if(vertice1 == null || vertice2 == null){
            System.out.println("Uno o ambos vértices no existen en la gráfica");
            return false; // Si alguno no existe, no pueden ser vecinos
        }

        for(Arista arista : vertice1.adyacentes){
            if(arista.vertice == vertice2){
                return true;
            }
        }
        return false;
    }

    @Override
    public void agregarAristaPonderada(T e1, T e2, int peso){
         if(buscarVertice(e1) && buscarVertice(e2)){
            Vertice<T> vertice1 = devolverVertice(e1);
            Vertice<T> vertice2 = devolverVertice(e2);

            if(vertice1 == vertice2){
                System.out.println("No se pueden agregar aristas entre el mismo vértice");
                return;
            }

            if(sonVecinos(e1, e2)){
                System.out.println("Los vértices ya son vecinos, no se pueden agregar aristas duplicadas");
                return;
            }

            vertice1.adyacentes.agregar(new Arista(vertice2, peso));
            vertice2.adyacentes.agregar(new Arista(vertice1, peso));
            numeroDeAristas++;
        } else {
            System.out.println("Uno o ambos vértices no existen en la gráfica");
        }
    }

    @Override
    public ListaDoblementeLigada<T> devolverBfs(T inicio){
        Vertice<T> w = devolverVertice(inicio);
        if (w == null) return new ListaDoblementeLigada<>();
        
        for(Vertice<T> aux : vertices){
            aux.visitado = false; 
        }

        Cola<Vertice<T>> cola = new Cola<>();
        ListaDoblementeLigada<T> bfs = new ListaDoblementeLigada<>();
        w.visitado = true;
        cola.meter(w);

        while(!cola.estaVacia()){
            Vertice<T> actual = cola.sacar();
            bfs.agregar(actual.elemento);

            for(Arista arista : actual.adyacentes){
                if(!arista.vertice.visitado){
                    arista.vertice.visitado = true;
                    cola.meter(arista.vertice);
                }
            }
        }
        return bfs;
    }

    @Override
    public ListaDoblementeLigada<T> devolverDfs(T inicio){
        Vertice<T> x = devolverVertice(inicio);
        if (x == null) return new ListaDoblementeLigada<>();

        for(Vertice<T> aux : vertices){
            aux.visitado = false; 
        }

        Pila<Vertice<T>> pila = new Pila<>();
        ListaDoblementeLigada<T> dfs = new ListaDoblementeLigada<>();
        x.visitado = true;
        pila.meter(x);

        while(!pila.estaVacia()){
            Vertice<T> actual = pila.sacar();
            dfs.agregar(actual.elemento);

            for(Arista arista : actual.adyacentes){
                if(!arista.vertice.visitado){
                    arista.vertice.visitado = true;
                    pila.meter(arista.vertice);
                }
            }
        }
        return dfs;
    }

    @Override
    public void eliminarVertice(T elemento){
        Vertice<T> verticeAEliminar = devolverVertice(elemento);
        if(verticeAEliminar == null){
            System.out.println("El vertice no existe en la grafica");
            return; // Detener ejecución para evitar errores al intentar eliminar un vértice inexistente
        }
        
        // CORREGIDO: Iterar sobre las aristas del vértice 'u' para limpiar referencias inversas
        for(Vertice<T> u : vertices){
            Arista aristaEncontrada = null; 

            for(Arista ar : u.adyacentes){ // Buscamos en los adyacentes de 'u'
                if(ar.vertice == verticeAEliminar){
                    aristaEncontrada = ar;
                    break;
                }
            }

            if(aristaEncontrada != null){
                u.adyacentes.eliminar(aristaEncontrada);
                numeroDeAristas--;
            }
        }
        vertices.eliminar(verticeAEliminar);
    }
    
    @Override
    public void eliminarArista(T e1, T e2){
        Vertice<T> vertice1 = devolverVertice(e1);
        Vertice<T> vertice2 = devolverVertice(e2);

        if(vertice1 == null || vertice2 == null){
            System.out.println("Uno o ambos vértices no existen en la gráfica");
            return;
        }

        if(!sonVecinos(e1, e2)){
            System.out.println("Los vértices no son vecinos, no existe una arista entre ellos");
            return;
        }

        Arista aristaEnVertice1 = null;
        Arista aristaEnVertice2 = null;

        for(Arista arista : vertice1.adyacentes){
            if(arista.vertice == vertice2){
                aristaEnVertice1 = arista;
                break;
            }
        }

        for(Arista arista : vertice2.adyacentes){
            if(arista.vertice == vertice1){
                aristaEnVertice2 = arista;
                break;
            }
        }

        if(aristaEnVertice1 != null && aristaEnVertice2 != null){
            vertice1.adyacentes.eliminar(aristaEnVertice1);
            vertice2.adyacentes.eliminar(aristaEnVertice2);
            numeroDeAristas--;
        } else {
            System.out.println("No existe una arista entre los vértices proporcionados");
        }
    }

    @Override
    public ListaDoblementeLigada<T> devolverRutaMasCortaNoPonderada(T inicio, T fin){
        Vertice<T> verticeInicio = devolverVertice(inicio);
        Vertice<T> verticeFin = devolverVertice(fin);

        if(verticeInicio == null || verticeFin == null){
            System.out.println("Uno o ambos vértices no existen en la gráfica");
            return null;
        }

        for(Vertice<T> aux : vertices){
            aux.visitado = false; 
            aux.padre = null; 
        }

        Cola<Vertice<T>> cola = new Cola<>();
        verticeInicio.visitado = true;
        cola.meter(verticeInicio);
        
        boolean rutaEncontrada = false;

        while(!cola.estaVacia()){
            Vertice<T> actual = cola.sacar();

            if(actual == verticeFin){ // Comparación de referencias directas
                rutaEncontrada = true;
                break;
            }

            for(Arista arista : actual.adyacentes){
                if(!arista.vertice.visitado){
                    arista.vertice.visitado = true;
                    arista.vertice.padre = actual; 
                    cola.meter(arista.vertice);
                }
            }
        } // CORREGIDO: El ciclo while termina de buscar libremente aquí

        // CORREGIDO: Construcción de la ruta fuera del ciclo de búsqueda
        if (rutaEncontrada) {
            ListaDoblementeLigada<T> ruta = new ListaDoblementeLigada<>();
            Vertice<T> verticeActual = verticeFin;
            while(verticeActual != null){
                ruta.agregar(verticeActual.elemento); // Tip opcional: Se puede insertar al inicio si tu lista lo soporta para que no quede invertido
                verticeActual = verticeActual.padre;
            }
            return ruta;
        }

        System.out.println("No existe una ruta entre los vértices proporcionados");
        return null;
    }
    
    @Override
    public ListaDoblementeLigada<T> rutaMasCortaPonderada(T inicio, T fin) {
        Vertice<T> verticeInicio = devolverVertice(inicio);
        Vertice<T> verticeFin = devolverVertice(fin);

        // 1.Validamos de que ambos existan en el metro
        if (verticeInicio == null || verticeFin == null) {
            System.out.println("Uno o ambos vértices no existen en la gráfica.");
            return null;
        }

        // 2. Inicialización de los estados de los vértices
        for (Vertice<T> aux : vertices) {
            aux.visitado = false;
            aux.padre = null;
            aux.distancia = Integer.MAX_VALUE; 
        }
        verticeInicio.distancia = 0; // La estación origen está a 0 minutos de sí misma

        // Ejecutamos el bucle principal tantas veces como vértices tengamos
        for (int i = 0; i < vertices.devolverLongitud(); i++) {
            
            // A. Buscar el vértice no visitado con la menor distancia conocida
            Vertice<T> actual = null;
            int distanciaMinima = Integer.MAX_VALUE;

            for (Vertice<T> v : vertices) {
                if (!v.visitado && v.distancia < distanciaMinima) {
                    distanciaMinima = v.distancia;
                    actual = v;
                }
            }

            // Si no encontramos un vértice alcanzable o ya procesamos todos, terminamos
            if (actual == null || actual.distancia == Integer.MAX_VALUE) {
                break;
            }

            // Si ya llegamos a la estación destino, podemos detener la búsqueda temprano
            if (actual == verticeFin) {
                break;
            }

            // Marcar el vértice actual como procesado/visitado
            actual.visitado = true;

            // B. RELAJACIÓN DE ARISTAS: Evaluar a todos sus vecinos adyacentes
            for (Arista arista : actual.adyacentes) {
                Vertice<T> vecino = arista.vertice;

                if (!vecino.visitado) {
                    // Calculamos el tiempo acumulado sumando el costo de la arista
                    int distanciaPotencial = actual.distancia + arista.peso;

                    // Si este nuevo camino es más rápido que el que ya conocía el vecino
                    if (distanciaPotencial < vecino.distancia) {
                        vecino.distancia = distanciaPotencial; // Actualizamos con el menor peso
                        vecino.padre = actual;                // Registramos de dónde venimos
                    }
                }
            }
        }

        // 3. RECONSTRUCCIÓN DE LA RUTA (Rastreo de padres desde el final hasta el inicio)
        // Si la distancia del destino sigue siendo infinito, significa que la estación está aislada
        if (verticeFin.distancia == Integer.MAX_VALUE) {
            System.out.println("No existe una ruta ponderada entre las estaciones proporcionadas.");
            return null;
        }

        ListaDoblementeLigada<T> ruta = new ListaDoblementeLigada<>();
        Vertice<T> recorrido = verticeFin;

        while (recorrido != null) {
            ruta.agregar(recorrido.elemento);
            recorrido = recorrido.padre; // Nos movemos al nodo padre
        }
        
        return ruta;
    }

    public GraficaListaAdyacencia<T> clonar() {
    // 1. Creamos la nueva instancia vacía que será la gráfica auxiliar
    GraficaListaAdyacencia<T> copia = new GraficaListaAdyacencia<>();

    // 2. Primera pasada: Copiar todos los vértices (estaciones)
    for (T estacion : this) { // Usamos el iterador que programaste
        copia.agregarVertice(estacion);
    }

    // 3. Segunda pasada: Copiar todas las aristas (tramos y pesos)
    for (Vertice<T> vOriginal : this.vertices) {
        for (Arista aristaOriginal : vOriginal.adyacentes) {
            // Extraemos los nombres de las estaciones y el peso del tramo
            T origen = vOriginal.elemento;
            T destino = aristaOriginal.vertice.elemento;
            int peso = aristaOriginal.peso;

            // Para evitar duplicar la arista en una gráfica no dirigida, 
            // validamos si ya existe la conexión en la copia antes de agregarla
            if (!copia.sonVecinos(origen, destino)) {
                copia.agregarAristaPonderada(origen, destino, peso);
            }
        }
    }

    return copia;
    }

    
    @Override
    public int devolverNumeroDeVertices(){
        return vertices.devolverLongitud(); 
    }
    
    @Override
    public int devolverNumeroDeAristas(){
        return this.numeroDeAristas; 
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new IteradorGrafica();
    }

    private class IteradorGrafica implements java.util.Iterator<T> {
        private java.util.Iterator<Vertice<T>> iteradorVertices;

        public IteradorGrafica() {
            this.iteradorVertices = vertices.iterator();
        }

        @Override
        public boolean hasNext() {
            return iteradorVertices.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No hay más elementos en la gráfica.");
            }
            Vertice<T> verticeActual = iteradorVertices.next();
            return verticeActual.elemento; 
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remover elementos mediante el iterador no está soportado.");
        }
    }
}

