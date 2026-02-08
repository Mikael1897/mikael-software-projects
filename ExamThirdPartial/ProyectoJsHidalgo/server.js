// server.js
const express = require('express');
const bodyParser = require('body-parser');
const sortController = require('./ec/edu/espe/controller/SortController');

const app = express();
const port = 3000;

// Configuración para recibir datos del formulario HTML
app.use(bodyParser.urlencoded({ extended: true }));

// Rutas
app.get('/', sortController.showHome);      // Cuando entras a la web
app.post('/ordenar', sortController.processSort); // Cuando das clic al botón

// Iniciar servidor
app.listen(port, () => {
    console.log(`------------------------------------------------`);
    console.log(`✅ Servidor corriendo en: http://localhost:${port}`);
    console.log(`   Abre ese link en tu navegador para ver la GUI`);
    console.log(`------------------------------------------------`);
});