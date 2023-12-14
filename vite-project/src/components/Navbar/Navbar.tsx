import { NavLink, useLocation } from 'react-router-dom';
import logoImg from '../../assets/logo.png';

import Cookies from 'js-cookie';
import './Navbar.css';
import { useEffect, useState } from 'react';

function Navbar(): any {
    const [cookieExists, setCookieExists] = useState(false);
    const [roleUser, setRoleUser] = useState('');
    const location = useLocation();

    useEffect(() => {
        const cookieValue = Cookies.get('id');
        const cookieRole = Cookies.get('roleuser');
        setCookieExists(!!cookieValue);
        if (cookieRole !== undefined) {
            setRoleUser(cookieRole);
        }
    }, [location]);

    return (
        <nav className="navbar">
            <div className="navbar-logo">
                <NavLink to="/" ><img className='imgLogo' src={logoImg} alt="Descripción de la imagen" /></NavLink>
            </div>
            <div className="navbar-links">
                {cookieExists ? (
                    <>
                        {roleUser !== 'admin' && (
                            <NavLink to="/projects" activeClassName="active">Proyectos</NavLink>
                        )}

                        {roleUser === 'admin' && (
                            <>
                                <NavLink to="/adminusers" >Administrar Usuarios</NavLink>
                                <NavLink to="/adminprojects" >Administrar Proyectos</NavLink>
                            </>
                        )}
                        <NavLink to="/perfil" >Mi Perfil</NavLink>
                        <NavLink to="/login" className="button">Logout</NavLink>
                    </>
                ) : (
                    <NavLink to="/login" className="button">Login</NavLink>
                )}
            </div>
        </nav >
    );
}

export default Navbar;
