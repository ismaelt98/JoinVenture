import { useState } from 'react';
import style from './login.module.css'
import { Link, useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface FormData {
    email: string
    password: string
}
function Login(): any {
    Cookies.remove('id');
    //Cookies.remove('roleuser');
    const navigate = useNavigate();

    const [formData, setFormData] = useState<FormData>({
        email: '',
        password: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>): void => {
        e.preventDefault();
        var requestOptions: RequestInit = {
            method: 'POST',
            redirect: 'follow'
        };
        fetch(`http://localhost:8080/users/login?email=${formData.email}&password=${formData.password}`, requestOptions)
            .then(response => response.json())
            .then(result => {
                console.log(result);
                Cookies.set('id', result.id, { expires: 10 });
                Cookies.set('roleuser', result.roleuser, { expires: 10 });
                navigate('/projects');
            })
            .catch(error => {
                console.log('error', error);
                toast.error('Usuario o contraseña mal', {
                    position: toast.POSITION.TOP_RIGHT,
                });
            });
    };
    return (
        <>
            <div className={style.container}>
                <h2 className={style.h2Title}>INICIAR SESION</h2>
                <form className={style.signinForm} onSubmit={handleSubmit}>
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
                    /><button className={style.submitBtn} type="submit">Iniciar sesión</button>
                    <div>
                        <ToastContainer />
                    </div>
                </form>
                <p>¿Aún no eres usuario? Pues haz click aquí 🪵 <Link to="../register">Registrarse</Link></p>
            </div>
        </>
    );
}

export default Login;