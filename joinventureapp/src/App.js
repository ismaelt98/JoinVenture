// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Header from './components/Header/Header';

import Projects from './pages/Projects/Projects';
import Recommendations from './pages/Recommendations/Recommendations';
import About from './pages/About/About';
import Contact from './pages/Contact/Contact';
import InteractiveMap from './components/InteractiveMap/InteractiveMap';

import Login from './pages/Login/Login';
// ... otros imports si son necesarios
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <Header />
        <Routes>
          <Route path="/projects" element={<Projects />} />
          <Route path="/recommendations" element={<Recommendations />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
          {/* Define más rutas aquí si es necesario */}
          <Route path="/interactive-map" element={<InteractiveMap />} />

        </Routes>
        
      </div>
    </Router>
  );
}

export default App;
