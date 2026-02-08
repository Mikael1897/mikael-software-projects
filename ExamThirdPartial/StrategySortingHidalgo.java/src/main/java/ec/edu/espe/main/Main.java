package ec.edu.espe.main;

import ec.edu.espe.controller.SortController;
import ec.edu.espe.model.SortingContext;
import ec.edu.espe.view.SortView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
 
        SwingUtilities.invokeLater(() -> {
         
            SortingContext context = new SortingContext();
            
         
            SortView view = new SortView();
            
        
            new SortController(view, context);

          
            view.setVisible(true);
        });
    }
}