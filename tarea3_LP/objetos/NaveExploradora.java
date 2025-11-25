package tarea3_LP.objetos;

import java.util.List;

public class NaveExploradora extends Vehiculo implements AccesoProfundidad {
    private int profundidadSoportada;
    private int anclaje;
    private ModuloProfundidad modulo;
    public NaveExploradora(){
    }
    public NaveExploradora(int profundidadSoportada, int anclaje,
     List<Item> bodega){

        super(bodega);
        this.profundidadSoportada = profundidadSoportada;
        this.anclaje = anclaje;
        this.modulo = new ModuloProfundidad(1000);
    }

    public boolean puedeAcceder(int metros){
        if (metros > this.profundidadSoportada){
            return false;
        }
        else {
            return true;
        }
    }
    public void setAnclaje(int metros){
        if (this.modulo.getModuloActivado() == true){
            this.anclaje = metros;
            System.out.println("Se ancló la nave "+
            "a "+metros+" [m]");
        }
        else {
            if (!this.puedeAcceder(metros)){
                System.out.println("¡La nave no soporta esa profundidad!");
            }
            else {
                this.anclaje = metros;
                System.out.println("Se ancló la nave "+
                "a "+metros+" [m]");
            }
        }
        
    }
    public int getAnclaje(){
        return this.anclaje;
    }
    public class ModuloProfundidad{
        private boolean moduloActivado;
        private int profundidadExtra;
        public ModuloProfundidad(){}
        public ModuloProfundidad(int profundidadExtra){
            this.moduloActivado = false;
            //profundidad extra = constante
        }
        public void aumentarProfundidad(){}
        public boolean getModuloActivado(){
            return this.moduloActivado;
        }
    }
    
}

