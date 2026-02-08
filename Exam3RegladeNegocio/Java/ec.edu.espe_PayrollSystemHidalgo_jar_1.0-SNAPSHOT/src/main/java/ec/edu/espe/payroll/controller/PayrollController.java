package ec.edu.espe.payroll.controller;

import ec.edu.espe.payroll.model.Employee;
import ec.edu.espe.payroll.utils.MongoConnection;
import ec.edu.espe.payroll.view.FrmPayroll;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayrollController {
    private FrmPayroll view;

    public PayrollController(FrmPayroll view) {
        this.view = view;
        this.view.addCalculateListener(new CalculateListener());
    }

    class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getNameInput();
                double salary = Double.parseDouble(view.getSalaryInput());

                // 1. Crear Modelo
                Employee emp = new Employee(name, salary);

                // --- REGLA DE NEGOCIO (Impuesto a la Renta) ---
                // Si gana menos de 1000: 0%
                // Si gana entre 1000 y 2500: 10%
                // Si gana más de 2500: 20%
                double taxRate;
                if (salary < 1000) {
                    taxRate = 0.0;
                } else if (salary <= 2500) {
                    taxRate = 0.10;
                } else {
                    taxRate = 0.20;
                }

                double taxAmount = salary * taxRate;
                double netSalary = salary - taxAmount;

                // Actualizar Modelo
                emp.setTax(taxAmount);
                emp.setNetSalary(netSalary);

                // 2. Mostrar en Vista
                String result = "Empleado: " + emp.getName() + "\n" +
                                "Sueldo Base: $" + emp.getSalary() + "\n" +
                                "Impuesto (" + (taxRate * 100) + "%): $" + taxAmount + "\n" +
                                "A Recibir: $" + netSalary;
                view.setResult(result);

                // 3. Guardar en MongoDB usando SINGLETON
                saveToDatabase(emp, taxRate);

            } catch (NumberFormatException ex) {
                view.showMessage("Error: Ingrese un sueldo válido.");
            }
        }
    }

    private void saveToDatabase(Employee emp, double rate) {
        try {
            // AQUÍ LLAMAMOS AL SINGLETON (No hacemos new Connection)
            MongoCollection<Document> collection = MongoConnection.getInstance()
                    .getDatabase()
                    .getCollection("payments");

            Document doc = new Document("name", emp.getName())
                    .append("salary", emp.getSalary())
                    .append("tax_rate", rate)
                    .append("tax_amount", emp.getTax())
                    .append("net_salary", emp.getNetSalary());

            collection.insertOne(doc);
            view.showMessage("¡Guardado exitosamente en MongoDB!");

        } catch (Exception ex) {
            view.showMessage("Error de Base de Datos: " + ex.getMessage());
        }
    }
}