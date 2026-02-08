package ec.edu.espe.payroll.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrmPayroll extends JFrame {
   
    private JTextField txtName;
    private JTextField txtSalary;
    private JButton btnCalculate;
    private JTextArea txtResult;

    public FrmPayroll() {
        setTitle("Sistema de Nómina - Hidalgo");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        add(new JLabel("Nombre del Empleado:"));
        txtName = new JTextField(20);
        add(txtName);

        add(new JLabel("Sueldo Mensual ($):"));
        txtSalary = new JTextField(20);
        add(txtSalary);

        btnCalculate = new JButton("Calcular y Guardar");
        add(btnCalculate);

        txtResult = new JTextArea(8, 30);
        txtResult.setEditable(false);
        add(new JScrollPane(txtResult));
    }

    public String getNameInput() { return txtName.getText(); }
    public String getSalaryInput() { return txtSalary.getText(); }
    public void setResult(String msg) { txtResult.setText(msg); }
   
    public void addCalculateListener(ActionListener listen) { 
        btnCalculate.addActionListener(listen); 
    }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}