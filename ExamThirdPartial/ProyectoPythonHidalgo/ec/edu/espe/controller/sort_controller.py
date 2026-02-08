from pymongo import MongoClient
from pymongo.errors import ConnectionFailure
from ec.edu.espe.model.sorting_model import BubbleSort, InsertionSort, QuickSort

class SortController:
    def __init__(self, view, context):
        self.view = view
        self.context = context
        
        # Conectar el botón de la vista con la función de ordenar
        self.view.sort_button.config(command=self.handle_sort)

        # --- CONFIGURACIÓN MONGODB ---
        # Tu URI corregida
        self.mongo_uri = "mongodb+srv://Mikael:Jaime2006@cluster0.fpyoe9m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
        self.db_name = "strategyHidalgo"
        self.collection_name = "arrayMikael"

    def handle_sort(self):
        try:
            text = self.view.get_input_text()
            if not text:
                self.view.show_error("Por favor ingresa números.")
                return

            # Convertir texto a lista de enteros
            try:
                numbers = [int(x.strip()) for x in text.split(",")]
            except ValueError:
                self.view.show_error("Solo ingresa números separados por comas.")
                return

            n = len(numbers)
            if n <= 1:
                self.view.show_error("Ingresa al menos 2 números.")
                return

            # --- LÓGICA DE SELECCIÓN (REGLAS DEL PROFE) ---
            if 2 <= n <= 5:
                self.context.set_sort_strategy(BubbleSort())
            elif 6 <= n <= 10:
                self.context.set_sort_strategy(InsertionSort())
            else: # Mayor a 10
                self.context.set_sort_strategy(QuickSort())

            # Ordenar
            sorted_numbers = self.context.sort(numbers)
            algo_name = self.context.get_strategy_name()

            # Mostrar resultado
            result_msg = f"Algoritmo: {algo_name}\n\nOriginal ({n}): {numbers}\n\nOrdenado: {sorted_numbers}"
            self.view.set_result_text(result_msg)
            self.view.show_message("Éxito", result_msg)

            # Guardar en Mongo
            self.save_to_mongo(numbers, sorted_numbers, algo_name, n)

        except Exception as e:
            self.view.show_error(f"Error inesperado: {str(e)}")

    def save_to_mongo(self, unsorted, sorted_arr, algo, size):
        try:
            client = MongoClient(self.mongo_uri)
            db = client[self.db_name]
            collection = db[self.collection_name]

            document = {
                "unsorted": str(unsorted),
                "size": size,
                "sort algorithm": algo,
                "sorted": str(sorted_arr)
            }

            collection.insert_one(document)
            print("Guardado en MongoDB correctamente.")
            client.close()
        except Exception as e:
            print(f"Error al guardar en Mongo: {e}")
            self.view.show_error(f"No se pudo guardar en la nube: {e}")