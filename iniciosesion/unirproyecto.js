const projectsContainer = document.getElementById('projectsContainer');

// Realizar la solicitud a la API
fetch('http://localhost:8080/projects')
  .then(response => response.json()) // Convertir la respuesta a JSON
  .then(data => {
    // Manipular la respuesta JSON
    data.forEach(proyecto => {
      // Crear un div para cada proyecto
      const divProyecto = document.createElement('div');
      divProyecto.classList.add('proyecto');

      // Crear un botón para unir al proyecto
      const botonUnir = document.createElement('button');
      botonUnir.innerText = 'Unir Proyecto';
      botonUnir.addEventListener('click', () => {
        // Aquí puedes agregar la lógica para unir el proyecto correspondiente
        // Por ejemplo, podrías mostrar un mensaje, hacer una solicitud al servidor, etc.
        alert(`Unido al proyecto: ${proyecto.name}`);
      });

      // Mostrar el nombre del proyecto dentro del div
      const nombreUsuario = document.createElement('p');
      nombreUsuario.innerText = `Creador: ${proyecto.name_creador}`;

      const nombreProyecto = document.createElement('p');
      nombreProyecto.innerText = `Proyecto: ${proyecto.name}`;

      const cantidadPersonas = document.createElement('p');
      cantidadPersonas.innerText = `Cantidad de Personas: ${proyecto.numMembers}`;

      const sector = document.createElement('p');
      sector.innerText = `Sector: ${proyecto.name_sector}`;

      const demanda = document.createElement('p');
      demanda.innerText = `Demanda: ${proyecto.name_demanda}`;

      // Agregar el botón y el nombre del proyecto al div
      divProyecto.appendChild(nombreProyecto);
      divProyecto.appendChild(nombreUsuario);
      divProyecto.appendChild(cantidadPersonas);
      divProyecto.appendChild(sector);
      divProyecto.appendChild(demanda);
      divProyecto.appendChild(botonUnir);

      // Agregar el div del proyecto al contenedor principal
      projectsContainer.appendChild(divProyecto);
    });
  })
  .catch(error => {
    console.error('Error al obtener los datos:', error);
  });
