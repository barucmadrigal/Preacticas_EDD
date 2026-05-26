package Colecciones.Arboles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsciiBox {
   static String si = "╔";
   static String sd = "╗";
   static String ii = "╚";
   static String id = "╝";
   static String ld = "║";
   static String st = "═";
   static String fi = "╠";
   static String fd = "╣";
   static String ti = "╩";
   static String tn = "╦";
   static String dt = "╬";
   static String pk = "¶";
   static String a = "á";
   static String e = "é";
   static String i = "í";
   static String o = "ó";
   static String u = "ú";
   static String ud = "ü";
   static String n = "ñ";
   static String aM = "Á";
   static String eM = "É";
   static String iM = "Í";
   static String oM = "Ó";
   static String uM = "Ú";
   static String udM = "Ü";
   static String nM = "Ñ";
   static String qm = "¿";
   static String am = "¡";
   static String RESET = "\u001b[0m";
   static String BLACK = "\u001b[30m";
   static String B_BLACK = "\u001b[90m";
   static String RED = "\u001b[31m";
   static String B_RED = "\u001b[91m";
   static String GREEN = "\u001b[32m";
   static String B_GREEN = "\u001b[92m";
   static String YELLOW = "\u001b[33m";
   static String B_YELLOW = "\u001b[93m";
   static String BLUE = "\u001b[34m";
   static String B_BLUE = "\u001b[94m";
   static String PURPLE = "\u001b[35m";
   static String B_PURPLE = "\u001b[95m";
   static String CYAN = "\u001b[36m";
   static String B_CYAN = "\u001b[96m";
   static String WHITE = "\u001b[37m";
   static String B_WHITE = "\u001b[97m";
   static String BG_BLACK = "\u001b[40m";
   static String BGB_BLACK = "\u001b[100m";
   static String BG_RED = "\u001b[41m";
   static String BGB_RED = "\u001b[101m";
   static String BG_GREEN = "\u001b[42m";
   static String BGB_GREEN = "\u001b[102m";
   static String BG_YELLOW = "\u001b[43m";
   static String BGB_YELLOW = "\u001b[103m";
   static String BG_BLUE = "\u001b[44m";
   static String BGB_BLUE = "\u001b[104m";
   static String BG_PURPLE = "\u001b[45m";
   static String BGB_PURPLE = "\u001b[105m";
   static String BG_CYAN = "\u001b[46m";
   static String BGB_CYAN = "\u001b[106m";
   static String BG_WHITE = "\u001b[47m";
   static String BGB_WHITE = "\u001b[107m";
   static String BOLD = "\u001b[1m";
   static String FAINT = "\u001b[2m";
   static String ITALIC = "\u001b[3m";
   static String UNDERL = "\u001b[4m";
   static String BLINK = "\u001b[5m";
   static String HIDE = "\u001b[8m";
   static String STRIKE = "\u001b[9m";
   static String D_UNDERL = "\u001b[21m";
   static String exception = " -hoo+.                                                            -.\n  -d++ooo/.                                                     :++s/\n   :y/oo+:/oo/`                                           `-///oo++s \n    /h/++os+../++/--:////:::////:--::/+/`           `-:///::/oso:-y` \n     os-:o++so   `..`   ```.`  `.-.``` ./+////////++/-`./+ss+:-..y.  \n      ss:-/++o                                     `-/os++/-:--/y:   \n       /y/::-                                    `/o++o++:-.  -s`    \n        /s--.                                    :++o+o+:...`+o      \n        `d--`                                     `...-:.::.s:       \n        o+-.                                           -- :y`        \n       :y--`                                            . h          \n      .y--.oNNd:-`            `-/+--/:-                  o/          \n     `h--.  :mMMdo         `+dMMMMmmms:                  h`          \n     .y-.     .--          `/osyso/.                     .s          \n     -y-`        ..                                ``     d          \n     h-.        ++//::.`                          ``-`    :y         \n     y-`       `hsoooyso.           `...                 `y:         \n     s-`         +yy:`             `--.                  /y`         \n     -+.   `.   `.:-`            `.--`                   .d.         \n      y.    .:yhddddhy+:``    ``./--`          ``        +s:         \n      y.`     :ddyyyyyhddhs+//+os/-`          `..         :s         \n     s:-. `.   o+///:///+syhys+..-.             `         -y         \n     .s-. .-`  -/.` ` `       - `..                       .y         \n      -o-` `    .+:.-.`    `-+.                         `oo/         \n       o:::-------/oooooooo+:----------------------------:sd`        \n        -------------------------------------+o++++s++/:+ooo:        \n";
   static String entrada = "";
   static Scanner scanner;
   static long inicio;
   static long finaly;
   static long tiempo;
   private static boolean hasEscapeS;
   private static int colorSize;
   private static String colorSafe;
   static Pattern ansiExpresion;
   static Matcher matcher;

   public static String asciiBox(String var0, boolean var1, int var2) {
      String var3 = "";
      int var6 = 1;
      int var7 = 0;
      boolean var8 = false;
      var2 += 5;
      var0 = isWindows() ? addAccents(var0) : var0;
      var0 = var0.replaceAll("\n", "0x0a ");
      var0 = var0.replaceAll("\t", " ");
      String[] var9 = var0.split(" ");
      var9 = checkWordsLength(var9, var2 - 5);
      var3 = addFrame(si, sd, var3, var2 - 1, true);
      var3 = var3 + ld;
      ++var6;

      try {
         for(String var13 : var9) {
            if (var13.contains("0x0a")) {
               var8 = true;
               var13 = var13.replaceAll("0x0a", "");
            }

            if (length(var3) + length(var13) + 1 <= var2 * var6 - 3) {
               var3 = var3 + " " + var13;
            } else {
               if (var1) {
                  var3 = justify(var6, var2, var3);
               }

               var7 = var2 * var6 - 3 - length(var3);
               var3 = addBlanks(var3, var7);
               if (hasEscapeS) {
                  var3 = var3 + " " + RESET + ld + "\n" + ld + colorSafe + " " + var13;
               } else {
                  var3 = var3 + " " + ld + "\n" + ld + " " + var13;
               }

               ++var6;
            }

            if (var8) {
               var7 = var2 * var6 - 3 - length(var3);
               var3 = addBlanks(var3, var7);
               var3 = var3 + " " + ld + "\n" + ld;
               ++var6;
               var8 = false;
            }
         }

         var7 = var2 * var6 - 2 - length(var3);
         var3 = addBlanks(var3, var7);
         var3 = var3 + ld + "\n";
         ++var6;
         var3 = addFrame(ii, id, var3, var2 - 1, true);
      } catch (Exception var14) {
         show(asciiBox((String)exception, false, 69));
         show(asciiBox(var14.getMessage(), false, var14.toString().length()));
         var3 = "";
      }

      hasEscapeS = false;
      colorSize = 0;
      colorSafe = "";
      return var3;
   }

   public static String asciiBox(String var0, boolean var1) {
      return asciiBox((String)var0, var1, 90);
   }

   public static String asciiBox(Object var0, boolean var1, int var2) {
      return asciiBox(var0.toString(), var1, var2);
   }

   public static String asciiBox(Object var0, boolean var1) {
      return asciiBox((String)var0.toString(), var1, 90);
   }

   private static String justify(int var0, int var1, String var2) {
      String var3 = "  ";
      int var7 = 0;
      int var8 = var1 * var0 - 3 - length(var2);
      int var9 = var1 * (var0 - 1) + 2 + colorSize;

      for(int var10 = 0; var10 < var8; ++var10) {
         var7 = var2.indexOf(" ", var9);
         if (var7 == -1) {
            length(var2);
            var9 = var1 * (var0 - 1) + 2 + colorSize;
            var7 = var2.indexOf(" ", var9);
            if (var7 == -1) {
               break;
            }
         }

         String var6 = var2.substring(var9, var7);
         var9 += length(var6) + 3;
         String var4 = var2.substring(0, var7);
         String var5 = var2.substring(var7 + 1);
         var2 = var4 + var3 + var5;
      }

      return var2;
   }

   public static String nodify(String var0, int var1) {
      String[] var2 = var0.split("\n");
      String var3 = "";
      String var4 = "";
      String var5 = "";
      int var6 = length(var2[0]);
      switch (var1) {
         case 1:
            var5 = addFrameCP(ii, tn, id, st, var5, var6, true);

            for(int var15 = 0; var15 < var2.length - 1; ++var15) {
               var3 = var3 + var2[var15] + "\n";
            }

            var3 = var3 + var5;
            break;
         case 2:
            var4 = addFrameCP(si, ti, sd, st, var4, var6, true);
            var5 = addFrameCP(ii, tn, id, st, var5, var6, true);
            var3 = var3 + var4;

            for(int var14 = 1; var14 < var2.length - 1; ++var14) {
               var3 = var3 + var2[var14] + "\n";
            }

            var3 = var3 + var5;
            break;
         case 3:
            var4 = addFrameCP(si, ti, sd, st, var4, var6, true);
            var3 = var3 + var4;

            for(int var7 = 1; var7 < var2.length; ++var7) {
               var3 = var3 + var2[var7] + "\n";
            }
            break;
         default:
            var3 = var0;
      }

      return var3;
   }

   public static String addTreeEdge(String var0) {
      String[] var1 = var0.split("\n");
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      String var6 = "";
      var3 = var1[0].indexOf(ti);
      var4 = var1[0].lastIndexOf(ti);
      var5 = var4 - var3 + 1;
      if (var5 == 1) {
         var6 = var6 + ld;
      } else {
         var6 = addFrame(si, sd, var6, var5, false);
         char[] var2 = var1[0].substring(var3, var4 + 1).toCharArray();
         StringBuilder var7 = new StringBuilder(var6);

         for(int var8 = 1; var8 < var2.length - 1; ++var8) {
            if (Character.compare(ti.charAt(0), var2[var8]) == 0) {
               var7.replace(var8, var8 + 1, tn);
            }
         }

         var6 = var7.toString();
      }

      var6 = addOffSet(var6, var3);
      return var6 + "\n" + var0;
   }

   public static String treeConcat(String var0, String var1) {
      String var2 = "";
      String var3 = "";
      String var4 = "";
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      String[] var8 = var0.split("\n");
      String[] var9 = var1.split("\n");
      char[] var10 = var9[0].toCharArray();

      for(int var11 = 0; var11 < var10.length; ++var11) {
         if (!Character.isWhitespace(var10[var11])) {
            var5 = var11;
            break;
         }
      }

      for(int var25 = var10.length - 1; var25 >= 0; --var25) {
         if (!Character.isWhitespace(var10[var25])) {
            var6 = var25;
            break;
         }
      }

      var7 = var6 - var5 + 1;
      int var26 = var7 - length(var8[0]);
      int var12 = var26 / 2;
      int var13 = var26 - var12;
      var12 += var5;

      for(int var14 = 0; var14 < var8.length; ++var14) {
         var2 = addBlanks(var2, var12);
         var2 = var2 + var8[var14];
         var2 = addBlanks(var2, var13);
         var2 = var2 + "\n";
      }

      String[] var28 = var2.split("\n");
      char[] var15 = var28[var28.length - 1].toCharArray();
      char[] var16 = var9[0].toCharArray();
      char[] var17 = Arrays.copyOf(var16, var15.length);
      Arrays.fill(var17, var16.length, var15.length, ' ');
      StringBuilder var18 = new StringBuilder(var9[0]);

      for(int var19 = 0; var19 < var15.length; ++var19) {
         if (Character.compare(tn.charAt(0), var15[var19]) == 0) {
            if (Character.compare(st.charAt(0), var17[var19]) == 0) {
               var18.replace(var19, var19 + 1, ti);
            } else if (Character.compare(tn.charAt(0), var17[var19]) == 0) {
               var18.replace(var19, var19 + 1, dt);
            } else if (Character.isWhitespace(var17[var19])) {
               var18.replace(var19 - 1, var19 + 1, si + id);
            }
            break;
         }
      }

      String var29 = var18.toString() + "\n";

      for(int var20 = 1; var20 < var9.length; ++var20) {
         var29 = var29 + var9[var20] + "\n";
      }

      return var2 + var29;
   }

   public static String addOffSet(String var0, int var1) {
      String var2 = "";
      String var3 = "";
      var3 = addBlanks(var3, var1);
      String[] var4 = var0.split("\n");

      for(int var5 = 0; var5 < var4.length; ++var5) {
         if (var5 < var4.length - 1) {
            var2 = var2 + var3 + var4[var5] + "\n";
         } else {
            var2 = var2 + var3 + var4[var5];
         }
      }

      return var2;
   }

   public static String concatAscii(String var0, String var1, int var2, boolean var3) {
      String var4 = "";
      String var5 = "";
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      String[] var9 = var0.split("\n");
      String[] var10 = var1.split("\n");
      var6 = var9.length;
      if (!var0.equals("") && !var1.equals("")) {
         for(String var14 : var9) {
            if (length(var14) > var7) {
               var7 = length(var14);
            }
         }

         for(String var39 : var10) {
            if (length(var39) > var8) {
               var8 = length(var39);
            }
         }

         if (var9.length < var10.length) {
            int var23 = var10.length - var9.length;
            if (var3) {
               String[] var30 = (String[])Arrays.copyOf(var9, var10.length);
               var9 = var30;

               for(int var38 = 0; var38 < var9.length; ++var38) {
                  if (var9[var38] == null) {
                     var5 = "";
                     var9[var38] = addBlanks(var5, var7);
                  }
               }
            } else {
               String[] var29 = new String[var10.length];

               for(int var36 = 0; var36 < var23; ++var36) {
                  var5 = "";
                  var29[var36] = addBlanks(var5, var7);
               }

               for(int var37 = 0; var37 < var9.length; ++var37) {
                  var29[var37 + var23] = var9[var37];
               }

               var9 = var29;
            }
         } else if (var10.length < var9.length) {
            int var22 = var9.length - var10.length;
            if (var3) {
               String[] var28 = (String[])Arrays.copyOf(var10, var9.length);
               var10 = var28;

               for(int var35 = 0; var35 < var10.length; ++var35) {
                  if (var10[var35] == null) {
                     var5 = "";
                     var10[var35] = addBlanks(var5, var8);
                  }
               }
            } else {
               String[] var27 = new String[var9.length];

               for(int var33 = 0; var33 < var22; ++var33) {
                  var5 = "";
                  var27[var33] = addBlanks(var5, var8);
               }

               for(int var34 = 0; var34 < var10.length; ++var34) {
                  var27[var34 + var22] = var10[var34];
               }

               var10 = var27;
            }
         }

         for(int var24 = 0; var24 < var9.length; ++var24) {
            int var31 = var7 - length(var9[var24]);
            var9[var24] = addBlanks(var9[var24], var31);
         }

         for(int var25 = 0; var25 < var9.length; ++var25) {
            var4 = var4 + var9[var25];
            String var10000 = addBlanks(var4, var2);
            var4 = var10000 + var10[var25] + "\n";
         }
      } else {
         var4 = var0.equals("") ? var1 : var0;
      }

      return var4;
   }

   public static String addBlanks(String var0, int var1) {
      StringBuilder var2 = new StringBuilder(var0);
      if (var1 > 0) {
         for(int var3 = 0; var3 < var1; ++var3) {
            var2.append(" ");
         }
      }

      return var2.toString();
   }

   public static String addFrame(String var0, String var1, String var2, int var3, boolean var4) {
      StringBuilder var5 = new StringBuilder(var2);
      if (var3 > 1) {
         var5.append(var0);

         for(int var6 = 1; var6 < var3 - 1; ++var6) {
            var5.append(st);
         }

         var5.append(var1);
         if (var4) {
            var5.append("\n");
         }
      }

      return var5.toString();
   }

   public static String addFrameCP(String var0, String var1, String var2, String var3, String var4, int var5, boolean var6) {
      StringBuilder var7 = new StringBuilder();
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      var8 = var5 - 2 - 1;
      var9 = var8 / 2;
      var10 = var8 - var9;
      var7.append(var0);

      for(int var11 = 0; var11 < var9; ++var11) {
         var7.append(var3);
      }

      var7.append(var1);

      for(int var15 = 0; var15 < var10; ++var15) {
         var7.append(var3);
      }

      var7.append(var2);
      if (var6) {
         var7.append("\n");
      }

      StringBuilder var16 = new StringBuilder(var4);
      var16.append(var7);
      return var16.toString();
   }

   public static String addAccents(String var0) {
      try {
         HashMap var1 = new HashMap();
         var1.put("á", a);
         var1.put("é", e);
         var1.put("í", i);
         var1.put("ó", o);
         var1.put("ú", u);
         var1.put("ü", ud);
         var1.put("Á", aM);
         var1.put("É", eM);
         var1.put("Í", iM);
         var1.put("Ó", oM);
         var1.put("Ú", uM);
         var1.put("Ü", udM);
         var1.put("ñ", n);
         var1.put("Ñ", nM);
         var1.put("¡", am);
         var1.put("¿", qm);

        for(Map.Entry<?, ?> var3 : (Iterable<Map.Entry<?, ?>>) var1.entrySet()) {
            var0 = var0.replace((CharSequence)var3.getKey(), (CharSequence)var3.getValue());
         }
      } catch (Exception var4) {
         show(asciiBox((String)exception, false, 69));
         show(asciiBox((Object)var4, false, var4.toString().length()));
      }

      return var0;
   }

   private static boolean hasEscapeS(String var0) {
      matcher = ansiExpresion.matcher(var0);
      boolean var1 = matcher.find();
      if (var1) {
         hasEscapeS = true;
      }

      return var1;
   }

   public static int length(String var0) {
      colorSize = 0;
      if (hasEscapeS(var0)) {
         matcher = ansiExpresion.matcher(var0);
         colorSafe = "";

         for(boolean var1 = matcher.find(); var1; var1 = matcher.find()) {
            if (var1) {
               colorSize += matcher.group().length();
               colorSafe = matcher.group(0);
            }
         }

         return var0.length() - colorSize;
      } else {
         return var0.length();
      }
   }

   private static Boolean isWindows() {
      try {
         if (System.getProperty("os.name").contains("Windows")) {
            return true;
         }
      } catch (Exception var1) {
         show(asciiBox((String)exception, false, 69));
         show(asciiBox((Object)var1, false, var1.toString().length()));
      }

      return false;
   }

   public static void show(Object var0) {
      System.out.println(var0.toString());
   }

   public static void show(String var0) {
      System.out.println(addAccents(var0));
   }

   private static String[] checkWordsLength(String[] var0, int var1) {
      ArrayList var2 = new ArrayList();

      for(int var3 = 0; var3 < var0.length; ++var3) {
         if (length(var0[var3]) > var1 && !var0[var3].contains("0x0a")) {
            int var4 = length(var0[var3]) / var1;

            for(int var5 = 0; var5 < var4; ++var5) {
               String var6;
               if (var5 == 0) {
                  var6 = var0[var3].substring(var5 * (var1 - 1), (var5 + 1) * (var1 - 1) + colorSize);
               } else {
                  var6 = var0[var3].substring(var5 * (var1 - 1) + colorSize, (var5 + 1) * (var1 - 1) + colorSize);
               }

               var6 = var6 + "-";
               var2.add(var6);
            }

            if (length(var0[var3]) % var1 != 0) {
               length(var0[var3]);
               String var7 = var0[var3].substring(var4 * (var1 - 1) + colorSize, length(var0[var3]) + colorSize);
               var2.add(var7);
            }
         } else {
            var2.add(var0[var3]);
         }
      }

      return (String[])var2.toArray(new String[0]);
   }

   static {
      scanner = new Scanner(System.in);
      inicio = 0L;
      finaly = 0L;
      tiempo = 0L;
      hasEscapeS = false;
      colorSize = 0;
      colorSafe = "";
      ansiExpresion = Pattern.compile("\\e\\[(\\d)+(;(\\d)+)*m");
   }
}
