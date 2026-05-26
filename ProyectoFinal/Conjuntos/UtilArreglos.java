package Conjuntos;

import java.lang.reflect.Array;
import java.util.Arrays;

public class UtilArreglos {
   public static <T> T[] eliminarDuplicados(T[] var0) {
      int var1 = var0.length;
      Object[] var2 = new Object[var1];
      int var3 = 0;

      for(int var4 = 0; var4 < var1; ++var4) {
         Object var5 = var0[var4];
         if (!esNulo(var5) && !estaRepetido(var2, var3, var5)) {
            var2[var3++] = var5;
         }
      }

      return (T[])copiarArreglo(var2, var3);
   }

   private static <T> boolean esNulo(T var0) {
      return var0 == null;
   }

   private static <T> boolean estaRepetido(T[] var0, int var1, T var2) {
      for(int var3 = 0; var3 < var1; ++var3) {
         if (var2.equals(var0[var3])) {
            return true;
         }
      }

      return false;
   }

   private static <T> T[] copiarArreglo(T[] var0, int var1) {
      Object[] var2 = new Object[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = var0[var3];
      }

      return (T[])var2;
   }

   public static <T> boolean tieneDuplicados(T[] var0) {
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Object var3 = var0[var2];
         if (!esNulo(var3)) {
            for(int var4 = var2 + 1; var4 < var1; ++var4) {
               Object var5 = var0[var4];
               if (var3.equals(var5)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static <T> boolean contieneNull(T[] var0) {
      if (var0 == null) {
         return false;
      } else {
         for(Object var4 : var0) {
            if (var4 == null) {
               return true;
            }
         }

         return false;
      }
   }

   public static <T> T[] copiaArregloGenerico(T[] var0) {
      return (T[])(var0 == null ? null : Arrays.copyOf(var0, var0.length, var0.getClass()));
   }

   public static <T> T[] crearArregloGenerico(T[] var0, int var1) {
      if (var0 == null) {
         throw new IllegalArgumentException("El arreglo base no puede ser null.");
      } else {
         return (T[])((Object[])Array.newInstance(var0.getClass().getComponentType(), var1));
      }
   }
}