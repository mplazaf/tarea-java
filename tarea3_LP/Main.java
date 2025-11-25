package tarea3_LP;
import java.util.Scanner;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import tarea3_LP.entorno.Zona;
import tarea3_LP.entorno.ZonaArrecife;
import tarea3_LP.entorno.ZonaProfunda;
import tarea3_LP.entorno.ZonaVolcanica;
import tarea3_LP.objetos.Item;
import tarea3_LP.objetos.ItemTipo;
import tarea3_LP.objetos.NaveExploradora;
import tarea3_LP.player.*;
public class Main {
   
   public static void main(String[] args) {
      boolean flag = true;
      List<Item> Inventario = new ArrayList<>();
      List<Item> Bodega = new ArrayList<>();
      Scanner input = new Scanner(System.in);
      NaveExploradora nave1 = new NaveExploradora(500, 0, Bodega);
      EnumSet<ItemTipo> items = EnumSet.allOf(ItemTipo.class);
      EnumSet<ItemTipo> itemsZonaArrecife = EnumSet.of(ItemTipo.cobre, ItemTipo.cuarzo, ItemTipo.silicio);
      EnumSet<ItemTipo> itemsZonaProfunda = EnumSet.of(ItemTipo.plata, ItemTipo.oro, ItemTipo.acero, 
      ItemTipo.diamante, ItemTipo.magnetita);
      EnumSet<ItemTipo> itemsZonaVolcanica = EnumSet.of(ItemTipo.sulfuro, ItemTipo.uranio, ItemTipo.titanio);

      ZonaArrecife arrecife = new ZonaArrecife("Arrecife", 
      0, 199, itemsZonaArrecife, 1, 3);

      ZonaProfunda profunda = new ZonaProfunda("ZonaProfunda",
       200, 999, itemsZonaProfunda, 2, 6);

      ZonaVolcanica volcanica = new ZonaVolcanica("ZonaVolcanica", 
      1000, 1500, itemsZonaVolcanica, 0, 0);

      List<Zona> zonas = new ArrayList<>();
      zonas.add(arrecife);
      zonas.add(profunda);
      zonas.add(volcanica);

      int stockPIEZATANQUE = 3;
      Oxigeno tanque = new Oxigeno(60,60); 
      Jugador jugador1 = new Jugador(arrecife, tanque,false, Inventario);
      while (flag) { 
         
         if(jugador1.getEstaEnlaNave() == false){
            System.out.println("-- EXTERIOR --");
            System.out.println("1) Subir o descender en profundidad"+
            "(a nado)");
            System.out.println("2) Explorar "); 
            System.out.println("3) Recoger recursos "); 
            System.out.println("4) Volver a la nave ");
            System.out.println("5) Ver profundidad actual "); 
            System.out.println("6) Ver oxígeno restante "); 
            System.out.println("7) Ver el inventario "); 
            System.out.println("0) Salir ");
            int opcion = input.nextInt(); 
            switch(opcion){
            case 0:
               flag = false;
               System.out.println("Saliendo...");
               break;
            case 1:
               System.out.println("Indica profundidad destino (rango zona "+ 
               jugador1.getZonaActual().dameRango()+" Tu d es: "+jugador1.d());
               int metros = input.nextInt();
               jugador1.descender(metros, arrecife, profunda, input);
               break;
            case 2:
               jugador1.getZonaActual().explorar(jugador1);  
               break;
               
            case 3:
               EnumSet<ItemTipo> recursosZonaActual = jugador1.getZonaActual().getRecursos();
               List<ItemTipo> recursosLista = new ArrayList<>(recursosZonaActual);
               Iterator<ItemTipo> recursosiIterator = recursosZonaActual.iterator();
               int i = 1;
               System.out.println("Que recurso deseas recoger?");
               while(recursosiIterator.hasNext()) {
                  System.out.println(i + ") " + recursosiIterator.next());
                  i++;
               }
               int recursoOpcion = input.nextInt();
               int cantidad = jugador1.cRecoleccion();
               int posicion = recursoOpcion - 1;
               if(posicion > recursosLista.size() - 1){
                  System.out.println("Esa opción es inválida");
               }
               else{
                  ItemTipo tipo = recursosLista.get(posicion);
                  jugador1.recolectar(tipo, cantidad);
               }
               break;
            case 4:
               System.out.println("Volviendo a la nave...");
               jugador1.setEstaEnlaNave(true);
               jugador1.getTanqueOxigeno().recargarCompleto();
               break;
            case 5:
               jugador1.verProfundidadActual();
               break;
            case 6:
               System.out.println("Oxigeno restante: "+ jugador1.getTanqueOxigeno().getOxigenoRestante());
               break;
            case 7:
               jugador1.verInventario();
               break;
            }
         }
         else{
            System.out.println("-- NAVE EXPLORADORA --");
            System.out.println("1) Ajustar anclaje de la nave (anclaje)");
            System.out.println("2) Guardar todos el inventario en la nave "); 
            System.out.println("3) Crear objetos "); 
            System.out.println("4) Moverse a otra zona ");
            System.out.println("5) Salir de la nave ");  
            System.out.println("6) Ver el inventario de la nave"); 
            int opcion = input.nextInt(); 
            switch(opcion){
            case 0:
               break;
            case 1:
               System.out.println("Indica nueva profundidad de anclaje (rango zona "+
               jugador1.getZonaActual().getProfundidadMin()+" - "+jugador1.getZonaActual().getProfundidadMax()+")");
               int metros = input.nextInt();
               if (!jugador1.getZonaActual().inRange(metros)){
                  System.out.println("¡No puedes anclar la nave fuera de zona!");
               }
               else{
                  nave1.setAnclaje(metros);
               }
               //System.out.println("La nave está anclada a "+ metros
               //+" [m]");
               break;
            case 2:
               nave1.transferirObjetos(jugador1.getInventario(), jugador1);
               break;
            case 3:
               
            case 4:
               System.out.println("A qué zona quieres moverte: ");
               int i = 0;
               while (i < zonas.size()){
                  System.out.println(i+1+") "+zonas.get(i).getNombre());
                  i++;
               }
               int zonaOpcion = input.nextInt();
               int posicionLista = zonaOpcion - 1;
               nave1.setAnclaje(zonas.get(posicionLista).getProfundidadMin());
               System.out.println("Te moviste a "+zonas.get(posicionLista).getNombre()+"\n");
               break;
            case 5:
               jugador1.setEstaEnlaNave(false);
               System.out.println("Saliendo de la nave...");
               jugador1.setProfundidadActual(nave1.getAnclaje());
               System.out.println("Profundidad actual: "+jugador1.getProfundidadActual()+ " [m]");
               break;
            case 6:
               nave1.verBodega();
               break;
            }
         }
         if(jugador1.getTanqueOxigeno().getOxigenoRestante() == 0){
            flag = false;
            System.out.println("¡Te quedaste sin oxígeno! : FIN DEL JUEGO");
         }
      }
      input.close();
   }
}
