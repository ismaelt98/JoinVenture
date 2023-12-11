import React, { useState, useEffect } from 'react';
import Cookies from 'js-cookie';
import './Projects.css';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Projects = () => {
  const [nameProject, setNameProject] = useState('');
  const [numMembers, setNumMembers] = useState();
  const [data, setData] = useState([]);
  const [data1, setData1] = useState([]);
  const [mostrarProyectos, setMostrarProyectos] = useState('todos');
  const [isCrearProyecto, setCrearProyecto] = useState(false);
  const [isMisProyectos, setMisProyectos] = useState(false);
  const [isAllProyectos, setAllProyectos] = useState(true);
  const [dataRoleUser, setDataRoleUser] = useState();
  const [participantes, setParticipantes] = useState([]);
  /*const [filtroSector, setFiltroSector] = useState('');
  const [filtroDemanda, setFiltroDemanda] = useState('');*/
  const [sectors, setSectors] = useState([]);
  const [selectedSector, setSelectedSector] = useState('');
  const [demands, setDemands] = useState([]);
  const [selectedDemand, setSelectedDemand] = useState('');
  const roleuser = Cookies.get('roleuser');
  const idUser = Cookies.get('id');

  const verParticipantes = async (idProyecto) => {
    const response = await fetch(`http://localhost:8080/projects/${idProyecto}/participantes`);
    const data = await response.json();
    //falta mirar en la api el metodo
    setParticipantes(data);
    
  };

  const unirseAlProyecto = async (idProyecto) => {
    const response = await fetch(`http://localhost:8080/projects/${idProyecto}/unirse`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ idUsuario: idUser })
    });
    if (response.ok) {
      toast.done('TE UNISTE AL PROYECTO', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }else{
      toast.error('YA TE UNISTE AL PROYECTO', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  };

  const estaUnidoAlProyecto = (proyecto) => {
    return proyecto.userIds.includes(idUser);
  };

  useEffect(() => {
    updateDataRoleUser();
  }, [roleuser]);

  useEffect(() => {
    const fetchData = async () => {
      const responseAll = await fetch('http://localhost:8080/projects');
      const jsonDataAll = await responseAll.json();
      const responseById = await fetch(`http://localhost:8080/projects/projectsUser?id=${idUser}`);
      const jsonDataById = await responseById.json();
      const responseSectors = await fetch('http://localhost:8080/sectors');
      const dataSectors = await responseSectors.json();
      const responseDemands = await fetch('http://localhost:8080/demands');
      const dataDemands = await responseDemands.json();
      setData(jsonDataAll);
      setData1(jsonDataById);
      setSectors(dataSectors);
      setDemands(dataDemands);
    };
    fetchData();
  }, []);

  const handleCrearProject = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/projects', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: nameProject, numMembers, sector: { id: selectedSector }, demand: { id: parseInt(selectedDemand) }, user: { id: idUser }, userList: [{id:idUser}] })
      });
      if (response.ok) {
        // Actualizar el estado o la UI según sea necesario
      }
    } catch (error) {
      console.error('Error creating project:', error);
    }
  };

  const updateDataRoleUser = () => {
    if (roleuser === 'PROGRAMADOR') {
      setDataRoleUser(true);
    } else {
      setDataRoleUser(false);
    }
  };

  const handleClick = (option, event) => {
    event.stopPropagation();
    if (mostrarProyectos !== option) {
      setMostrarProyectos(option);
      setAllProyectos(option === 'todos');
      setMisProyectos(option === 'mis');
      setCrearProyecto(option === 'crear');
    }
  };

  const handleSectorChange = (event) => {
    setSelectedSector(event.target.value);
  };

  const handleDemandChange = (event) => {
    setSelectedDemand(event.target.value);
  };

  return (
    <div>
      <div style={{ display: dataRoleUser ? 'flex' : 'none' }} className="navbar1">
        <button className={mostrarProyectos === 'todos' ? 'active' : ''} onClick={(e) => handleClick('todos', e)}>Todos los Proyectos</button>
        <button className={mostrarProyectos === 'mis' ? 'active' : ''} onClick={(e) => handleClick('mis', e)}>Mis Proyectos</button>
        <button className={mostrarProyectos === 'crear' ? 'active' : ''} onClick={(e) => handleClick('crear', e)}>Crear Proyecto</button>
      </div>

      <div style={{ display: isAllProyectos ? 'flex' : 'none' }} className='project-container'>
        {data.map((objeto, index) => (
          <div className='project-card' key={index}>
            <p><strong>Nombre Proyecto: </strong>{objeto.name}</p>
            <p><strong>Máximo Integrantes: </strong>{objeto.numMembers}</p>
            <p><strong>Sector: </strong>{objeto.name_sector}</p>
            <p><strong>Demanda: </strong>{objeto.name_demanda}</p>
            {dataRoleUser && !objeto.usersName.includes(data1.name) && <button onClick={() => unirseAlProyecto(objeto.id)}>Unirse al Proyecto</button>}
            {roleuser === 'EMPRESA' && <button onClick={() => verParticipantes(objeto.id)}>Ver Participantes</button>}
          </div>
        ))}
        <div>
          <ToastContainer />
        </div>
      </div>

      <div style={{ display: isMisProyectos ? 'flex' : 'none' }} className='project-container'>
        {data1.map((objeto, index) => (
          <div className='project-card' key={index}>
            <p>{objeto.name}</p>
            <p>{objeto.numMembers}</p>
            <p>{objeto.name_sector}</p>
            <p>{objeto.name_demanda}</p>
          </div>
        ))}
      </div>

      <div style={{ display: isCrearProyecto ? 'block' : 'none' }} className='container'>
        <form id="projectForm" onSubmit={handleCrearProject}>
          <div className="form-group">
            <label htmlFor="projectName">Nombre del Proyecto:</label>
            <input type="text" value={nameProject} onChange={(e) => setNameProject(e.target.value)} required />
          </div>
          <div className="form-group">
            <label htmlFor="maxMembers">Límite de participantes:</label>
            <input type="number" value={numMembers} onChange={(e) => setNumMembers(e.target.value)} required />
          </div>
          <div className="form-group">
            <label htmlFor="sector">Sector:</label>
            <select id="sector" name="sector" value={selectedSector} onChange={handleSectorChange} required>
              <option value="" disabled selected hidden>Selecciona un sector</option>
              {sectors.map((sector) => (
                <option key={sector.id} value={sector.id}>{sector.name}</option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="demand">Demanda:</label>
            <select id="demand" name="demand" value={selectedDemand} onChange={handleDemandChange} required>
              <option value="" disabled selected hidden>Selecciona una demanda</option>
              {demands.map((demand) => (
                <option key={demand.id} value={demand.id}>{demand.name}</option>
              ))}
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
