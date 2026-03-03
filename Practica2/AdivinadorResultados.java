import java.io.File;
import java.util.Scanner;

/**
 * Esta clase se encarga de comparar a un alumno con un grupo de participantes
 * para adivinar quién ganaría, quién perdería o quién tiene un nivel similar.
 */
public class AdivinadorResultados {

    /**
     * Busca a todos los participantes a los que el alumno "a" les ganaría.
     * Gana si tiene mejor índice de victoria o si, empatando en índice, su cinta tiene una mayor jerarquía.
     * @param a El alumno que estamos analizando.
     * @param g El grupo de todos los participantes.
     * @return Un arreglo con los alumnos que perdieron contra "a".
     */
    public static Participante[] alumnoALesGana(Participante a, Participante[] g) {
        double indiceA = a.indiceDeVictoria();
        int jerarquiaA = a.getCinta().getJerarquia();

        int totalGanados = 0;
        for (int i = 0; i < g.length; i++) {
            if (indiceA > g[i].indiceDeVictoria() || (indiceA == g[i].indiceDeVictoria() && jerarquiaA > g[i].getCinta().getJerarquia())) {
                totalGanados++;
            }
        }

        Participante[] aGana = new Participante[totalGanados];
        int posicionDestino = 0;
        for (int i = 0; i < g.length; i++) {
            if (indiceA > g[i].indiceDeVictoria() || (indiceA == g[i].indiceDeVictoria() && jerarquiaA > g[i].getCinta().getJerarquia())) {
                aGana[posicionDestino] = g[i];
                posicionDestino++;
            }
        }
        return aGana;
    }

    /**
     * Encuentra quiénes son mejores que el alumno "a".
     * "a" pierde si el otro tiene mejor índice o mejor cinta en caso de empate.
     * * @param a Nuestro alumno de referencia.
     * @param g La lista de oponentes.
     * @return Arreglo de participantes que son mejores que "a".
     */
    public static Participante[] alumnoAPierde(Participante a, Participante[] g) {
        double indiceA = a.indiceDeVictoria();
        int jerarquiaA = a.getCinta().getJerarquia();

        int totalPerdidos = 0;
        for (int i = 0; i < g.length; i++) {
            if (indiceA < g[i].indiceDeVictoria() || (indiceA == g[i].indiceDeVictoria() && jerarquiaA < g[i].getCinta().getJerarquia())) {
                totalPerdidos++;
            }
        }

        Participante[] aPierde = new Participante[totalPerdidos];
        int posicionDestino = 0;
        for (int i = 0; i < g.length; i++) {
            if (indiceA < g[i].indiceDeVictoria() || (indiceA == g[i].indiceDeVictoria() && jerarquiaA < g[i].getCinta().getJerarquia())) {
                aPierde[posicionDestino] = g[i];
                posicionDestino++;
            }
        }
        return aPierde;
    }

    /**
     * Crea una lista simple con solo los números de los índices de victoria de todos.
     * * @param g El grupo de participantes.
     * @return Un arreglo de números decimales (índices).
     */
    public static double[] indicesVictoria(Participante[] g) {
        double[] ivictoria = new double[g.length];
        for (int i = 0; i < g.length; i++) {
            ivictoria[i] = g[i].indiceDeVictoria();
        }
        return ivictoria;
    }

    /**
     * Busca qué alumnos tienen exactamente el mismo color/jerarquía de cinta que "a".
     * * @param a El alumno de referencia.
     * @param g El grupo donde buscar.
     * @return Arreglo de compañeros con la misma cinta (sin incluir a "a").
     */
    public static Participante[] mismaCinta(Participante a, Participante[] g) {
        int cinta = a.getCinta().getJerarquia();

        int cintasIguales = 0;
        for (int i = 0; i < g.length; i++) {
            if (cinta == g[i].getCinta().getJerarquia() && a != g[i]) {
                cintasIguales++;
            }
        }

        Participante[] cintasI = new Participante[cintasIguales];
        int posicionDestino = 0;
        for (int i = 0; i < g.length; i++) {
            if (cinta == g[i].getCinta().getJerarquia() && a != g[i]) {
                cintasI[posicionDestino] = g[i];
                posicionDestino++;
            }
        }
        return cintasI;
    }

    /**
     * Encuentra alumnos que tienen un nivel de pelea parecido al de "a".
     * Se considera similar si la diferencia entre sus índices es menor a 0.1.
     * * @param a El alumno de referencia.
     * @param g El grupo de competidores.
     * @return Arreglo de alumnos con nivel parecido.
     */
    public static Participante[] indicesimilarAlAlumnoA(Participante a, Participante[] g) {
        double indiceA = a.indiceDeVictoria();

        int similares = 0;
        for (int i = 0; i < g.length; i++) {
            double diferencia = indiceA - g[i].indiceDeVictoria();
            if (diferencia < 0.1 && diferencia > -0.1 && a != g[i]) {
                similares++;
            }
        }

        Participante[] similarA = new Participante[similares];
        int posicionDestino = 0;

        for (int i = 0; i < g.length; i++) {
            double diferencia = indiceA - g[i].indiceDeVictoria();
            if (diferencia < 0.1 && diferencia > -0.1 && a != g[i]) {
                similarA[posicionDestino] = g[i];
                posicionDestino++;
            }
        }
        return similarA;
    }

    /**
     * Método principal que arranca el programa. 
     * Lee el archivo "participantes.txt", crea a los alumnos y muestra los resultados.
     */
   public static void main(String[] args) {
        String separacion = "--------------------------------------------------------------------------------";

        try {
            File archivo = new File("participantes.txt");
            Scanner lector = new Scanner(archivo);

            // 1. La primera línea es el alumno de referencia
            Participante alumnoA = null;
            if (lector.hasNextLine()) {
                alumnoA = parsearLinea(lector.nextLine());
            }

            // 2. Leer el resto (Suponiendo un máximo de 100 participantes)
            Participante[] grupo = new Participante[100];
            int total = 0;
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                if (!linea.trim().isEmpty()) {
                    grupo[total] = parsearLinea(linea);
                    total++;
                }
            }
            lector.close();

            // 3. Ajustar el arreglo al tamaño real
            Participante[] grupoReal = new Participante[total];
            for (int i = 0; i < total; i++) {
                grupoReal[i] = grupo[i];
            }

            // --- PROBAR MÉTODOS ---

            // Comprobar contra qué alumnos gana el alumno A
            Participante[] ganados = alumnoALesGana(alumnoA, grupoReal);
            if (ganados.length > 0) {
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " le gana a: ||---");
                for (int i = 0; i < ganados.length; i++) {
                    String rivalG = ganados[i].toString();
                    System.out.println("- " + rivalG);
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no ganó ningún enfrentamiento.");
            }

            System.out.println(separacion);

            // Comprobar contra qué alumnos pierde el alumno A
            Participante[] perdidos = alumnoAPierde(alumnoA, grupoReal);
            if (perdidos.length > 0) {
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " pierde contra: ||---");
                for (int i = 0; i < perdidos.length; i++) {
                    String rivalP = perdidos[i].toString();
                    System.out.println("- " + rivalP);
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no perdió ningún enfrentamiento.");
            }

            System.out.println(separacion);

            // Comprobar con qué alumnos tiene índices similares
            Participante[] similares = indicesimilarAlAlumnoA(alumnoA, grupoReal);
            if (similares.length > 0) {
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " tiene índices similares a: ||---");
                for (int i = 0; i < similares.length; i++) {
                    String rivalS = similares[i].toString();
                    System.out.println("- " + rivalS);
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no tiene índices similares.");
            }

            System.out.println(separacion);

            // Comprobar quiénes tienen la misma cinta
            Participante[] mismaCintaArr = mismaCinta(alumnoA, grupoReal);
            if (mismaCintaArr.length > 0) {
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " tiene la misma cinta que: ||---");
                for (int i = 0; i < mismaCintaArr.length; i++) {
                    String rivalMC = mismaCintaArr[i].toString();
                    System.out.println("- " + rivalMC);
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no tiene la misma cinta.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Toma una línea de texto del archivo y la convierte en un objeto tipo Participante.
     * Corta la línea usando las comas y los dos puntos como guía.
     * * @param linea El texto leido del archivo.
     * @return Un nuevo objeto Participante con sus datos cargados.
     */
    private static Participante parsearLinea(String linea) {
        String[] partes = linea.split(",");
        String nombre = partes[0].trim();
        int m = Integer.parseInt(partes[1].split(":")[1].trim());
        int p = Integer.parseInt(partes[2].split(":")[1].trim());
        String cinta = partes[3].split(":")[1].trim();

        return new Participante(nombre, m, p, cinta);
    }
}