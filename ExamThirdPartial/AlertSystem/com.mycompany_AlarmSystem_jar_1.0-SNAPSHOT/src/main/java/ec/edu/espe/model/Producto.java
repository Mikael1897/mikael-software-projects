package ec.edu.espe.model;

public class Producto {
    private final String nombre;
    private int cantidad;

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    @Override
    public String toString() {
        return nombre + " (Stock: " + cantidad + ")";
    }
}