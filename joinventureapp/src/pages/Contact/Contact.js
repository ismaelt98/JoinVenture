import React, { useEffect, useState } from 'react';
import './Contact.css'; // Asegúrate de crear este archivo si quieres estilos específicos
import Cookies from 'js-cookie';


const Contact = () => {
  const id = Cookies.get("id");
  const [data1, setData1] = useState({
    username: '',
    alias: '',
    email: '',
  });


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
    //eslint-disable-next-line
  }, []);




  return (
    <div>
      <div className='profile'>
        
        <h2 className='username'>{data1.username}</h2>
        <p className='bio'>Descripción corta sobre el usuario</p>
        <ul className='profile-details'>
          <li><strong>Alias:</strong>{data1.alias}</li>
          <li><strong>Email:</strong>{data1.email}</li>
          <li><strong>Telefono:</strong>{data1.phone}</li>
        </ul>



      </div>
      {/* Aquí va el resto de tu contenido para la página de Proyectos */}
    </div>
  );
};

export default Contact;
