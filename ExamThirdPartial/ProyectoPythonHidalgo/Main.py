import tkinter as tk
from ec.edu.espe.model.sorting_model import SortingContext
from ec.edu.espe.view.sort_view import SortView
from ec.edu.espe.controller.sort_controller import SortController

def main():
    root = tk.Tk()
    context = SortingContext()
    view = SortView(root)
    controller = SortController(view, context)
    root.mainloop()

if __name__ == "__main__":
    main()