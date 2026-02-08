from ec.edu.espe.payroll.model.employee import Employee
from ec.edu.espe.payroll.utils.mongo_connection import MongoConnection

class PayrollController:
    def __init__(self, view):
        self.view = view
        # Conectamos el botón de la vista a la función de este controlador
        self.view.btn_calculate.config(command=self.calculate_and_save)

    def calculate_and_save(self):
        try:
            name = self.view.get_name()
            salary_str = self.view.get_salary()

            if not name or not salary_str:
                self.view.show_error("Llena todos los campos.")
                return

            salary = float(salary_str)
            
            # 1. Crear Modelo
            emp = Employee(name, salary)

            # --- REGLA DE NEGOCIO (Igual que en Java) ---
            if salary < 1000:
                tax_rate = 0.0
            elif salary <= 2500:
                tax_rate = 0.10
            else:
                tax_rate = 0.20

            tax_amount = salary * tax_rate
            net_salary = salary - tax_amount

            # Actualizar Modelo
            emp.tax = tax_amount
            emp.net_salary = net_salary

            # 2. Mostrar en Vista
            result = (f"Empleado: {emp.name}\n"
                      f"Sueldo Base: ${emp.salary}\n"
                      f"Impuesto ({int(tax_rate*100)}%): ${tax_amount}\n"
                      f"A Recibir: ${net_salary}")
            
            self.view.set_result(result)

            # 3. Guardar en MongoDB usando SINGLETON
            self.save_to_db(emp, tax_rate)

        except ValueError:
            self.view.show_error("El sueldo debe ser un número.")

    def save_to_db(self, emp, rate):
        try:
            # Llamamos al Singleton (No creamos conexión nueva)
            db_conn = MongoConnection()
            collection = db_conn.get_collection("payments")

            document = {
                "name": emp.name,
                "salary": emp.salary,
                "tax_rate": rate,
                "tax_amount": emp.tax,
                "net_salary": emp.net_salary
            }

            collection.insert_one(document)
            self.view.show_message("¡Guardado exitosamente en MongoDB!")
            
        except Exception as e:
            self.view.show_error(f"Error al guardar: {e}")