MIGUEL ANGEL ENRIQUE PLAZA FERNANDEZ 202473058-4
-Se agrega el atributo boolean estaEnlaNave
-Consideración: ¿Que pasa si se pone una profundidad
de anclaje mayor a la zona? ¿hay que validar? Ver en main
- Se agrega el atributo nmin y nmax en zona
-En main se pasa el EnumSet temporalmente a una lista para
poder hacer operaciones que requieran posiciones.
-String nombre de cada zona:
    Zona arrecife: "ZonaArrecife"
    Zona profunda: "ZonaProfunda"
    Zona volcanica: "ZonaVolcanica"
-Implementar el metodo tieneItem en Jugador que tome (itemtipo, arraylist inventario)
y haga un for que revise si tiene de ese tipo ->boolean
-Se asume que el ususario va a poner inputs correctos, por ejemplo cuando se pide
seleccionar un recurso.
- Se asume que al explorar no se pierde oxigeno extra por haber recolectado
-El metodo de subir/bajar se llama descender
- d es entero y por lo tanto si da entre 0 y 1 queda como 0
---Cambios importantes:
    Jugador-> 47 : int to float
    Jugador-> 62 : casteo de (int)
    Jugador-> 87 : casteo de (int)
    Jugador-> 100 : casteo de (int)
    Jugador-> 109 : casteo de (int)
    Jugador-> 158 : casteo de (int)
    Jugador-> 163 : casteo de (int)
-Cuando se pregunta el cambiar de zona, debe poner "si" sin tilde
-Se esta asumiendo que el elegir destino para ir a alguna zona te lleva a la prof minima de la zona.
-Se entiende que si no se tiene modulo profundidad, la nave no puede ser anclada a más de 500 m .
-Se quizo implementar que si se intenta anclar la nave a una profundidad que quede en una Zona
distinta solo se le niegue la opción al jugador.