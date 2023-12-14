/*import Login from "../../components/Login/Login";
import Test from "../../components/Test/Test";*/
import { useState } from 'react';
import style from './crearproject.module.css'
import Cookies from 'js-cookie';
import { Link, useNavigate } from 'react-router-dom';

interface Proyecto {
    name: string;
    nummembers: string;
    sector: string;
    demand: string;
}




function CrearProject(): any {
    const [proyecto, setProyecto] = useState<Proyecto>({
        name: '',
        nummembers: '',
        sector: '',
        demand: '',
    });
    const idUser = Cookies.get('id');
    const navigate = useNavigate();
    const handleInputChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) => {
        const { name, value } = e.target;
        setProyecto({ ...proyecto, [name]: value });
    };

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        // Aquí puedes hacer lo que quieras con el objeto de proyecto
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({
            "name": proyecto.name,
            "nummembers": proyecto.nummembers,
            "sector": proyecto.sector,
            "demand": proyecto.demand,
            "userCreator": {
                "id": idUser
            }
        });

        var requestOptions: RequestInit = {
            method: 'PUT',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("http://localhost:8080/projects", requestOptions)
            .then(response => response.text())
            .then(result => {
                console.log(result);
                navigate("/projects");
            })
            .catch(error => console.log('error', error));
    };


    return (
        <>
            <div className={style.allDiv}>
               
                    
                   
              
                <form onSubmit={handleSubmit} className={style.formContainer}>
                <h2 className={style.h2Crear}>CREAR PROYECTO</h2>
                    <label className={style.labelStyle}>
                        Nombre del Proyecto:
                        <input
                            className={style.inputStyle}
                            type="text"
                            name="name"
                            value={proyecto.name}
                            onChange={handleInputChange}
                            required
                        />
                    </label>
                    <br />

                    <label className={style.labelStyle}>
                        Número de Miembros:
                        <input
                            className={style.inputStyle}
                            type="number"
                            name="nummembers"
                            value={proyecto.nummembers}
                            onChange={handleInputChange}
                            required
                        />
                    </label>
                    <br />

                    <label className={style.labelStyle}>
                        Sector:
                        <select
                            className={style.selectStyle}
                            name="sector"
                            value={proyecto.sector}
                            onChange={handleInputChange}
                            required
                        >
                            <option value="">Seleccionar Sector</option>
                            <option value="Tecnología">Tecnología</option>
                            <option value="Salud">Salud</option>
                            <option value="Educación">Educación</option>
                            <option value="Social">Social</option>
                            <option value="Cultura">Cultura</option>
                            {/* Agrega más opciones según tus necesidades */}
                        </select>
                    </label>
                    <br />

                    <label className={style.labelStyle}>
                        Demanda:
                        <select
                            className={style.selectStyle}
                            name="demand"
                            value={proyecto.demand}
                            onChange={handleInputChange}
                            required
                        >
                            <option value="">Seleccionar Demanda</option>
                            <option value="APP_DEVELOPMENT">APP_DEVELOPMENT</option>
                            <option value="WEB_DEVELOPMENT">WEB_DEVELOPMENT</option>
                            <option value="SOFTWARE_DEVELOPMENT">SOFTWARE_DEVELOPMENT</option>
                            <option value="IA">IA</option>
                            <option value="IOT">IOT</option>
                            {/* Agrega más opciones según tus necesidades */}
                        </select>
                    </label>
                    <br />

                    <button className={style.submitButton} type="submit">Crear Proyecto</button>
                    <Link to="../projects" className={style.linkProject}>🔙</Link>
                </form>
            </div>
        </>
    );
}

export default CrearProject;