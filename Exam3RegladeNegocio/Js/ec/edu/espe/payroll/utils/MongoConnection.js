const { MongoClient } = require('mongodb');

class MongoConnection {
    constructor() {
        if (!MongoConnection.instance) {
            // TU CADENA DE CONEXIÓN EXACTA
            this.uri = "mongodb+srv://Mikael:Jaime2006@cluster0.fpyoe9m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            this.client = new MongoClient(this.uri);
            this.dbName = "payrollHidalgo";
            this.connectionPromise = this.client.connect(); // Conectamos una sola vez
            MongoConnection.instance = this;
            console.log("--> Instancia Singleton de MongoDB Creada <--");
        }
        return MongoConnection.instance;
    }

    async getCollection(collectionName) {
        await this.connectionPromise; // Esperar a que conecte
        return this.client.db(this.dbName).collection(collectionName);
    }
}

// Inicializamos la instancia
const instance = new MongoConnection();
Object.freeze(instance); // Congelamos para que nadie la modifique

module.exports = instance;