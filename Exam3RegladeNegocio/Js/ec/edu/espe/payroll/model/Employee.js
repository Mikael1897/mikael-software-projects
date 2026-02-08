class Employee {
    constructor(name, salary) {
        this.name = name;
        this.salary = parseFloat(salary);
        this.tax = 0;
        this.netSalary = 0;
    }
}

module.exports = Employee;