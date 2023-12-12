import { Route, Routes } from 'react-router-dom';
import './App.css'
import Navbar from './components/Navbar/Navbar'
import Test from './components/Test/Test';
import Login from './components/Login/Login';
import Register from './components/Register/Register';
import AutenficationPage from './pages/AutenficationPage/AutenficationPage';
import Projects from './pages/Projects/Projects';


function App() {

  return (
    <>
      <Navbar />

      <Routes>
        <Route path="/autenficationpage" element={<AutenficationPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/test" element={<Test />} />
        <Route path="/projects" element={<Projects />} />
      </Routes>

    </>
  )
}

export default App;
