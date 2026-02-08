package ec.edu.espe.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SortView extends JFrame {
    
    private JTextField inputField;
    private JTextArea resultArea;
    private JButton sortButton;

    public SortView() {
        setTitle("Patrón Strategy - Ordenamiento MVC");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JLabel label = new JLabel("Ingresa números separados por comas (ej: 5, 2, 9, 1):");
        inputField = new JTextField(40);
        sortButton = new JButton("ORDENAR AHORA");
        resultArea = new JTextArea(15, 45);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(label);
        add(inputField);
        add(sortButton);
        add(scrollPane);
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void setResultText(String text) {
        resultArea.setText(text);
    }

    public void addSortButtonListener(ActionListener listener) {
        sortButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}