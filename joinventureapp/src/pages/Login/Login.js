import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css'; // Asegúrate de que la ruta al archivo CSS sea correcta



const Login = () => {

  const [selectedValue, setSelectedValue] = useState('1'); // Estado para almacenar el valor seleccionado

  const handleChange = (e) => {
    const { value, checked } = e.target;

   
    if (checked) {
      setSelectedValue(value); // Actualiza el estado con el valor del checkbox seleccionado
    } else {
      setSelectedValue(value); // Establece un valor vacío si el checkbox se desmarca
    }
  }; 

  
  
  const [isLogin, setIsLogin] = useState(true);

  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [alias, setAlias] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');
  

  const [projectList] = useState([]);
  const [listLanguage] = useState([]);
  const [listFrameworks] = useState([]);
  const [confirmPassword, setConfirmPassword] = useState('');
  const [currentTitleIndex, setCurrentTitleIndex] = useState(0); // Asegúrate de que esta línea esté presente

  const navigate = useNavigate();

  const titles = ['JoinVenture: Transformando Ideas en Realidad!', 'Nuestra Misión: Unir Fuerzas para el Éxito'];
  
  const paragraphs = ['En el dinámico mundo de los negocios y la innovación, surge JoinVenture como un faro de colaboración y ejecución. Somos más que una empresa, somos el puente que conecta visionarios con expertos, convirtiendo ideas en proyectos tangibles y exitosos.', 'En JoinVenture, creemos en el poder de la colaboración. Nuestra misión es catalizar el potencial creativo al unir emprendedores con profesionales especializados.', 'En el vasto panorama de proyectos e innovación, JoinVenture destaca como un motor de transformación. Nos definimos como arquitectos de sueños empresariales, trabajando incansablemente para materializar ideas y convertirlas en proyectos prósperos.', 'En el corazón de JoinVenture yace una filosofía de unión. Creemos en el poder de la colaboración, donde visionarios y ejecutores se encuentran para dar forma a ideas que impulsan el cambio y el progreso.'];
  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTitleIndex((prevIndex) => (prevIndex + 1) % titles.length);
    }, 8000); // Cambia cada 8 segundos
    return () => clearInterval(intervalId);
  }, [titles.length]);

 

  const handleLogin = async (event) => {
    event.preventDefault();
    // Lógica para iniciar sesión...
    try {
      var formdata = new FormData();
      formdata.append("email", email);
      formdata.append("password", password);

      var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
      };
      const response = await fetch('http://localhost:8080/users/login', requestOptions);
      const data = await response.json();
      if (data) {
        // Guardar el token y posiblemente el ID de usuario en el almacenamiento local o en cookies
        localStorage.setItem('userToken', data.token);
        navigate('/rutaPrincipal'); // Asegúrate de que esta es la ruta correcta
      } else {
        // Manejar error de inicio de sesión
        console.error('Error de inicio de sesión');
      }
    } catch (error) {
      console.error('Error al conectar con la API', error);
    }
  };

  const handleRegister = async (event) => {
    console.log("Intentando registrar"); // Para depuración

    event.preventDefault();
    // Lógica para registro...
    if (password !== confirmPassword) {
      console.error('Las contraseñas no coinciden');
      return;
    }
    try {
      var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      };

      const responseEmail = await fetch(`http://localhost:8080/users/checkEmail?email=${email}`, requestOptions);
      const responsePassword = await fetch(`http://localhost:8080/users/checkPassword?password=${password}`, requestOptions)
      const responsePhone = await fetch(`http://localhost:8080/users/checkPhone?phone=${phone}`, requestOptions)
      
      const dataEmail = await responseEmail.json();
      const dataPassword = await responsePassword.json();
      const dataPhone = await responsePhone.json();

      if (!dataEmail && !dataPassword && !dataPhone) {
        const response = await fetch('http://localhost:8080/users', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ username, alias, email, password, phone, roleuser: { id: selectedValue }, projectList, listLanguage, listFrameworks })
        });
        const data = await response.json();
        if (data.id) {
          navigate('/rutaPrincipal'); // Asegúrate de que esta es la ruta correcta
        } else {
          // Manejar errores de registro
          console.error('Error de registro');
        }
      } else {
        
        //se tendra con el !dataEmail && !dataPassword && !dataPhone algo que salte alerts personalizados por cada uno
        console.log("Esta mal algo");
        return;
      }


    } catch (error) {
      console.error('Error al conectar con la API', error);
    }
  };

  return (
    <div className="login-wrapper">
      <div className='l1'>
        <div className='div1l1'>
          <h2>{titles[currentTitleIndex]}</h2>
          <p>{paragraphs[currentTitleIndex]}</p>
        </div>
        <div className='div2l1'>
          <h3 id='h3CrearCuentaoInici'>{isLogin ? '¿No tienes una cuenta? ¡No te preocupes!' : '¿Ya tienes cuenta? ¡Inicia sesión ahora mismo y forma parte de nuestra comunidad!'} </h3>
          <button onClick={() => setIsLogin(!isLogin)}>
            {isLogin ? 'CREAR CUENTA' : 'INICIAR SESIÓN'}
          </button>
        </div>
      </div>
      <div className='l2'>
      <h2 id="h2CrearCuenta" >{isLogin ? 'Iniciar Sesión':'Crear Cuenta'}</h2>
        
      <div style={{ display: isLogin ? 'none' : 'block' }} id="divElige" className="userType-buttons">
      <label>
        <input
          type="checkbox"
         value="1"
          onChange={handleChange}
          checked={selectedValue === "1"}
        />
        Programador
      </label>
      <label>
        <input
          type="checkbox"
          value="2"
          onChange={handleChange}
          checked={selectedValue === "2"}
        />
        Empresa
      </label>
    </div>
        
        

        {isLogin ? (
          <form className='signup-form' onSubmit={handleLogin}>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Correo electrónico"
              required
            />
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Contraseña"
              required
            />
            <button className='submit-btn' type="submit">Iniciar sesión</button>
          </form>
        ) :  (

          <form className='signup-form' onSubmit={handleRegister}>
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
            <button className='submit-btn' type="submit">Registrarse</button>
          </form>
        )}
      </div>

    </div>
  );
};

export default Login;
