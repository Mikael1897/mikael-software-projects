package ec.edu.espe.view;

import ec.edu.espe.controller.InventarioController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InventarioView extends JFrame {
    
    // Componentes de la interfaz
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextArea areaInventario;
    private JButton btnIngresar; // Usamos la clase estándar JButton

    public InventarioView() {
        setTitle("Sistema de Gestión de Inventario MVC");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Panel de Entrada ---
        JPanel panelEntrada = new JPanel(new GridLayout(3, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Ingresar Artículo"));
        
        panelEntrada.add(new JLabel("Nombre del Producto:"));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);
        
        panelEntrada.add(new JLabel("Cantidad Actual:"));
        txtCantidad = new JTextField();
        panelEntrada.add(txtCantidad);
        
        // Inicialización correcta del botón
        btnIngresar = new JButton("Ingresar al Stock");
        panelEntrada.add(new JLabel("")); // Espaciador
        panelEntrada.add(btnIngresar);

        // --- Área de Resultados ---
        areaInventario = new JTextArea();
        areaInventario.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaInventario);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inventario Actual"));

        // Agregar paneles a la ventana
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // --- Métodos Getters (para que el Controller saque datos) ---
    public String getNombre() {
        return txtNombre.getText();
    }

    public String getCantidad() {
        return txtCantidad.getText();
    }

    // --- Métodos Setters (para que el Controller actualice la vista) ---
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    public void actualizarLista(String infoProducto) {
        areaInventario.append(infoProducto + "\n");
    }
    
    public void limpiarCampos() {
        txtNombre.setText("");
        txtCantidad.setText("");
        txtNombre.requestFocus(); // Pone el cursor en el nombre de nuevo
    }

    // --- Método Crucial para el Patrón Observer/MVC ---
    public void addIngresarListener(ActionListener listener) {
        // Aquí conectamos el listener que viene del controlador al botón
        btnIngresar.addActionListener(listener);
    }

    // --- MAIN ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Instanciar la Vista
            InventarioView vista = new InventarioView();
            
            // 2. Instanciar el Controlador (esto conecta todo)
            new InventarioController(vista);
            
            // 3. Mostrar la Vista
            vista.setVisible(true);
        });
    }
}