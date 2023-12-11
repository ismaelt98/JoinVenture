import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import './Navbar.css';
import Cookies from 'js-cookie';
import imagen from '../../assets/logod.png';
import { useLocation } from 'react-router-dom';

function Navbar() {
  const [cookieExists, setCookieExists] = useState(false);
  const [roleUser, setRoleUser] = useState('');
  const location = useLocation();

  useEffect(() => {
    const cookieValue = Cookies.get('id');
    const cookieRole = Cookies.get('roleuser');
    setCookieExists(!!cookieValue);
    setRoleUser(cookieRole);
  }, [location]);

  return (
    <nav className="navbar">
      <div className="navbar-logo"><img className='imgLogo' src={imagen} alt="Descripción de la imagen" /></div>
      <div className="navbar-links">
        {cookieExists ? (
          <>
            <NavLink to="/projects" activeClassName="active">Proyectos</NavLink>
            
            {roleUser === 'ADMIN' && (
              <>
                <NavLink to="/admin/users" activeClassName="active">Administrar Usuarios</NavLink>
                <NavLink to="/admin/projects" activeClassName="active">Administrar Proyectos</NavLink>
              </>
            )}
            <NavLink to="/contact" activeClassName="active">Mi Perfil</NavLink>
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
