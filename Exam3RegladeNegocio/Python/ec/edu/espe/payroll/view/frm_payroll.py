import tkinter as tk
from tkinter import messagebox

class FrmPayroll(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Sistema de Nómina - Hidalgo (Python)")
        self.geometry("400x350")
        self.eval('tk::PlaceWindow . center') # Intentar centrar

        # Variables para los campos
        self.name_var = tk.StringVar()
        self.salary_var = tk.StringVar()

        # UI
        tk.Label(self, text="Nombre del Empleado:").pack(pady=5)
        tk.Entry(self, textvariable=self.name_var).pack(pady=5)

        tk.Label(self, text="Sueldo Mensual ($):").pack(pady=5)
        tk.Entry(self, textvariable=self.salary_var).pack(pady=5)

        # Botón (El controlador le asignará la acción)
        self.btn_calculate = tk.Button(self, text="Calcular y Guardar", bg="#4CAF50", fg="white")
        self.btn_calculate.pack(pady=20)

        # Área de resultados
        self.txt_result = tk.Text(self, height=8, width=40)
        self.txt_result.pack(pady=10)

    # Getters y Setters visuales
    def get_name(self): return self.name_var.get()
    def get_salary(self): return self.salary_var.get()
    
    def set_result(self, text):
        self.txt_result.delete(1.0, tk.END)
        self.txt_result.insert(tk.END, text)

    def show_message(self, msg):
        messagebox.showinfo("Información", msg)
    
    def show_error(self, msg):
        messagebox.showerror("Error", msg)