import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainLectorPaginas {
    
    public static void main(String[] args) {
        ListaDoblementeLigada<Pagina> lista = new ListaDoblementeLigada<>();
        Historial historial = new Historial(lista);
        
        try (BufferedReader br = new BufferedReader(new FileReader("paginas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    String fecha = partes[1].trim();
                    Pagina nuevaPagina = new Pagina(nombre, fecha);
                    historial.agregarPagina(nuevaPagina);
                }
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
        
        System.out.println("Historial del navegador 'El Chido':");
        System.out.println(historial);
    }
}