import React, { useState, useEffect } from 'react';
import Cookies from 'js-cookie';
import './Projects.css'; // Asegúrate de crear este archivo si quieres estilos específicos

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
  
  const [sectors, setSectors] = useState([]);
  const [selectedSector, setSelectedSector] = useState('');
  const [demands, setDemands] = useState([]);
  const [selectedDemand, setSelectedDemand] = useState('');
  const roleuser = Cookies.get('roleuser');
  const idUser = Cookies.get('id');

  //window.location.reload();

  



  const estaUnidoAlProyecto = (usuariosProyecto) => {
    const projectNameElement = document.getElementById('nameProject');
    if (Array.isArray(usuariosProyecto) && projectNameElement && projectNameElement.innerText) {
      return usuariosProyecto.some(user => user.innerText && user.innerText === projectNameElement.innerText);
    }
    return false;
  };
  
 

  const updateDataRoleUser = () => {
    if (roleuser === 'PROGRAMADOR') {
      setDataRoleUser(true);
    } else {
      setDataRoleUser(false);
    }
  };


  const handleCrearProject = async (event) => {
    event.preventDefault();
   
    try {
      

      var requestOptions = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json', // Añade este encabezado
        },
        body: JSON.stringify({ name:nameProject, numMembers, sector: { id: selectedSector }, demand: { id: parseInt(selectedDemand) }, user: {id:idUser}, userList: [{id: idUser}]}),
        redirect: 'follow'
      };
      
      const response = await fetch('http://localhost:8080/projects', requestOptions);
      const data = await response.json();
      
      console.log(data);
      
    } catch (e) {


    }
  };

  useEffect(() => {

    updateDataRoleUser();
    // eslint-disable-next-line
  }, [roleuser]);

  const handleClick = (option, event) => {
    event.stopPropagation();
    if (mostrarProyectos !== option) {
      setMostrarProyectos(option);
    }
  };
  const handleSectorChange = (event) => {
    setSelectedSector(event.target.value);
    
  };
  const handleDemandChange = (event) => {
    setSelectedDemand(event.target.value);
  };

  useEffect(() => {

    const fetchData = async () => {
      try {
        const responseAll = await fetch('http://localhost:8080/projects');
        const jsonDataAll = await responseAll.json();
        


        const responseById = await fetch(`http://localhost:8080/projects/projectsUser?id=${idUser}`);
        const jsonDataById = await responseById.json();
        const responseSectors = await fetch('http://localhost:8080/sectors');
        const dataSectors = await responseSectors.json();
        const responseDemands = await fetch('http://localhost:8080/demands');
        const dataDemands = await responseDemands.json();

        setSectors(dataSectors);
        setDemands(dataDemands);
        setData(jsonDataAll);
        setData1(jsonDataById);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
    // eslint-disable-next-line
  }, []);

  return (
    <div >
      <div style={{ display: dataRoleUser ? 'flex' : 'none' }} className="navbar1">
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
            <p id='nameProject'>{objeto.name}</p>
            <p>{objeto.numMembers}</p>
            <p>{objeto.name_sector}</p>
            <p>{objeto.name_demanda}</p>
            <p>
                {estaUnidoAlProyecto(objeto.usersName)
                  ? 'Estás unido a este proyecto'
                  : 'No estás unido a este proyecto'}
              </p>
            <button>{dataRoleUser ? 'UNIRSE PROYECTO' : 'VER PROYECTO'}</button>
          </div>
        ))}
      </div>

      <div style={{ display: isMisProyectos ? 'block' : 'none' }} className='project-container'>
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
            <input
              type="text"
              value={nameProject}
              onChange={(e) => setNameProject(e.target.value)}
              
              required
            />
            
          </div>
          <div className="form-group">
            <label htmlFor="maxMembers">Límite de participantes:</label>
            <input
              type="number"
              value={numMembers}
              onChange={(e) => setNumMembers(e.target.value)}
              
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="sector">Sector:</label>
            <select id="sector" name="sector" value={selectedSector} onChange={handleSectorChange} required>
              <option value="" disabled selected hidden>Selecciona un sector</option>
              {sectors.map((sector) => (
                <option key={sector.id} value={sector.id}>
                  {sector.name}
                </option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="demand">Demanda:</label>
            <select id="demand" name="demand" value={selectedDemand} onChange={handleDemandChange} required>
            <option value="" disabled selected hidden>Selecciona una demanda</option>
              {demands.map((demand) => (
                <option key={demand.id} value={demand.id}>
                  {demand.name}
                </option>
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