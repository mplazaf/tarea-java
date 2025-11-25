package tarea3_LP.objetos;

public class Item {
    private ItemTipo tipo;
    private int cantidad;
    public Item(){}
    public Item(ItemTipo tipo, int cantidad){
        this.tipo = tipo;
        this.cantidad += cantidad;
    }
    public void aumentarCantidad(int cantidad){
        this.cantidad += cantidad;
    }
    public ItemTipo getTipo(){
        return this.tipo;
    }
    public int getCantidad(){
        return this.cantidad;
    }
}
