import React, { useEffect, useState } from 'react';
import './Contact.css'; // Asegúrate de crear este archivo si quieres estilos específicos
import Cookies from 'js-cookie';


const Contact = () => {
  const id = Cookies.get("id");
  const [data1, setData1] = useState();
  
  
  useEffect(() => { 
    const fetchData = async () => {
      try {
        const requestOptions = {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
          redirect: 'follow',
        };

        const response = await fetch(`http://localhost:8080/users/user?id=${id}`, requestOptions);
        const data = await response.json();
        setData1(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
fetchData();
  }, [id]);
  

  

  return (
    <div>
      <div className='project-container'>
        
          <div className='project-card' >
          <p>{data1.username}</p>
          <p>{data1.alias}</p>
          <p>{data1.email}</p>
              
          </div>
        
      </div>
      {/* Aquí va el resto de tu contenido para la página de Proyectos */}
    </div>
  );
};

export default Contact;
