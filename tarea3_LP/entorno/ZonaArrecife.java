package tarea3_LP.entorno;

import java.util.EnumSet;

import tarea3_LP.objetos.ItemTipo;

public class ZonaArrecife extends Zona {
    private int presion ;
    public ZonaArrecife(String nombre, int profundidadMin,
     int profundidadMax, EnumSet<ItemTipo> recursos, int nmin, int nmax){
        super(nombre, profundidadMin, profundidadMax, recursos, nmin, nmax);
        this.presion = 0;
    }
    @Override
    public int getPresion(){
        return this.presion;
    }

}
