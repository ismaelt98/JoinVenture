import { NavLink } from 'react-router-dom';
import logoImg from '../../assets/logo.png';
import { useLocation } from 'react-router-dom';
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
            <div className="navbar-logo"><img className='imgLogo' src={logoImg} alt="Descripción de la imagen" /></div>
            <div className="navbar-links">
                {cookieExists ? (
                    <>
                        <NavLink to="/projects" >Proyectos</NavLink>

                        {roleUser === 'ADMIN' && (
                            <>
                                <NavLink to="/login" >Administrar Usuarios</NavLink>
                                <NavLink to="/login" >Administrar Proyectos</NavLink>
                            </>
                        )}
                        <NavLink to="/login" >Mi Perfil</NavLink>
                        <NavLink to="/login" className="button">Logout</NavLink>
                    </>
                ) : (
                    <NavLink to="/login" className="button">Login</NavLink>
                )}
            </div>
        </nav>
    );
}

export default Navbar;
