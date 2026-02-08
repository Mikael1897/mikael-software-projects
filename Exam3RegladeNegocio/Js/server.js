const express = require('express');
const bodyParser = require('body-parser');
const payrollController = require('./ec/edu/espe/payroll/controller/PayrollController');

const app = express();
const PORT = 3000;

// Configuración básica
app.use(bodyParser.urlencoded({ extended: true }));

// Rutas
app.get('/', payrollController.showForm);           // Mostrar formulario
app.post('/calculate', payrollController.calculateAndSave); // Acción del botón

// Arrancar Servidor
app.listen(PORT, () => {
    console.log(`---------------------------------------------------`);
    console.log(`🚀 Servidor JS corriendo en: http://localhost:${PORT}`);
    console.log(`---------------------------------------------------`);
});