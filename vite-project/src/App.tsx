import { Route, Routes } from 'react-router-dom';
import './App.css'
import Navbar from './components/Navbar/Navbar'
import Test from './components/Test/Test';
import Login from './components/Login/Login';
import Register from './components/Register/Register';

function App() {

  return (
    <>
      <Navbar />

      <Routes>
        <Route path="/test" element={<Test />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/test" element={<Test />} />
      </Routes>

    </>
  )
}

export default App;
