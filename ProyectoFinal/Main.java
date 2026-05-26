public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos los tres componentes del MVC
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        
        // 2. El controlador recibe al modelo y a la vista
        Controlador controlador = new Controlador(modelo, vista);
        
        // 3. Arrancamos la aplicación
        controlador.iniciar();
    }
}