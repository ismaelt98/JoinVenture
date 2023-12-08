import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import './Navbar.css';
import Cookies from 'js-cookie';


function Navbar() {
  const [cookieExists, setCookieExists] = useState(false);

  useEffect(() => {
    const cookieValue = Cookies.get('id'); // Reemplaza 'nombre_de_la_cookie' por el nombre de tu cookie
    if (cookieValue) {
      setCookieExists(true);
    } else {
      setCookieExists(false);
    }
  }, []);

  // useEffect para ejecutar la función cuando `roleuser` cambie

  return (
    <nav className="navbar">
      <div className="navbar-logo"><img src="" alt="Descripción de la imagen" /></div>
      <div className="navbar-links">
        {cookieExists ? (
          <>
            <NavLink to="/projects" activeClassName="active">Proyectos</NavLink>
            <NavLink to="/recommendations" activeClassName="active">Recomendaciones</NavLink>
            <NavLink to="/about" activeClassName="active">Nosotros</NavLink>
            <NavLink to="/contact" activeClassName="active">Contacto</NavLink>
            {/* Agrega más enlaces aquí utilizando NavLink */}
            <NavLink to="/login" activeClassName="active" className="button">Logout</NavLink>
          </>
        ) : (
          <NavLink to="/login" activeClassName="active" className="button">Login</NavLink>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
