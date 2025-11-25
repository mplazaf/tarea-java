package tarea3_LP.entorno;

import java.util.EnumSet;

import tarea3_LP.objetos.ItemTipo;

public class ZonaVolcanica extends Zona{
    private int presion;
    private boolean planoEncontrado;

    public ZonaVolcanica(String nombre, int profundidadMin,
     int profundidadMax, EnumSet<ItemTipo> recursos, int nmin, int nmax){
        super(nombre, profundidadMin, profundidadMax, recursos, nmin, nmax);
        this.presion = 10;
        this.planoEncontrado = false;
    }
    public int getPresion() {
        return this.presion;
    }
}
