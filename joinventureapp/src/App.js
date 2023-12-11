// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Header from './components/Header/Header';

import Projects from './pages/Projects/Projects';

import Contact from './pages/Contact/Contact';
import InteractiveMap from './components/InteractiveMap/InteractiveMap';

import Login from './pages/Login/Login';
import AuthGuard from './pages/AuthGuard/AuthGuard';
// ... otros imports si son necesarios
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        
        <Routes>
          <Route path="/projects" element={<AuthGuard><Projects /></AuthGuard>} />
        
        
          <Route path="/contact" element={<AuthGuard><Contact /></AuthGuard>} />
          <Route path="/login" element={<Login />} />
          {/* Define más rutas aquí si es necesario */}
          <Route path="/interactive-map" element={<AuthGuard><InteractiveMap /></AuthGuard>} />

        </Routes>
        
      </div>
    </Router>
  );
}

export default App;
