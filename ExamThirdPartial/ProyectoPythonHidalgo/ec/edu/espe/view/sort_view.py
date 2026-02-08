import tkinter as tk
from tkinter import messagebox, scrolledtext

class SortView:
    def __init__(self, root):
        self.root = root
        self.root.title("Patrón Strategy - Python Hidalgo")
        self.root.geometry("500x400")

        # Etiqueta
        self.label = tk.Label(root, text="Ingresa números separados por comas (ej: 5, 2, 9):")
        self.label.pack(pady=10)

        # Campo de texto
        self.entry = tk.Entry(root, width=50)
        self.entry.pack(pady=5)

        # Botón
        self.sort_button = tk.Button(root, text="ORDENAR AHORA", bg="#4CAF50", fg="white")
        self.sort_button.pack(pady=10)

        # Área de resultados
        self.result_area = scrolledtext.ScrolledText(root, width=50, height=10)
        self.result_area.pack(pady=10)

    def get_input_text(self):
        return self.entry.get()

    def set_result_text(self, text):
        self.result_area.delete(1.0, tk.END)
        self.result_area.insert(tk.END, text)

    def show_message(self, title, message):
        messagebox.showinfo(title, message)

    def show_error(self, message):
        messagebox.showerror("Error", message)