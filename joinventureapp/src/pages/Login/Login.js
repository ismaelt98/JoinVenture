import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css'; // Asegúrate de que la ruta al archivo CSS sea correcta

const Login = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [currentTitleIndex, setCurrentTitleIndex] = useState(0); // Asegúrate de que esta línea esté presente

  const navigate = useNavigate();

  const titles = ['JoinVenture: Transformando Ideas en Realidad!', 'Nuestra Misión: Unir Fuerzas para el Éxito'];
  const paragraphs = ['En el dinámico mundo de los negocios...', 'En JoinVenture, creemos en el poder de la colaboración...'];

  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTitleIndex((prevIndex) => (prevIndex + 1) % titles.length);
    }, 8000); // Cambia cada 8 segundos
    return () => clearInterval(intervalId);
  }, [titles.length]);

  const handleLogin = async (event) => {
    event.preventDefault();
    // Lógica para iniciar sesión...
    try {
      const response = await fetch('http://localhost:8080/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
      });
      const data = await response.json();
      if (data.auth) {
        // Guardar el token y posiblemente el ID de usuario en el almacenamiento local o en cookies
        localStorage.setItem('userToken', data.token);
        navigate('/rutaPrincipal'); // Asegúrate de que esta es la ruta correcta
      } else {
        // Manejar error de inicio de sesión
        console.error('Error de inicio de sesión');
      }
    } catch (error) {
      console.error('Error al conectar con la API', error);
    }
  };

  const handleRegister = async (event) => {
    console.log("Intentando registrar"); // Para depuración

    event.preventDefault();
    // Lógica para registro...
    if (password !== confirmPassword) {
      console.error('Las contraseñas no coinciden');
      return;
    }
    try {
      const response = await fetch('http://localhost:8080/users/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password /* Otros campos aquí */ })
      });
      const data = await response.json();
      if (data.id) {
        navigate('/rutaPrincipal'); // Asegúrate de que esta es la ruta correcta
      } else {
        // Manejar errores de registro
        console.error('Error de registro');
      }
    } catch (error) {
      console.error('Error al conectar con la API', error);
    }
  };

  return (
    <div className="login-wrapper">
      <h2>{titles[currentTitleIndex]}</h2>
      <p>{paragraphs[currentTitleIndex]}</p>
      {isLogin ? (
        <form onSubmit={handleLogin}>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Correo electrónico"
            required
          />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Contraseña"
            required
          />
          <button type="submit">Iniciar sesión</button>
        </form>
      ) : (
        <form onSubmit={handleRegister}>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Correo electrónico"
            required
          />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Contraseña"
            required
          />
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            placeholder="Confirmar contraseña"
            required
          />
          <button type="submit">Registrarse</button>
        </form>
      )}
      <button onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? 'Crear cuenta' : 'Volver al inicio de sesión'}
      </button>
    </div>
  );
};

export default Login;
