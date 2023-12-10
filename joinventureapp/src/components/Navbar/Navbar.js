import React, { useEffect, useState } from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import './Navbar.css';
import Cookies from 'js-cookie';
import imagen from '../../assets/logod.png';

function Navbar() {
  const [cookieExists, setCookieExists] = useState(false);
  const location = useLocation();

  useEffect(() => {
    const cookieValue = Cookies.get('id');
    setCookieExists(!!cookieValue);
  }, [location]);

  // useEffect para ejecutar la función cuando `roleuser` cambie

  return (
    <nav className="navbar">
      <div className="navbar-logo"><img className='imgLogo' src={imagen} alt="Descripción de la imagen" /></div>
      <div className="navbar-links">
        {cookieExists ? (
          <>
            <NavLink to="/projects" activeClassName="active">Proyectos</NavLink>
          
            <NavLink to="/contact" activeClassName="active">Mi Perfil</NavLink>
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
