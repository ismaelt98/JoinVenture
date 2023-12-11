import { useState } from "react";
import style from './register.module.css'
function Register(): any {
  
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [alias, setAlias] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');



  const [confirmPassword, setConfirmPassword] = useState('');

    return (
      <>
      <form className={style.signupForm}>
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
      </form>
      <p>¿Eres usuario? Pues haz click aquí 🪵<button className={style.submitBtn} type="submit">Iniciar Sesion</button></p>
      </>
    );
}

export default Register;

