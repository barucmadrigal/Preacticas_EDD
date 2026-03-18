import java.io.File;
import java.util.Scanner;

/**
 * Esta clase se encarga de comparar a un alumno con un grupo de participantes
 * para adivinar quién ganaría, quién perdería o quién tiene un nivel similar.
 * AHORA IMPLEMENTADA CON LISTAS LIGADAS SIMPLES.
 * 
 * @author Baruc Santiago Madrigal Ronces y Areli Ramirez Espejel
 */
public class AdivinadorResultados {

	/**
	 * Encuentra quienes son peores que el alumno "a".
	 * @param a Participante del que se quiere saber contra que competidores gana.
	 * @param g Lista de competidores.
	 * @return Lista de participantes contra los que el participante "a" gana.
	 * */
    public static ListaLigadaSimple<Participante> alumnoALesGana(Participante a, ListaLigadaSimple<Participante> g) {
        ListaLigadaSimple<Participante> aGana = new ListaLigadaSimple<>();
        double indiceA = a.indiceDeVictoria();
        int jerarquiaA = a.getCinta().getJerarquia();

        // Gracias a tu iterador, podemos usar un ciclo for-each directamente
        for (Participante p : g) {
            if (indiceA > p.indiceDeVictoria() || (indiceA == p.indiceDeVictoria() && jerarquiaA > p.getCinta().getJerarquia())) {
                aGana.agregar(p); // La lista crece automáticamente
            }
        }
        return aGana;
    }

    /**
	 * Encuentra quiénes son mejores que el alumno "a".
	 * @param a Participante de que se quiere saber contra que comperidores pierde.
	 * @param g Lista de competidores.
	 * @return Lista de participantes contra los que el participante "a" pierde 
	*/
    public static ListaLigadaSimple<Participante> alumnoAPierde(Participante a, ListaLigadaSimple<Participante> g) {
        ListaLigadaSimple<Participante> aPierde = new ListaLigadaSimple<>();
        double indiceA = a.indiceDeVictoria();
        int jerarquiaA = a.getCinta().getJerarquia();

        for (Participante p : g) {
            if (indiceA < p.indiceDeVictoria() || (indiceA == p.indiceDeVictoria() && jerarquiaA < p.getCinta().getJerarquia())) {
                aPierde.agregar(p);
            }
        }
        return aPierde;
    }

	/**
	 * Devuelve los indices de victoria de cada participante
	 * @param g Lista con los participantes de los que se quiere saber su indice de victoria.
	 * @return Lista con los indices de victoria de los participantes.
	*/
    public static ListaLigadaSimple<Double> indicesVictoria(ListaLigadaSimple<Participante> g) {
        ListaLigadaSimple<Double> ivictoria = new ListaLigadaSimple<>();
        for (Participante p : g) {
            ivictoria.agregar(p.indiceDeVictoria());
        }
        return ivictoria;
    }

    /**
	 * Busca qué alumnos tienen exactamente el mismo color/jerarquía de cinta que "a"
	 * @param a Participante del que se quiere saber que otros competidores comparten la misma cinta.
	 * @param g	Lista de los competidores.
	 * @return Lista de competidores con los que el participante "a" comparte el mismo color 
	 */
     
    public static ListaLigadaSimple<Participante> mismaCinta(Participante a, ListaLigadaSimple<Participante> g) {
        ListaLigadaSimple<Participante> cintasI = new ListaLigadaSimple<>();
        int cinta = a.getCinta().getJerarquia();

        for (Participante p : g) {
            if (cinta == p.getCinta().getJerarquia() && a != p) {
                cintasI.agregar(p);
            }
        }
        return cintasI;
    }

    /**
	 * Encuentra alumnos que tienen un nivel de pelea parecido al de "a".
	 * @param a Participante con el que se quiere comparar el nivel de pelea de otros participantes. 
	 * @param g Lista de competidores.
	 * @return Lista de participantes con los que "a" tiene un nivel parecido.
	 */
    public static ListaLigadaSimple<Participante> indicesimilarAlAlumnoA(Participante a, ListaLigadaSimple<Participante> g) {
        ListaLigadaSimple<Participante> similarA = new ListaLigadaSimple<>();
        double indiceA = a.indiceDeVictoria();

        for (Participante p : g) {
            double diferencia = indiceA - p.indiceDeVictoria();
            if (diferencia < 0.1 && diferencia > -0.1 && a != p) {
                similarA.agregar(p);
            }
        }
        return similarA;
    }

	/**
	 * Metodo principal que arranca el programa.
	 */
    public static void main(String[] args) {
        String separacion = "--------------------------------------------------------------------------------";

        try {
            File archivo = new File("/home/barucsantiagomadrigalronces/Preacticas_EDD/Practica4/Alumnos/participantes.txt");
            Scanner lector = new Scanner(archivo);

            Participante alumnoA = null;
            if (lector.hasNextLine()) {
                alumnoA = parsearLinea(lector.nextLine());
            }

            // Aquí cambiamos el arreglo fijo por tu lista simple
            ListaLigadaSimple<Participante> grupo = new ListaLigadaSimple<>();
            
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                if (!linea.trim().isEmpty()) {
                    grupo.agregar(parsearLinea(linea));
                }
            }
            lector.close();


            ListaLigadaSimple<Participante> ganados = alumnoALesGana(alumnoA, grupo);
            if (ganados.devolverLongitud() > 0) { // Usamos tu nuevo método devolverLongitud()
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " le gana a: ||---");
                for (Participante p : ganados) {
                    System.out.println("- " + p.toString());
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no ganó ningún enfrentamiento.");
            }
            System.out.println(separacion);

            ListaLigadaSimple<Participante> perdidos = alumnoAPierde(alumnoA, grupo);
            if (perdidos.devolverLongitud() > 0) {
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " pierde contra: ||---");
                for (Participante p : perdidos) {
                    System.out.println("- " + p.toString());
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no perdió ningún enfrentamiento.");
            }
            System.out.println(separacion);

            ListaLigadaSimple<Participante> similares = indicesimilarAlAlumnoA(alumnoA, grupo);
            if (similares.devolverLongitud() > 0) {
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " tiene índices similares a: ||---");
                for (Participante p : similares) {
                    System.out.println("- " + p.toString());
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no tiene índices similares.");
            }
            System.out.println(separacion);

            ListaLigadaSimple<Participante> mismaCintaArr = mismaCinta(alumnoA, grupo);
            if (mismaCintaArr.devolverLongitud() > 0) {
                System.out.println("---|| El alumno " + alumnoA.getNombre() + " tiene la misma cinta que: ||---");
                for (Participante p : mismaCintaArr) {
                    System.out.println("- " + p.toString());
                }
            } else {
                System.out.println("El participante " + alumnoA.getNombre() + " no tiene la misma cinta.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Participante parsearLinea(String linea) {
        String[] partes = linea.split(",");
        String nombre = partes[0].trim();
        int m = Integer.parseInt(partes[1].split(":")[1].trim());
        int p = Integer.parseInt(partes[2].split(":")[1].trim());
        String cinta = partes[3].split(":")[1].trim();

        return new Participante(nombre, m, p, cinta);
    }
}