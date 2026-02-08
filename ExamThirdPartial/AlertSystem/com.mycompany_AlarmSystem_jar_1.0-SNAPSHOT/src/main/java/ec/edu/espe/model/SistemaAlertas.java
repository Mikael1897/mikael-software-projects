package ec.edu.espe.model; // <--- CAMBIO IMPORTANTE: Ruta completa

import javax.swing.JOptionPane;

public class SistemaAlertas {
    
    private static SistemaAlertas instancia;
    private final int STOCK_MINIMO = 5;

    private SistemaAlertas() {}

    // Asegúrate de tener este método SOLO UNA VEZ
    public static SistemaAlertas getInstancia() {
        if (instancia == null) {
            instancia = new SistemaAlertas();
        }
        return instancia;
    }

    public void verificarStock(Producto producto) {
        if (producto.getCantidad() <= STOCK_MINIMO) {
            String mensaje = """
                             \u00a1ALERTA CR\u00cdTICA!
                             El producto '""" + producto.getNombre() + "' tiene stock bajo (" + producto.getCantidad() + ").\n" +
                             "Se necesita volver a llenar el stock desde el almacén inmediatamente.";
            
            JOptionPane.showMessageDialog(null, mensaje, "Sistema de Alertas de Stock", JOptionPane.WARNING_MESSAGE);
        }
    }
}