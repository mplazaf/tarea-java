package tarea3_LP.entorno;

import java.util.List;
import java.util.Random;

//import javax.print.DocFlavor.STRING;
import java.util.ArrayList;
import java.util.EnumSet;

import tarea3_LP.player.Jugador;
import tarea3_LP.objetos.ItemTipo;

public abstract class Zona {
    public String nombre;
    private int profundidadMin;
    private int profundidadMax;
    private int nmin;
    private int nmax;
    private EnumSet<ItemTipo> recursos;
    
    public Zona(String nombre, int profundidadMin, 
    int profundidadMax, EnumSet<ItemTipo> recursos, int nmin, int nmax){
        this.nombre = nombre;
        this.profundidadMin = profundidadMin;
        this.profundidadMax = profundidadMax;
        this.recursos = recursos;
        this.nmin = nmin;
        this.nmax = nmax;
        }
    public String getNombre(){
        return this.nombre;
    }
    public abstract int getPresion();
    
    public void entrar(Jugador jugador){
        int dif1 = Math.abs(this.profundidadMin - jugador.getProfundidadActual());
        int dif2 = Math.abs(this.profundidadMax - jugador.getProfundidadActual());
        if (Math.min(dif1, dif2) == dif1){
            jugador.setZonaActual(this);
            jugador.setProfundidadActual(this.profundidadMin);
            System.out.println("Entrando a "+this.nombre+"...");
            System.out.println("Profundidad actual: "+jugador.getProfundidadActual());
        }
        else{
            jugador.setZonaActual(this);
            jugador.setProfundidadActual(this.profundidadMax);
            System.out.println("Entrando a "+this.nombre+"...");
            System.out.println("Profundidad actual: "+jugador.getProfundidadActual());
        }
    }
    public String dameRango(){
        String rango = "[" + profundidadMin + " - " + profundidadMax + "]";
        return rango;
    }
    public int getProfundidadMin(){
        return this.profundidadMin;
    }
    public int getProfundidadMax(){
        return this.profundidadMax;
    }
    public int getNmin(){
        return this.nmin;
    }
    public int getNmax(){
        return this.nmax;
    }
    public EnumSet<ItemTipo> getRecursos(){
        return this.recursos;
    }
    public void setRecursosZona(EnumSet<ItemTipo> recursos){
        this.recursos = recursos;
    }
    public boolean inRange(int numero){
        return (numero >= this.profundidadMin && numero <= this.profundidadMax);

    }
    public void explorar(Jugador jugador){
        System.out.println("Fuiste a buscar un objeto especial...");
            int OxConsumido = (int)(10 +6*jugador.d() + jugador.presD());
            if(Math.random() < 0.3 && jugador.getZonaActual().getNombre() == "Arrecife"){
                ItemTipo tipo = ItemTipo.PIEZA_TANQUE;
                jugador.explorarAccion(tipo, 1);
                System.out.println("¡Encontraste 1"+" de "+ tipo+" !");
               }
            else{
                EnumSet<ItemTipo> recursosZonaActual = jugador.getZonaActual().getRecursos();
                List<ItemTipo> recursosLista = new ArrayList<>(recursosZonaActual);
                int tamanoLista = recursosLista.size();
                Random random = new Random();
                int posicionRandom = random.nextInt(0,tamanoLista);
                int cantidad = jugador.cRecoleccion();
                ItemTipo tipoSacado = recursosLista.get(posicionRandom);
                jugador.explorarAccion(tipoSacado, cantidad);
                System.out.println("Se ha recolectado "+ cantidad + " de "+tipoSacado);
               }
            jugador.getTanqueOxigeno().consumirO2(OxConsumido);
            System.out.println("Se consumio "+ OxConsumido+ " de O2. Ahora: "+ 
            jugador.getTanqueOxigeno().getOxigenoRestante());
    }

}
