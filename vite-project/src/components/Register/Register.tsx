import { useState } from "react";
import style from './register.module.css'
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Register(): any {

  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [alias, setAlias] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [projectList] = useState([]);
  const [listLanguage] = useState([]);
  const [listFrameworks] = useState([]);
  const [valueRoleUser, setValueRoleUser] = useState('1');
  const navigate = useNavigate();

  const irLogin = () => {
    navigate('/login');
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, checked } = e.target;

    if (checked) {
      setValueRoleUser(value);
    } else {
      setValueRoleUser(value);
    }
  };

  const handleRegister = async (e: React.FormEvent<HTMLFormElement>) => {

    e.preventDefault();
    // Lógica para registro...
    if (password === confirmPassword) {
      try {
        const responseEmail = await fetch(`http://localhost:8080/users/checkEmail?email=${email}`);
        const responsePassword = await fetch(`http://localhost:8080/users/checkPassword?password=${password}`)
        const responsePhone = await fetch(`http://localhost:8080/users/checkPhone?phone=${phone}`)

        const booleanExistsEmail = await responseEmail.json();
        const booleanExistsPassword = await responsePassword.json();
        const booleanExistsPhone = await responsePhone.json();

        if (!booleanExistsEmail) {
          if (!booleanExistsPhone) {
            if (!booleanExistsPassword) {
              const response = await fetch('http://localhost:8080/users', {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, alias, email, password, phone, roleuser: { id: valueRoleUser }, projectList, listLanguage, listFrameworks })
              });

              const data = await response.json();
              if (data.id) {

                setUsername('');
                setAlias('');
                setEmail('');
                setPhone('');
                setPassword('');
                setConfirmPassword('');
                navigate('/login');

              } else {
                console.error('Error de registro');
              }

            } else {
              toast.error('El password ya existe', {
                position: toast.POSITION.TOP_RIGHT,
              });
            }
          } else {
            toast.error('El telefono ya existe', {
              position: toast.POSITION.TOP_RIGHT,
            });
          }
        } else {
          toast.error('El email ya existe', {
            position: toast.POSITION.TOP_RIGHT,
          });
        }

      } catch (error) {
        console.error('Error al conectar con la API', error);
      }
    } else {
      toast.error('El password no coincide', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  };

  return (
    <>
      <div className={style.container}>
        <h2 className={style.h2TitleC}>CREAR CUENTA</h2>
        <div className={style.divElige}>
          <label className={style.labelChecbox}>
            <input
              type="checkbox"
              value="1"
              onChange={handleChange}
              checked={valueRoleUser === "1"}
            />
            Programador
          </label>
          <label className={style.labelChecbox}>
            <input
              type="checkbox"
              value="2"
              onChange={handleChange}
              checked={valueRoleUser === "2"}
            />
            Empresa
          </label>
        </div>
        <form className={style.signupForm} onSubmit={handleRegister}>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Nombre"
            required
          />
          <input
            type="text"
            value={alias}
            onChange={(e) => setAlias(e.target.value)}
            placeholder="Alias"
            required
          />
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Correo electrónico"
            required
          />
          <input
            type="number"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            placeholder="Teléfono"
            required
          />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Contraseña"
            required
          />
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            placeholder="Confirmar contraseña"
            required
          />
          <button className={style.submitBtn} type="submit">Registrarse</button>
          <div>
            <ToastContainer />
          </div>
        </form>
        <p>¿Eres usuario? Pues haz click aquí 🪵<button className={style.submitBtnIni} onClick={irLogin} type="submit">Iniciar Sesion</button></p>
      </div>
    </>
  );
}

export default Register;

