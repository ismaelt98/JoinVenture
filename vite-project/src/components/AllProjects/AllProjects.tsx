
import { useEffect, useState } from 'react';
import style from './allprojects.module.css';
import Cookies from 'js-cookie';


function AllProjects(): any {
    interface User {
        id: number;
        username: string;
    }

    interface Project {
        id: number;
        name: string;
        nummembers: string;
        sector: string;
        demand: string;
        userCreator: User;
        usersList: User[];
    }

    const [projects, setProjects] = useState<Project[]>([]);
    const idUser = Cookies.get('id');
    const roleUser = Cookies.get('roleuser');

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
    }, []); // Ejecución al montar el componente



    const unirseAlProyecto = async (idProyecto: number) => {

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({
            "idUsuario": idUser
        });

        var requestOptions: RequestInit = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch(`http://localhost:8080/projects/${idProyecto}/addMember/${idUser}`, requestOptions)
            .then(response => response.json())
            .then(result => {
                console.log(result);
                // { setUnirse(false) };
            })
            .catch(error => {

                console.log('error', error)
            });

    };

    const salirseDelProyecto = async (idProyecto: number) => {

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var requestOptions: RequestInit = {
            method: 'DELETE',
            headers: myHeaders,

            redirect: 'follow'
        };

        fetch(`http://localhost:8080/projects/${idProyecto}/removeMember/${idUser}`, requestOptions)
            .then(response => response.text())
            .then(result => {
                console.log(result)
                //{ setUnirse(true) };
            })
            .catch(error => console.log('error', error));

    };

    //const [unirse, setUnirse] = useState(true); //! para mirar 👀

    interface User {
        id: number
        username: string
        alias: string
        email: string
        password: string
        phone: string
        roleuser: string
    }


    return (
        <div className={style.container}>
            {projects.map(project => (
                <div className={`${style.card} w-96 bg-base-100 shadow-xl`} key={project.id}>
                    <figure><img src="https://mexico.unir.net/wp-content/uploads/sites/6/2019/05/ficha_1920x1080-copiaiStock-1274417553-1.jpg" className={style.image} alt="Shoes" /></figure>
                    <div className="card-body">
                        <h2 className="card-title">{project.name}</h2>
                        <p><strong>Numero de participantes: </strong>{project.nummembers}</p>
                        <p><strong>Nombre Creador: </strong>{project.userCreator.username}</p>
                        <div className={style.cardActions}>
                            <div className={style.badgeOutline}>{project.sector}</div>
                            <div className={style.badgeOutline}>{project.demand}</div>
                        </div>

                        {roleUser != 'empresa' ? (
                            <>
                                {project.usersList.some(user => user.id.toString() === idUser) ?
                                    <Button clases={style.exitBtn} idProject={project.id} text="Salirse" onClick={salirseDelProyecto} /> :
                                    <Button clases={style.inBtn} idProject={project.id} text="Unirse" onClick={unirseAlProyecto} />
                                }
                            </>
                        ) : (
                            <button className={style.verBtn}>VER PARTICIPANTES</button>
                        )}


                    </div>
                </div>
            ))}
        </div>
    );
}

interface ButtonProps {
    idProject: number;
    text: string;
    clases: string
    onClick: (param: any) => void; // Ajusta el tipo de parámetro según lo que esperas recibir
}

const Button = ({ idProject, text, clases, onClick }: ButtonProps) => {


    const handleClick = () => {
        // Aquí puedes definir el parámetro que quieres pasar a la función onClick
        const parametro = idProject; // Puedes definir el parámetro que necesites
        // alert(parametro);
        // Llamas a la función onClick y pasas el parámetro
        onClick(parametro);
        window.location.reload();
    };

    return (
        <button onClick={handleClick} className={clases}>
            {text}
        </button>
    );
};

export default AllProjects;