
import { useEffect, useState } from 'react';
import style from './allprojects.module.css'

function AllProjects(): any {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            var requestOptions: RequestInit = {
                method: 'GET',
                redirect: 'follow'
            };

            fetch("http://localhost:8080/projects", requestOptions)
                .then(response => {
                    return response.json()
                })
                .then(result => {
                    setData(result);
                })
                .catch(error => console.log('error', error));
        };
        fetchData();
    }, []);

    return (
        <div className={style.projectContainer}>
            {data.map((objeto, index) => (
                <div className={style.projectCard} key={index}>
                    <p><strong>Nombre Proyecto: </strong></p>
                    <p><strong>Máximo Integrantes: </strong></p>
                    <p><strong>Sector: </strong></p>
                    <p><strong>Demanda: </strong></p>
                </div>
            ))}
        </div>
    );
}

export default AllProjects;