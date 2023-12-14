/*import Login from "../../components/Login/Login";
import Test from "../../components/Test/Test";*/
import style from './adminprojects.module.css'

import { useEffect, useState } from "react";

function AdminProjects(): any {

    interface User{
        id: number;
        username: string;
    }

    interface Project {
        id: number;
        name: string;
        nummembers: number;
        sector: string;
        demand: string;
        userCreator: User;
    }

    const eliminarProyecto = async (idProject: number) => {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var requestOptions: RequestInit = {
            method: 'DELETE',
            headers: myHeaders,
            redirect: 'follow'
        };

        fetch(`http://localhost:8080/projects/${idProject}`, requestOptions)
            .then(response => response.text())
            .then(result => {
                console.log(result)
                window.location.reload();
            })
            .catch(error => console.log('error', error));
    };

    const [projects, setProjects] = useState<Project[]>([]);
    
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/projects");
                const data: Project[] = await response.json();
                setProjects(data);
            } catch (error) {
                console.log('error', error);
            }
        };
        fetchData();
    }, []);

    return (
        <div>
            <table className={style.table}>
                <thead>
                    <tr>
                        <th>Nombre proyecto</th>
                        <th>Numero integrantes</th>
                        <th>Sector</th>
                        <th>Demanda</th>
                        <th>Nombre Creador</th>
                        <th>Opciones</th>
                        
                        {/* Puedes agregar más encabezados si es necesario */}
                    </tr>
                </thead>
                <tbody>
                {projects.map((proj) => (
                        <tr key={proj.id}>
                            
                            <td>{proj.name}</td>
                            <td>{proj.nummembers}</td>
                            <td>{proj.sector}</td>
                            <td>{proj.demand}</td>
                            <td>{proj.userCreator.username}</td>
                            
                            <td><button onClick={() => eliminarProyecto(proj.id)}>DEL</button></td>
                        </tr>
                    ))}
                  </tbody>
            </table>
        </div>
    );
}

export default AdminProjects;