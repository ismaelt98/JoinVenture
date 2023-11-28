import React from 'react';
import Navbar from './components/Navbar/Navbar';
import Header from './components/Header/Header';
import MainContent from './components/MainContent/MainContent';
import Footer from './components/Footer/Footer';
import './App.css';

function App() {
  return (
    <div className="App">
      <Navbar />
      <Header />
      <MainContent />
      <Footer />
    </div>
  );
}

export default App;
