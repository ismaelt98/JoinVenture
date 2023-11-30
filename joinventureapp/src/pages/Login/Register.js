import React from 'react';
import './Register.css'; // Asegúrate de que la ruta sea correcta

const Register = () => {
  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    
    try {
      const response = await fetch("http://localhost:8080/users/register", {
        method: 'POST',
        body: formData,
        redirect: 'follow'
      });
      const result = await response.text();
      
      if (result === "true") {
        console.log("Registro exitoso");
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
          <input type="text" id="name" name="name" required />
          <label htmlFor="name" className="floating-label">Nombre</label>
        </div>
        <div className="form-group">
          <input type="text" id="surname" name="surname" required />
          <label htmlFor="surname" className="floating-label">Apellido</label>
        </div>
        <div className="form-group">
          <input type="email" id="email" name="email" required />
          <label htmlFor="email" className="floating-label">Email</label>
          <span id="emailError" className="error-message"></span>
        </div>
        <div className="form-group">
          <input type="password" id="password" name="password" required />
          <label htmlFor="password" className="floating-label">Contraseña</label>
          <span id="pasError" className="error-message"></span>
        </div>
        <div className="form-group">
          <input type="password" id="confirm-password" name="confirm-password" required />
          <label htmlFor="confirm-password" className="floating-label">Confirmar Contraseña</label>
        </div>
        <button type="submit">SIGN UP</button>
        <p>¿Ya tienes una cuenta? <Link to="/login">Inicia sesión</Link></p>
      </form>
    </div>
  );
};

export default Register;
