import { NavLink } from 'react-router-dom';
import logoImg from '../../assets/logo.png';
import './Navbar.css';
function Navbar(): any {

    return (
        <nav className="navbar">
            <div className="navbar-logo"><img className='imgLogo' src={logoImg} alt="Descripción de la imagen" /></div>
            <div className="navbar-links">
                <NavLink to="/login" className="button">
                    Login
                </NavLink>
            </div>
        </nav>
    );
}

export default Navbar;
