import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Grupo {
    private String nombreMateria;
    private ConjuntoArreglo<String> estudiantes;

    public Grupo(String nombreMateria) {
        this.nombreMateria = nombreMateria;
        this.estudiantes = new ConjuntoArreglo<>(); // Usa un constructor vacio.
    }

    public void inscribir(String estudiante) {
        this.estudiantes.agregarElemento(estudiante); // Usa el método agregarElemento para agregar estudiantes al conjunto.
    }

    public ConjuntoArreglo<String> getEstudiantes() {
        return this.estudiantes;
    }

    public static void main(String[] args) {
        Grupo web = new Grupo("Programacion Web");
        Grupo robotica = new Grupo("Robotica");
        Grupo ciber = new Grupo("Ciberseguridad");
        
        try (BufferedReader br = new BufferedReader(new FileReader("Alumnos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    String alumno = partes[0].trim();
                    String materia = partes[1].trim();

                    if (materia.equalsIgnoreCase("Programacion Web")) web.inscribir(alumno);
                    else if (materia.equalsIgnoreCase("Robotica")) robotica.inscribir(alumno);
                    else if (materia.equalsIgnoreCase("Ciberseguridad")) ciber.inscribir(alumno);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("--- RESULTADOS DE LA COORDINACION ---");

        // a)Los estudiantes inscritos simult ́aneamente en las materias Programaci ́on Web y Rob ́otica.(Interseccion)
        Conjunto<String> incA = web.getEstudiantes().interseccion(robotica.getEstudiantes());
        System.out.println("a) Simultaneos en Web y Robotica: " + incA);

        // b)Los estudiantes inscritos en Programaci ́on Web, pero que no est ́en inscritos en Ciberseguridad.(Pertenencia)
        ConjuntoArreglo<String> incB = new ConjuntoArreglo<>();
        for (String est : web.getEstudiantes()) {
            if (!ciber.getEstudiantes().pertenece(est)) {
                incB.agregarElemento(est);
            }
        }
        System.out.println("b) En Web pero no en Ciberseguridad: " + incB);

        // c) Los estudiantes inscritos en al menos una de las materias optativas.(Unión de los 3)
        Conjunto<String> incC = web.getEstudiantes().union(robotica.getEstudiantes()).union(ciber.getEstudiantes());
        System.out.println("c) En al menos una materia: " + incC);

        // d)Los estudiantes que se encuentren en al menos dos optativas. (combinadas)
        Conjunto<String> wR = web.getEstudiantes().interseccion(robotica.getEstudiantes());
        Conjunto<String> wC = web.getEstudiantes().interseccion(ciber.getEstudiantes());
        Conjunto<String> rC = robotica.getEstudiantes().interseccion(ciber.getEstudiantes());
        Conjunto<String> incD = wR.union(wC).union(rC);
        System.out.println("d) En al menos dos optativas: " + incD);
    }
}