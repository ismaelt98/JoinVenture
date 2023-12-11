import { useState } from 'react';
import style from './login.module.css'

function Login(): any {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const irRegistro = async () => {
     
    
      };

    return (
        <>
            <form className={style.signupForm}> { /*onSubmit={handleLogin}*/}
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
                <button className={style.submitBtn} type="submit">Iniciar sesión</button>
            </form>
            <p>¿Aún no eres usuario? Pues haz click aquí 🪵<button className={style.submitBtn} type="submit" onClick={() => irRegistro()}>Registrarse</button></p>
        </>
    );
}

export default Login;