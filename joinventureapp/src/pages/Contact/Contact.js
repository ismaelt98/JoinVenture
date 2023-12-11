import React, { useEffect, useState } from 'react';
import './Contact.css';
import Cookies from 'js-cookie';
import imagen from '../../assets/perfil.png';
import { useNavigate } from 'react-router-dom';

const Contact = () => {
  const id = Cookies.get("id");
  const navigate = useNavigate();
  const [profileData, setProfileData] = useState({
    username: '',
    alias: '',
    email: '',
    phone: '' // Asegúrate de que este campo se devuelva desde la API.
  });

  const handleDelete = async () => {
    try {
      const requestOptions = {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        redirect: 'follow',
      };

      const response = await fetch(`http://localhost:8080/users/deleteUser?userId=${id}`, requestOptions);
      if(response.ok) {
        navigate('/login');
      } else {
        
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  
  };
  

  useEffect(() => {
    const fetchProfileData = async () => {
      try {
        const requestOptions = {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
          redirect: 'follow',
        };

        const response = await fetch(`http://localhost:8080/users/user?id=${id}`, requestOptions);
        if (response.ok) {
          const data = await response.json();
          setProfileData({
            username: data.username,
            alias: data.alias,
            email: data.email,
            phone: data.phone // Aquí asignamos el teléfono desde la respuesta.
          });
        } else {
          console.error('Error fetching profile data:', response.statusText);
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchProfileData();
  }, [id]); // Dependencia en id para reaccionar a los cambios de usuario.

  return (
    <div className='profile'>
      <div className='divImp'>
        <h2 className='username'><strong>Nombre Usuario: </strong>{profileData.username}</h2>
        
        <ul className='profile-details'>
          <li><strong>Alias: </strong>{profileData.alias}</li>
          <li><strong>Email: </strong>{profileData.email}</li>
          <li><strong>Teléfono: </strong>{profileData.phone}</li>
          {/* Más detalles que quieras mostrar del perfil */}
        </ul>
        <button className='edit-profile-btn'>Editar Perfil</button>
      <button className='delete-profile-btn' onClick={handleDelete}>Eliminar cuenta</button>
      </div>
      
      <div>
        <img src={imagen} alt='Imagen de perfil' className='profile-image' />
      </div>
    </div>
  );
};

export default Contact;
