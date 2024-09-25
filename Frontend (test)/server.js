// server.js
const express = require('express');
const mysql = require('mysql');
const cors = require('cors');

const app = express();
const PORT = 8080;

// Configurar CORS para permitir solicitudes desde el frontend
app.use(cors({
    origin: 'http://localhost:4200'
}));

// Configurar conexión a la base de datos
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'tu_usuario',
    password: 'tu_contraseña',
    database: 'SIRUMATEK'
});

// Conectar a la base de datos
connection.connect((err) => {
    if (err) {
        console.error('Error conectando a la base de datos:', err);
        return;
    }
    console.log('Conectado a la base de datos SIRUMATEK');
});

// Definir el endpoint para listar empleados
app.get('/api/empleados/listar', (req, res) => {
    const query = 'SELECT * FROM empleado';
    connection.query(query, (error, results) => {
        if (error) {
            console.error('Error al obtener empleados:', error);
            res.status(500).json({ error: 'Error al obtener empleados' });
        } else {
            res.json(results);
        }
    });
});

// Iniciar el servidor
app.listen(PORT, () => {
    console.log(`Servidor backend ejecutándose en http://localhost:${PORT}`);
});
