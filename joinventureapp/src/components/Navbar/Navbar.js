import React from 'react';
import { NavLink } from 'react-router-dom';
import './Navbar.css';



function Navbar() {
  

  // useEffect para ejecutar la función cuando `roleuser` cambie

  return (
    <nav className="navbar">
      <div className="navbar-logo">LOGO</div>
      <div className="navbar-links">
        
        <NavLink to="/projects" activeClassName="active" >Proyectos</NavLink>
        <NavLink to="/recommendations" activeClassName="active">Recomendaciones</NavLink>
        <NavLink to="/about" activeClassName="active">Nosotros</NavLink>
        <NavLink to="/contact" activeClassName="active">Contacto</NavLink>
        <NavLink to="/login" activeClassName="active" className="button">Login</NavLink>
        {/* Agrega más enlaces aquí utilizando NavLink */}
      </div>
    </nav>
  );
}

export default Navbar;
