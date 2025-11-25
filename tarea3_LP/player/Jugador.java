package tarea3_LP.player;

import java.util.List;
import java.util.Scanner;

import tarea3_LP.entorno.Zona;
import tarea3_LP.entorno.ZonaArrecife;
import tarea3_LP.entorno.ZonaProfunda;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import tarea3_LP.objetos.AccesoProfundidad;
import tarea3_LP.objetos.Item;
import tarea3_LP.objetos.ItemTipo;
import tarea3_LP.objetos.NaveExploradora;

public class Jugador implements AccesoProfundidad {
    private Oxigeno tanqueOxigeno;
    private List<Item> inventario;
    private Zona zonaActual;
    private int profundidadActual;
    private boolean tienePlanos;
    private NaveExploradora nave;
    private boolean trajeTermico;
    private boolean mejoraTanque;
    private boolean estaEnlaNave;
    public Jugador(){}
    public Jugador(Zona zonaActual, Oxigeno tanqueOxigeno, boolean estaEnlaNave, List<Item> inventario){
        this.tanqueOxigeno = tanqueOxigeno;
        this.zonaActual = zonaActual;
        this.estaEnlaNave = false;
        this.inventario = inventario;
    }
    public boolean puedeAcceder(int numero){
        return true;
    }
    public void verEstadoJugador(){

    }
    public void verProfundidadActual(){
        System.out.println("La profundidad actual es "+
        profundidadActual + "  [m]");
    }
    public void setProfundidadActual(int metros){
        this.profundidadActual = metros;
    }
    public int getProfundidadActual(){
        return this.profundidadActual;
    }
    public Oxigeno getTanqueOxigeno(){
        return this.tanqueOxigeno;
    }
    public float d(){
        int numerador = this.profundidadActual -
         this.zonaActual.getProfundidadMin();
        int denominador = Math.max(1, this.zonaActual.getProfundidadMax()-
        this.zonaActual.getProfundidadMin());
        float d = (float)numerador / denominador;
        return d;
    }
    public int presD(){
        if (mejoraTanque == true){
            return 0; 
        }
        else {
            String nombre = this.zonaActual.getNombre();
            if (nombre == "ZonaProfunda"){
                return (int)(this.zonaActual.getPresion() + 6*this.d());  //no se usa el 10 arbitrario como pide el pdf
            }
            else if(nombre == "ZonaVolcanica") {
                return 999999999;
            }
            else {
                return 0;
            }
        }
    }
    public void descender(int metros, ZonaArrecife arrecife, ZonaProfunda zonaprofunda, Scanner Input){
        int profReal = metros;
        
        int costo = 0;
        int profMinActual = this.getZonaActual().getProfundidadMin();
        int profMaxActual = this.getZonaActual().getProfundidadMax();
        if(!(metros >= profMinActual && metros <= profMaxActual )){
            System.out.println("¿Desea cambiar de zona? ");
            if(Input.hasNextLine()){
                Input.nextLine();
            }
            String opcion = Input.nextLine();
            if (opcion.equalsIgnoreCase("si")){
                if (arrecife.inRange(metros)){
                    int dif1 = Math.abs(arrecife.getProfundidadMin() - this.getProfundidadActual());
                    int dif2 = Math.abs(arrecife.getProfundidadMax() - this.getProfundidadActual());
                    if (Math.min(dif1, dif2) == dif1){
                        profReal = 0;
                    }
                    else{
                        profReal = 199;
                    }
                    costo = (int)((3+3*this.d())*Math.abs(profReal - 
                    this.profundidadActual))/50;
                    arrecife.entrar(this);
                }
                else if (zonaprofunda.inRange(metros)){
                    int dif1 = Math.abs(zonaprofunda.getProfundidadMin() - this.getProfundidadActual());
                    int dif2 = Math.abs(zonaprofunda.getProfundidadMax() - this.getProfundidadActual());
                    if (Math.min(dif1, dif2) == dif1){
                        profReal = 200;
                    }
                    else{
                        profReal = 999;
                    }
                    costo = (int)((3+3*this.d())*Math.abs(profReal - 
                    this.profundidadActual))/50;
                    zonaprofunda.entrar(this);
                }
                else if (metros >= 1000 && metros <= 1500){
                }
                this.tanqueOxigeno.consumirO2(costo);
                System.out.println("Te moviste a " + profReal + 
                " [m]  Consumo de O2 = "+costo + ", ahora queda "+
                this.tanqueOxigeno.getOxigenoRestante());
            }
            else{
                System.out.println("No te moviste.");
            }
        }
        else {
            costo = (int)((3+3*this.d())*Math.abs(profReal - 
            this.profundidadActual))/50;
            this.profundidadActual = profReal;
            this.tanqueOxigeno.consumirO2(costo);
            System.out.println("Te moviste a " + profReal + 
            " [m]  Consumo de O2 = "+costo + ", ahora queda "+
            this.tanqueOxigeno.getOxigenoRestante());
        }
        /* 
        this.tanqueOxigeno.consumirO2(costo);
        System.out.println("Te moviste a " + profReal + 
        " [m]  Consumo de O2 = "+costo + ", ahora queda "+
        this.tanqueOxigeno.getOxigenoRestante());
        */
    }
    public Zona getZonaActual(){
        return this.zonaActual;
    }
    public void setZonaActual(Zona zonaActual){
        this.zonaActual = zonaActual;
    }
    public boolean getEstaEnlaNave(){
        return this.estaEnlaNave;
    }
    public void setEstaEnlaNave(boolean opcion){
        this.estaEnlaNave = opcion;
    }
    public void verInventario(){               
        if(this.inventario.size() == 0){
            System.out.println("El inventario está vacío");
        }
        else{
            System.out.println("Inventario:");
            for (Item item : this.inventario) {
                System.out.println(item.getTipo() + ": "+item.getCantidad());
            }
        }
    }
    public boolean estaEnelInventario(ItemTipo tipo){
        List<Item> lista = this.inventario;
        if(lista != null){
            for (Item item : lista){
                if (item.getTipo() == tipo){
                    return true;
                }
            }
        }    
        return false;
    }  
    public List<Item> getInventario(){
        return this.inventario;
    }
    public int cRecoleccion(){
        int nmin = this.zonaActual.getNmin();
        int nmax = this.zonaActual.getNmax();
        int nD = (int)(nmin + (nmax - nmin)* this.d());
        int n = Math.max(1, nD);
        return n;
    }
    public void recolectar(ItemTipo tipo, int cantidad){
        int OxConsumido = (int)(10 +6*this.d() + this.presD());
        if(!this.estaEnelInventario(tipo)) {
                  Item itemRecolectado = new Item(tipo, cantidad);
                  this.inventario.add(itemRecolectado);
               }
        else{
            for (Item item : this.getInventario()){
                if (item.getTipo() == tipo){
                    item.aumentarCantidad(cantidad);
                }
            }
        }
        this.getTanqueOxigeno().consumirO2(OxConsumido);
        System.out.println("Se ha recolectado "+cantidad+" de "+ tipo+". Se consumió "+OxConsumido+" Ahora: "+this.getTanqueOxigeno().getOxigenoRestante());
    }
    public void explorarAccion(ItemTipo tipo, int cantidad){
        if(!this.estaEnelInventario(tipo)) {
            Item itemRecolectado = new Item(tipo, cantidad);
            this.inventario.add(itemRecolectado);
        }
        else{
            for (Item item : this.getInventario()){
                if (item.getTipo() == tipo){
                    item.aumentarCantidad(cantidad);
                }
            }
        }
    }
    public void vaciarInventario(){
        this.inventario.clear();
    }
}
