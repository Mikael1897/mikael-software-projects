const Employee = require('../model/Employee');
const mongoSingleton = require('../utils/MongoConnection'); // Importamos el Singleton
const path = require('path');

const controller = {};

// 1. Mostrar la Vista (GET)
controller.showForm = (req, res) => {
    res.sendFile(path.join(__dirname, '../view/index.html'));
};

// 2. Procesar Datos (POST)
controller.calculateAndSave = async (req, res) => {
    try {
        const { name, salary } = req.body;
        
        // Crear Modelo
        const emp = new Employee(name, salary);

        // --- REGLA DE NEGOCIO (Impuesto a la Renta) ---
        let taxRate = 0;
        if (emp.salary < 1000) {
            taxRate = 0.0;     // 0%
        } else if (emp.salary <= 2500) {
            taxRate = 0.10;    // 10%
        } else {
            taxRate = 0.20;    // 20%
        }

        const taxAmount = emp.salary * taxRate;
        const netSalary = emp.salary - taxAmount;

        // Actualizar Modelo
        emp.tax = taxAmount;
        emp.netSalary = netSalary;

        // --- GUARDAR EN MONGODB USANDO SINGLETON ---
        // Fíjate que no hacemos "new MongoClient", usamos la instancia única
        const collection = await mongoSingleton.getCollection('payments');
        
        await collection.insertOne({
            name: emp.name,
            salary: emp.salary,
            tax_rate: taxRate,
            tax_amount: emp.tax,
            net_salary: emp.netSalary,
            date: new Date()
        });

        // Responder al usuario
        res.send(`
            <div style="text-align:center; font-family:Arial; padding:50px;">
                <h1>✅ Cálculo Exitoso</h1>
                <p><strong>Empleado:</strong> ${emp.name}</p>
                <p><strong>Sueldo Base:</strong> $${emp.salary}</p>
                <p><strong>Impuesto (${taxRate * 100}%):</strong> $${taxAmount}</p>
                <hr>
                <h3>💰 A Recibir: $${netSalary}</h3>
                <p><em>Guardado en MongoDB usando Singleton</em></p>
                <a href="/"><button>Volver</button></a>
            </div>
        `);

    } catch (error) {
        console.error(error);
        res.send("Error al procesar: " + error.message);
    }
};

module.exports = controller;