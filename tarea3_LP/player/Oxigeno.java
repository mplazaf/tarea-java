package tarea3_LP.player;

public class Oxigeno {
    private int oxigenoRestante; 
    private int capacidadMaxima;
    public Oxigeno(){}
    public Oxigeno(int oxigenoRestante, int capacidadMaxima){
        this.oxigenoRestante = oxigenoRestante;
        this.capacidadMaxima = capacidadMaxima;
    }
    public void consumirO2(int unidades){
        this.oxigenoRestante -= unidades;
        if (this.oxigenoRestante < 0){
            this.oxigenoRestante = 0;
        }
    }
    public void recargarCompleto(){
        this.oxigenoRestante = capacidadMaxima;
        System.out.println("Se lleno el tanque de oxigeno!. Ahora: "+this.oxigenoRestante);
    }
    public int getOxigenoRestante(){
        return this.oxigenoRestante;
    }
}
