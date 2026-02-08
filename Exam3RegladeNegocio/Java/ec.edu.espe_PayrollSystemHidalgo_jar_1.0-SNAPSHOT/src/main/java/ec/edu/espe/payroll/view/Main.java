package ec.edu.espe.payroll.view;

import ec.edu.espe.payroll.controller.PayrollController;

public class Main {
    public static void main(String[] args) {
       
        FrmPayroll view = new FrmPayroll();
        
       
        new PayrollController(view); 
        
        
        view.setLocationRelativeTo(null); // Centrar
        view.setVisible(true);
    }
}