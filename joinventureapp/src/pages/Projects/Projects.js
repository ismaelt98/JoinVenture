import React, { useState, useEffect } from 'react';
import Cookies from 'js-cookie';
import './Projects.css'; // Asegúrate de crear este archivo si quieres estilos específicos

const Projects = () => {
  const [data, setData] = useState([]);
  const [data1, setData1] = useState([]);
  const [mostrarProyectos, setMostrarProyectos] = useState('todos');
  const [isCrearProyecto, setCrearProyecto] = useState(false);
  const [isMisProyectos, setMisProyectos] = useState(false);
  const [isAllProyectos, setAllProyectos] = useState(true);
  const [dataRoleUser, setDataRoleUser] = useState();
  const roleuser = Cookies.get('roleuser');
  
  const updateDataRoleUser = () => {
    if (roleuser === 'PROGRAMADOR') {
      setDataRoleUser(true);
    } else {
      setDataRoleUser(false);
    }
  };

  // useEffect para ejecutar la función cuando `roleuser` cambie
  useEffect(() => {
    updateDataRoleUser();
  }, [roleuser]);

  const handleClick = (option, event) => {
    event.stopPropagation();
    if (mostrarProyectos !== option) {
      setMostrarProyectos(option);
    }
  };

  useEffect(() => {
    
    const fetchData = async () => {
      try {
        const responseAll = await fetch('http://localhost:8080/projects');
        const jsonDataAll = await responseAll.json();
        const id = Cookies.get('id');


        const responseById = await fetch(`http://localhost:8080/projects/projectsUser?id=${id}`);
        const jsonDataById = await responseById.json();
        setData(jsonDataAll);
        setData1(jsonDataById);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);

  return (
    <div style={{display: dataRoleUser ? 'block': 'none'}}>
      <div  className="navbar1">
        <button
          className={mostrarProyectos === 'todos' ? 'active' : ''}
          onClick={(e) => {
            handleClick('todos', e);
            setAllProyectos(true);
            setMisProyectos(false);
            setCrearProyecto(false);
          }}
        >
          Todos los Proyectos
        </button>
        <button
          className={mostrarProyectos === 'mis' ? 'active' : ''}
          onClick={(e) => {
            handleClick('mis', e);
            setAllProyectos(false);
            setMisProyectos(true);
            setCrearProyecto(false);
          }}
        >
          Mis Proyectos
        </button>
        <button
          className={mostrarProyectos === 'crear' ? 'active' : ''}
          onClick={(e) => {
            handleClick('crear', e);
            setAllProyectos(false);
            setMisProyectos(false);
            setCrearProyecto(true);
          }}
        >
          Crear Proyecto
        </button>
      </div>

      <div style={{ display: isAllProyectos ? 'block' : 'none' }} className='project-container'>
        {data.map((objeto, index) => (
          <div className='project-card' key={index}>
            <p>{objeto.name}</p>
            <p>{objeto.numMembers}</p>
            <p>{objeto.name_sector}</p>
            <p>{objeto.name_demanda}</p>
            <button>UNIRSE PROYECTO</button>
          </div>
        ))}
      </div>

      <div style={{ display: isMisProyectos ? 'block' : 'none' }} className='project-container'>
        {data1.map((objeto, index) => (
          <div className='project-card' key={index}>
            <p>{objeto.numMembers}</p>
            <p>{objeto.numMembers}</p>
            <p>{objeto.name_sector}</p>
            <p>{objeto.name_demanda}</p>
            <button>UNIRSE PROYECTO</button>
          </div>
        ))}
      </div>

      <div style={{ display: isCrearProyecto ? 'block' : 'none' }} className='container'>
        <form id="projectForm">
          <div className="form-group">
            <label htmlFor="projectName">Nombre del Proyecto:</label>
            <input type="text" id="projectName" name="projectName" required />
          </div>
          <div className="form-group">
            <label htmlFor="maxMembers">Límite de participantes:</label>
            <input type="number" id="maxMembers" name="maxMembers" required />
          </div>
          <div className="form-group">
            <label htmlFor="sector">Sector:</label>
            <select id="sector" name="sector" required>
              <option value="" disabled selected hidden>Selecciona un sector</option>
              {/* Aquí puedes agregar opciones para el sector */}
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="demand">Demanda:</label>
            <select id="demand" name="demand" required>
              <option value="" disabled selected hidden>Selecciona una demanda</option>
              {/* Aquí puedes agregar opciones para la demanda */}
            </select>
          </div>
          <div className="form-group">
            <input type="submit" value="Crear Proyecto" />
          </div>
        </form>
      </div>
    </div>
  );
};

export default Projects;