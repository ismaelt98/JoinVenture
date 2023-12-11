import React, { useState } from 'react';
import './Register.css'; // Asegúrate de que la ruta sea correcta
import { Link, useNavigate } from 'react-router-dom'; // Importa useNavigate de react-router-dom si quieres redirigir después del registro

const Register = () => {
  // Estado para cada campo del formulario
  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const navigate = useNavigate(); // Para redirigir después del registro

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Aquí puedes añadir lógica para validar las entradas, como verificar si las contraseñas coinciden

    try {
      const formData = new FormData();
      formData.append('name', name);
      formData.append('surname', surname);
      formData.append('email', email);
      formData.append('password', password);

      const response = await fetch("http://localhost:8080/users/register", {
        method: 'POST',
        body: formData,
        redirect: 'follow'
      });

      if (response.ok) {
        console.log("Registro exitoso");
        // Resetear los estados de los inputs
        setName('');
        setSurname('');
        setEmail('');
        setPassword('');
        setConfirmPassword('');

        // Opcional: Redirigir al usuario después del registro
        navigate('/login');
      } else {
        alert("ERROR EN EL REGISTRO");
      }
    } catch (error) {
      console.error('error', error);
    }
  };

  return (
    <div className="container">
      <form className="form" id="miFormulario" onSubmit={handleSubmit}>
        <h2>Crear Tu Cuenta</h2>
        <div className="form-group">
          <input type="text" id="name" name="name" value={name} onChange={e => setName(e.target.value)} required />
          <label htmlFor="name" className="floating-label">Nombre</label>
        </div>
        <div className="form-group">
          <input type="text" id="surname" name="surname" value={surname} onChange={e => setSurname(e.target.value)} required />
          <label htmlFor="surname" className="floating-label">Apellido</label>
        </div>
        <div className="form-group">
          <input type="email" id="email" name="email" value={email} onChange={e => setEmail(e.target.value)} required />
          <label htmlFor="email" className="floating-label">Email</label>
        </div>
        <div className="form-group">
          <input type="password" id="password" name="password" value={password} onChange={e => setPassword(e.target.value)} required />
          <label htmlFor="password" className="floating-label">Contraseña</label>
        </div>
        <div className="form-group">
          <input type="password" id="confirm-password" name="confirm-password" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} required />
          <label htmlFor="confirm-password" className="floating-label">Confirmar Contraseña</label>
        </div>
        <button type="submit">SIGN UP</button>
        <p>¿Ya tienes una cuenta? <Link to="/login">Inicia sesión</Link></p>
      </form>
    </div>
  );
};

export default Register;
