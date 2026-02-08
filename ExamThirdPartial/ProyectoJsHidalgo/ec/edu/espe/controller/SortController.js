// ec/edu/espe/controller/SortController.js
const { MongoClient } = require('mongodb');
const { BubbleSort, InsertionSort, QuickSort, SortingContext } = require('../model/SortingModel');
const path = require('path');

// --- TU CONEXIÓN (Ya configurada) ---
const uri = "mongodb+srv://Mikael:Jaime2006@cluster0.fpyoe9m.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
const dbName = "strategyHidalgo";
const collectionName = "arrayMikael";

const controller = {};

// 1. Mostrar la página web (GET)
controller.showHome = (req, res) => {
    res.sendFile(path.join(__dirname, '../view/index.html'));
};

// 2. Procesar el ordenamiento (POST)
controller.processSort = async (req, res) => {
    try {
        const inputString = req.body.numbers;
        // Convertir texto "5,2,3" a array [5, 2, 3]
        const numbers = inputString.split(',').map(num => parseInt(num.trim())).filter(n => !isNaN(n));
        const n = numbers.length;

        if (n <= 1) {
            return res.send("Error: Ingresa al menos 2 números.");
        }

        // --- SELECCIÓN DE ESTRATEGIA (Lógica del Profesor) ---
        const context = new SortingContext();

        if (n >= 2 && n <= 5) {
            context.setStrategy(new BubbleSort());
        } else if (n >= 6 && n <= 10) {
            context.setStrategy(new InsertionSort());
        } else { // Mayor a 10
            context.setStrategy(new QuickSort());
        }

        // Ordenar
        const sortedNumbers = context.sort(numbers);
        const algoName = context.getStrategyName();

        // --- GUARDAR EN MONGODB ---
        const client = new MongoClient(uri);
        try {
            await client.connect();
            const db = client.db(dbName);
            const collection = db.collection(collectionName);

            const document = {
                unsorted: numbers.toString(),
                size: n,
                "sort algorithm": algoName,
                sorted: sortedNumbers.toString()
            };

            await collection.insertOne(document);
            console.log("Guardado en MongoDB Atlas exitosamente.");

        } catch (dbError) {
            console.error("Error Mongo:", dbError);
            return res.send("Error al conectar con la Base de Datos: " + dbError.message);
        } finally {
            await client.close();
        }

        // Enviar respuesta al navegador
        res.send(`
            <div style="font-family: sans-serif; text-align: center; padding: 50px;">
                <h1>Resultado Exitoso</h1>
                <p><strong>Algoritmo Usado:</strong> ${algoName}</p>
                <p><strong>Original:</strong> [${numbers}]</p>
                <p><strong>Ordenado:</strong> [${sortedNumbers}]</p>
                <p><em>Datos guardados en MongoDB (strategyHidalgo / arrayMikael)</em></p>
                <br>
                <a href="/"><button style="padding:10px; background:#007bff; color:white; border:none; cursor:pointer;">Volver</button></a>
            </div>
        `);

    } catch (e) {
        console.error(e);
        res.send("Ocurrió un error en el servidor: " + e.message);
    }
};

module.exports = controller;