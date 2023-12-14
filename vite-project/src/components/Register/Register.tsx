import { useState } from "react";
import style from './register.module.css'
import { Link, useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

// Data form
interface FormData {
  username: string
  alias: string
  email: string
  password: string
  phone: string
  roleuser: string
}
function Register(): any {
  const navigate = useNavigate();
  const [formData, setFormData] = useState<FormData>({
    username: '',
    alias: '',
    email: '',
    password: '',
    phone: '',
    roleuser: ''
  });
  const [selectedRole, setSelectedRole] = useState('programador');
  const handleRoleChange = (role: string): void => {
    if (selectedRole === role) {
      setSelectedRole('');
    } else {
      setSelectedRole(role);
    }
  };
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    var raw = JSON.stringify({
      "username": formData.username,
      "alias": formData.alias,
      "email": formData.email,
      "password": formData.password,
      "phone": formData.phone,
      "roleuser": selectedRole
    });
    var requestOptions: RequestInit = {
      method: 'PUT',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };
    fetch("http://localhost:8080/users", requestOptions)
      .then(response => response.json())
      .then(result => {
        console.log(result);
        navigate("/login");
      })
      .catch(error => {
        console.log('error', error);
        toast.error('Ya existe este usuario con este email', {
          position: toast.POSITION.TOP_RIGHT,
        });
      });
  };
  return (
    <>
      <div className={style.divGe}>
        <h2 className={style.h2CrearCuenta}>CREAR CUENTA</h2>
        <form className={style.signupForm} onSubmit={handleSubmit}>
          <div className={style.divElige}>
            <label className={style.labelChecbox}>
              <input
                type="checkbox"
                name="programador"
                checked={selectedRole === 'programador'}
                onChange={() => handleRoleChange('programador')}
              />
              Programador
            </label>
            <label className={style.labelChecbox}>
              <input
                type="checkbox"
                name="empresa"
                checked={selectedRole === 'empresa'}
                onChange={() => handleRoleChange('empresa')}
              />
              Empresa
            </label>
          </div>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            placeholder="Username"
            required
          />
          <input
            type="text"
            name="alias"
            value={formData.alias}
            onChange={handleChange}
            placeholder="Alias"
            required
          />
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Email"
            required
          />
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="Contraseña"
            required
          />
          <input
            type="number"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            placeholder="Teléfono"
            required
          />
          <button className={style.submitBtnIni} type="submit">Crear Cuenta</button>
          <div>
          <ToastContainer />
        </div>
        </form>
        <p>Eres usuario? Pues haz click aquí 🪵 <Link to="../login" className={style.linkLogin}>Iniciar Sesion</Link></p>
      </div>
    </>
  );
}

export default Register;