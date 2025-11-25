package tarea3_LP.objetos;

import java.util.List;
import tarea3_LP.player.Jugador;
import java.util.ArrayList;

public abstract class Vehiculo {
    private List<Item> bodega;
    public Vehiculo(){}
    public Vehiculo(List<Item> bodega){
        this.bodega = bodega;
    }
    public void transferirObjetos(List<Item> inventario, Jugador jugador){
        if(inventario.size() == 0){
            System.out.println("El inventario estaba vacío");
        }
        else {
            System.out.println("Transfiriendo Inventario...");
            for (Item itemInventario : inventario){
                if(!this.bodega.contains(itemInventario)){
                    this.bodega.add(itemInventario);
                }
                else{
                    for (Item itemBodega : this.bodega){
                        if(itemBodega.getTipo() == itemInventario.getTipo()){
                            itemBodega.aumentarCantidad(itemInventario.getCantidad());
                        }
                    }
                }  
            }

        jugador.vaciarInventario();
        }
    }
    public void verBodega(){
        if(this.bodega.size() == 0){
            System.out.println("La bodega está vacía");
        }
        else{
            System.out.println("Bodega:");
            for (Item item : this.bodega) {
                System.out.println(item.getTipo() + ": "+item.getCantidad());
            }
        }
    }

}
