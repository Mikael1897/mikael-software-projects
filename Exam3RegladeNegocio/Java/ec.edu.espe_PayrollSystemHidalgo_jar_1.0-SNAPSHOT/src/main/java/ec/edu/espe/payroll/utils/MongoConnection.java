package ec.edu.espe.payroll.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    // 1. Instancia estática única (El Singleton)
    private static MongoConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    // TU CONEXIÓN (Ya con la clave correcta)
    private final String URI = "mongodb+srv://Mikael:Jaime2006@cluster0.fpyoe9m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    // 2. Constructor privado (Nadie puede entrar sin permiso)
    private MongoConnection() {
        try {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase("payrollHidalgo"); // Base de datos nueva
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    // 3. Método de acceso global
    public static MongoConnection getInstance() {
        if (instance == null) {
            instance = new MongoConnection();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}