import React from 'react';
import './Navbar.css';

function Navbar() {
  return (
    <nav className="navbar">
      {/* Agrega tu logo y los enlaces aquí */}
      <div className="navbar-logo">LOGO</div>
      <div className="navbar-links">
        <a href="/home">Home</a>
        {/* Agrega más enlaces aquí */}
      </div>
    </nav>
  );
}

export default Navbar;
