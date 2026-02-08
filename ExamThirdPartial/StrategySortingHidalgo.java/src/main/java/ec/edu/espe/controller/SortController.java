package ec.edu.espe.controller;

import ec.edu.espe.model.*;
import ec.edu.espe.view.SortView;
import org.bson.Document;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortController {
    private SortView view;
    private SortingContext context;
    
private static final String MONGO_URI = "mongodb+srv://Mikael:Jaime2006@cluster0.fpyoe9m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
   
    private static final String DB_NAME = "strategyHidalgo"; 
    private static final String COLLECTION_NAME = "arrayMikael";

    public SortController(SortView view, SortingContext context) {
        this.view = view;
        this.context = context;
        
        this.view.addSortButtonListener(new SortListener());
    }

    class SortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
               
                String text = view.getInputText();
                if(text.isEmpty()) {
                    view.showMessage("Por favor ingresa números.");
                    return;
                }
                
           
                String[] parts = text.split(",");
                int[] numbers = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    numbers[i] = Integer.parseInt(parts[i].trim());
                }
                
                int n = numbers.length;
                
                if (n <= 1) {
                    view.showMessage("Debes ingresar al menos 2 números para ordenar.");
                    return;
                }
               
                if (n >= 2 && n <= 5) {
                    context.setSortStrategy(new BubbleSort());
                } 
               
                else if (n >= 6 && n <= 10) {
                    context.setSortStrategy(new InsertionSort());
                } 
               
                else { 
                    context.setSortStrategy(new QuickSort());
                }

                
                int[] sortedNumbers = context.sort(numbers);
                String algorithmUsed = context.getCurrentStrategyName();

            
                String resultMsg = "Algoritmo seleccionado automáticamente: " + algorithmUsed + "\n\n" +
                                   "Arreglo Original (Tamaño " + n + "):\n" + Arrays.toString(numbers) + "\n\n" +
                                   "Arreglo Ordenado:\n" + Arrays.toString(sortedNumbers);
                
                view.setResultText(resultMsg);
               
                view.showMessage(resultMsg);

               
                saveToMongoDB(numbers, sortedNumbers, algorithmUsed, n);

            } catch (NumberFormatException ex) {
                view.showMessage("Error: Asegúrate de ingresar solo números separados por comas válidas.");
            } catch (Exception ex) {
                ex.printStackTrace();
                view.showMessage("Ocurrió un error inesperado: " + ex.getMessage());
            }
        }
    }

    private void saveToMongoDB(int[] unsorted, int[] sorted, String algo, int size) {
        try (MongoClient mongoClient = MongoClients.create(MONGO_URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document doc = new Document("unsorted", Arrays.toString(unsorted))
                    .append("size", size)
                    .append("sort algorithm", algo)
                    .append("sorted", Arrays.toString(sorted));

            collection.insertOne(doc);
            System.out.println("Registro guardado exitosamente en MongoDB Atlas.");
        } catch (Exception e) {
            view.showMessage("Error al conectar con MongoDB. Revisa tu conexión a internet o la cadena de conexión.\nDetalle: " + e.getMessage());
            e.printStackTrace();
        }
    }
}