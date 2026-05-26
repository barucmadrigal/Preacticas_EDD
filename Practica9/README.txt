Estructura de Datos - Practica9 
-------------------------------

Integrantes: 
-Baruc Santiago Madrigal Ronces. 
-Ramirez Espejel Arely. 

Como compilar y ejecutar el programa:
Compilar todas las clases Java en el proyecto que estan en la carpeta Practica, asegurándose de incluir el paquete EstructurasAux. Luego, ejecutar la clase GeneradorTorneo para generar la gráfica del torneo a partir del archivo de texto con los competidores. Asegúrate de que el archivo "participantes.txt" esté en el mismo directorio que el programa o proporciona la ruta correcta al archivo.
Además, para probar el funcionamiento de los árboles completos, ejecutar la clase MainArbolCompleto, que contiene ejemplos de uso de la clase ArbolBinarioCompleto. Esto permitirá verificar que las operaciones de inserción, eliminación y recorrido del árbol funcionan correctamente.

⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⠴⠖⠒⠚⠛⠛⠒⠚⠃⠻⠶⠶⣶⣶⣤⣤⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⠤⠖⡛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠻⢿⣿⣶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠹⣄⣠⡴⠋⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⢀⡿⠃⠀⠀⠀⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⣷⡄⠀⠀⠀⠀⠙⢿⣿⣿⣦⡀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣠⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⢠⠋⠀⠀⠀⢀⠞⠀⢳⠀⠀⠀⠀⠀⠀⠙⢿⣿⣷⣄⠀⠀⠀⠀
⠀⠀⠀⠀⠀⣰⡯⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠃⠐⠁⠀⠀⠀⠀⠋⣠⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣆⠀⠀⠀
⠀⠀⠀⠀⢰⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠊⠀⡄⠀⠀⠀⠀⠀⣠⠞⠁⢻⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⡆⠀⠀
⠀⠀⠀⢀⡏⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⠇⠀⠠⢂⣠⠞⠁⠀⠀⠈⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⡀⠀
⠀⠀⠀⡾⢀⡀⠀⢰⠋⠁⠀⠀⣀⣀⣀⣤⣴⣶⠾⠟⢋⣿⢀⣠⠴⠛⠁⠀⠀⠀⠀⠀⠸⣿⡄⠀⠀⠀⠀⠀⠀⠀⡀⠘⣿⣿⣧⠀
⠀⢴⣾⡟⢛⣇⣠⣯⣀⣤⣴⣿⠟⠛⠋⠉⠁⠀⠀⠀⠚⣋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣆⢀⠀⠀⠀⠀⠀⡜⠀⠀⣿⣿⣿⠀
⠀⠸⡏⠉⠉⣿⣿⢁⣾⣿⣿⣿⣷⠀⠀⠀⠀⠀⠐⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣦⡀⠈⢿⡆⠀⠀⠀⡼⠁⠀⣠⣿⣿⣿⠆
⠀⠀⢳⠀⣠⡿⠁⠸⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⡿⠀⠀⣧⠀⠀⢠⣁⣠⡬⠵⠿⠿⠿⡦
⠀⠀⢈⣿⠟⡵⠀⠀⠈⠙⠛⠋⠁⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⣿⣿⡿⠃⠀⠀⣿⠀⠀⡾⠉⠀⠀⠀⠀⢐⡼⠁
⠀⣴⡿⠋⡜⠁⠀⠀⠀⠀⠀⠀⢰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⠀⠀⠀⠀⠀⠀⠀⠀⣸⡇⠀⢰⡇⠀⠀⠀⠀⣠⡾⠁⠀
⠸⣿⡀⠘⡀⠀⠀⠀⠀⠀⠀⠀⠘⣷⣀⣀⣤⣾⣦⡀⠀⠀⠀⠀⣀⣴⠇⠀⠀⠀⠀⠀⠀⠀⢠⣿⠃⠀⣼⠁⠀⠀⢀⣴⣿⠁⠀⠀
⠀⢻⣧⡀⠣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠁⠀⠈⠛⠛⠛⠛⠛⠛⠁⠀⠀⠀⠀⠀⠀⠀⢠⢫⡟⠀⠀⡟⠀⢀⣴⣿⣿⡟⠀⠀⠀
⠀⠀⠙⢿⣄⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢎⡿⠀⢠⠀⢿⣾⣿⣿⣿⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠙⢿⣿⣷⣂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠋⣸⠁⠀⠇⡀⢸⣿⣿⣿⣿⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠹⣍⠛⢻⡷⣶⣦⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⡾⠿⢿⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠙⣦⡼⠁⠀⠀⠀⠉⢉⣩⡏⠀⠀⠀⠀⠒⣖⠒⠛⠛⠛⠋⠉⠀⠀⠀⠘⡇⢀⣼⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⣠⣶⡟⠉⠛⠒⠶⠶⠦⠶⢿⣻⣦⣄⡀⠀⠀⠀⠀⠀⠀⠷⠻⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⢿⣿⡀⠀⠀⢀⣠⣤⣶⠿⣛⣁⠍⣻⣦⣀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡿⢷⡿⠁⠐⣿⣷⣶⣾⣿⠿⠀⠁⠀⠁⠀⣼⠟⣡⡿⣧⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⡾⠁⣼⠇⠀⠀⠘⣷⡾⠋⠁⠀⠀⠀⠀⢀⠰⣿⡟⠉⠀⠈⣧⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⠃⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⢀⡿⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠘⣰⡏⠀⠀⠀⠀⠸⡆⠀⠀⠀⠀⠀⠀⠈⠻⠟⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⣼⣧⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣼⣇⣀⣀⣀⣀⣠⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀

Gracias. 