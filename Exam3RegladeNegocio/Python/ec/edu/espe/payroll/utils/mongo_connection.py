from pymongo import MongoClient

class MongoConnection:
    _instance = None
    _client = None
    _db = None

    # El patrón Singleton en Python: __new__ garantiza una sola instancia
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(MongoConnection, cls).__new__(cls)
            try:
                # TU CADENA DE CONEXIÓN EXACTA
                uri = "mongodb+srv://Mikael:Jaime2006@cluster0.fpyoe9m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
                cls._client = MongoClient(uri)
                cls._db = cls._client["payrollHidalgo"]
                print("Conexión Singleton establecida.")
            except Exception as e:
                print(f"Error de conexión: {e}")
        return cls._instance

    def get_collection(self, collection_name):
        return self._db[collection_name]