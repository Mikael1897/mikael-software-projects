package ec.edu.espe.controller; // <--- CAMBIO: Ruta completa

// IMPORTACIONES CORREGIDAS PARA ENCONTRAR TUS CLASES
import ec.edu.espe.model.Producto;
import ec.edu.espe.model.SistemaAlertas;
import ec.edu.espe.view.InventarioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioController {
    
    private final InventarioView vista;

    public InventarioController(InventarioView vista) {
        this.vista = vista;
        
        this.vista.addIngresarListener((ActionEvent e) -> {
            ingresarProducto();
        });
    }

    private void ingresarProducto() {
        try {
            String nombre = vista.getNombre();
            int cantidad = Integer.parseInt(vista.getCantidad());

            Producto nuevoProducto = new Producto(nombre, cantidad);

            vista.actualizarLista(nuevoProducto.toString());
            vista.limpiarCampos();

            // Aquí estaba el error "cannot find symbol"
            // Ahora funcionará porque importamos ec.edu.espe.model.SistemaAlertas arriba
            SistemaAlertas.getInstancia().verificarStock(nuevoProducto);

        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Por favor, ingrese una cantidad numérica válida.");
        } catch (Exception ex) {
            vista.mostrarMensaje("Error al ingresar el producto.");
        }
    }
}