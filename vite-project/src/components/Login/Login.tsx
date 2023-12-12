import { useState } from 'react';
import style from './login.module.css'
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
function Login(): any {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const irRegistro = () => {
        navigate('/register');
    };

    const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        // Lógica para iniciar sesión...
        try {

            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var raw = JSON.stringify({
                "email": email,
                "password": password
            });

            var requestOptions: RequestInit = {
                method: 'POST',
                headers: myHeaders,
                body: raw,
                redirect: 'follow'
            };

            fetch(`http://localhost:8080/users/login?email=${email}&password=${password}`, requestOptions)
                .then(response => {
                    return response.json()
                })
                .then(result => {
                    if (result) {
                        var requestOptions: RequestInit = {
                            method: 'GET',
                            headers: myHeaders,
                            redirect: 'follow'
                        };
                        fetch("http://localhost:8080/users/buscarEmail?email=davidcamps@hotmail.es", requestOptions)
                            .then(response => {
                                return response.json()
                            })
                            .then(user => {

                                const cookieValue = user.id; // Convertir el JSON en un string para la cookie
                                const cookieRoleUser = user.name_role;
                                // Crear la cookie con el valor obtenido del JSON
                                Cookies.set('id', cookieValue, { expires: 10 });
                                Cookies.set('roleuser', cookieRoleUser, { expires: 10 });
                                navigate('/autenficationpage');
                            })
                            .catch(error => console.log('error', error));

                    } else {
                        toast.error('El email o password no coinciden', {
                            position: toast.POSITION.TOP_RIGHT,
                        });
                    }
                })
                .catch(error => console.log('error', error));
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <>
            <div className={style.container}>
                <h2 className={style.h2Title}>INICIAR SESION</h2>
                <form className={style.signinForm} onSubmit={handleLogin}>
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
                    <div>
                        <ToastContainer />
                    </div>
                </form>
                <p>¿Aún no eres usuario? Pues haz click aquí 🪵<button className={style.submitBtn1} type="submit" onClick={() => irRegistro()}>Registrarse</button></p>
            </div>
        </>
    );
}

export default Login;