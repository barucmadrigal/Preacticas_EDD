/**
 * Esta clase representa la cinta o cinturón de un participante.
 * Se encarga de asignar un nivel de importancia (jerarquía)
 * en función del nombre de la cinta.
 */
public class Cinta {
   /** Nombre de la cinta (ej. "Moli", "Kaimua") */
   String nombre;
   
   /** Nivel numérico de la cinta. A mayor número, mayor rango. */
   int jerarquia;

   // Definición de niveles de colores para referencia
   static final int BLANCO = 1;
   static final int NARANJA = 2;
   static final int MORADO = 3;
   static final int AZUL = 4;
   static final int VERDE = 5;

   /**
    * Crea una nueva cinta y le asigna su jerarquía automáticamente.
    * @param nombre El nombre de la cinta que se va a crear.
    */
   public Cinta(String nombre) {
      this.nombre = nombre;
      this.obtenerJerarquia(nombre);
   }

   /**
    * Método interno que traduce el nombre de la cinta a un número.
    * Si el nombre no existe, se le asigna 0.
    * @param nombreCinta El texto con el nombre de la cinta.
    */
   private void obtenerJerarquia(String nombreCinta) {
      switch (nombreCinta) {
         case "Kaimua":
            this.jerarquia = BLANCO;
            break;
         case "Moli":
            this.jerarquia = NARANJA;
            break;
         case "Lua":
            this.jerarquia = MORADO;
            break;
         case "Moana":
            this.jerarquia = AZUL;
            break;
         case "Ulajui":
            this.jerarquia = VERDE;
            break;
         default:
            this.jerarquia = 0;
      }
   }

   /**
    * Entrega el nivel numérico de la cinta para poder comparar peleadores.
    * @return Un número del 1 al 5.
    */
   public int getJerarquia() {
      return this.jerarquia;
   }

   /**
    * Entrega el nombre de la cinta.
    * @return El nombre (ej. "Moana").
    */
   public String getNombre() {
      return this.nombre;
   }

   /**
    * Escribe los atributos de la cinta en forma de texto.
    * @return Una frase con el nombre y la jerarquía de la cinta.
    */
   @Override
   public String toString() {
      return "Nombre de la cinta: " + this.nombre + " ,jerarquia: " + this.jerarquia;
   }
}