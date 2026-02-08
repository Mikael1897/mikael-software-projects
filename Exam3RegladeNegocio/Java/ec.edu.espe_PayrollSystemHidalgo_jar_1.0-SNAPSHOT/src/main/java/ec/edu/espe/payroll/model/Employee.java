package ec.edu.espe.payroll.model;

public class Employee {
    private String name;
    private double salary;
    private double tax;
    private double netSalary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Getters y Setters
    public String getName() { return name; }
    public double getSalary() { return salary; }
    
    public void setTax(double tax) { this.tax = tax; }
    public double getTax() { return tax; }
    
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
    public double getNetSalary() { return netSalary; }
}