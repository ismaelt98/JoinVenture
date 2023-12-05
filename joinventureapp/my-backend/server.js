const express = require('express');
const cors = require('cors');
const app = express();
const port = 8080;


// Habilita CORS para todas las rutas
app.use(cors({
    origin: 'http://localhost:3000' // Solo permite solicitudes de este origen
  }));
  

app.use(express.json());

// Endpoint de prueba para el login
app.post('/users/login', (req, res) => {
    const { email, password } = req.body;
    // Aquí añadirías la lógica para verificar el usuario y la contraseña
    if (email === "test@test.com" && password === "123456") {
        res.json({ auth: true, token: "unTokenFicticio123" });
    } else {
        res.status(401).json({ auth: false, message: "Credenciales incorrectas" });
    }
});

// Endpoint de prueba para el registro
app.post('/users/register', (req, res) => {
    const { email, password } = req.body;
    // Aquí añadirías la lógica para registrar el usuario
    console.log(`Registrando al usuario: ${email}`);
    res.json({ id: 1, email }); // Simula una respuesta con el ID del usuario
});

app.listen(port, () => {
    console.log(`Servidor ejecutándose en http://localhost:${port}`);
});
