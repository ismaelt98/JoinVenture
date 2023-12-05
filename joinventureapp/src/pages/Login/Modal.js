import React, { useState } from 'react';
import './Modal.css'; // Asegúrate de que la ruta sea correcta

const ModalLogin = () => {
  const [isOpen, setIsOpen] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    
    try {
      const response = await fetch("http://localhost:8080/users/login", {
        method: 'POST',
        body: formData,
        redirect: 'follow'
      });
      const result = await response.text();
      
      if (result === "true") {
        setIsOpen(false);
        console.log("Inicio de sesión exitoso");
      } else {
        alert("EMAIL O CONTRASEÑA INCORRECTAS");
      }
    } catch (error) {
      console.error('error', error);
    }
  };

  return (
    <>
      <button onClick={() => setIsOpen(true)}>Abrir Modal</button>
      {isOpen && (
        <div className="modal">
          <div className="login-container">
            <h2>Iniciar Sesión</h2>
            <form id="miFormulario" onSubmit={handleSubmit}>
              <div className="input-container">
                <input type="email" id="email" name="email" placeholder="Email" required />
              </div>
              <div className="input-container">
                <input type="password" id="password" name="password" placeholder="Contraseña" required />
              </div>
              <button id="btnEntrar" type="submit">ENTRAR</button>
              <div className="linea-elegante">
                <span className="texto-en-linea">OR</span>
              </div>
              <button className="close-btn" onClick={() => setIsOpen(false)}>Cerrar</button>
            </form>
            <div className="divAllMedia">
              <p>Sign with Social Media</p>
              <div className="divSocialMed">
                <button className="butSocialMed">
                  <img className="socialMed" src="img/4766956-removebg-preview.png" alt=""/>
                </button>
              </div>
            </div>
            <div id="divCrearCuenta">
            <Link to="/register">NO TENGO CUENTA</Link>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default ModalLogin;
