const email = getCookie("email");
const projectsContainer = document.getElementById('projectsContainer');
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var requestOptions = {
    method: 'GET',
    headers: myHeaders,
  
    redirect: 'follow'
};

fetch(`http://localhost:8080/users/buscarEmail?email=${email}`, requestOptions)
    .then(response => {
      return  response.json()
    })
    .then(result => {
        console.log(result);
        setCookie("id", result.id, 40);
    })
    .catch(error => console.log('error', error));




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
                console.log(proyecto.id)
            });

            // Mostrar el nombre del proyecto dentro del div
            const nombreUsuario = document.createElement('p');
            nombreUsuario.innerText = `PRODUCT OWNER: ${proyecto.name_creador}`;

            const nombreProyecto = document.createElement('p');
            nombreProyecto.innerText = `NAME OF PROJECT: ${proyecto.name}`;

            const cantidadPersonas = document.createElement('p');
            cantidadPersonas.innerText = `NUMBER OF MEMBERS: ${proyecto.numMembers}`;

            const sector = document.createElement('p');
            sector.innerText = `SECTOR: ${proyecto.name_sector}`;

            const demanda = document.createElement('p');
            demanda.innerText = `DEMAND: ${proyecto.name_demanda}`;

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


function setCookie(nombre, valor, dias) {
    const fecha = new Date();
    fecha.setTime(fecha.getTime() + (dias * 24 * 60 * 60 * 1000));
    const expiracion = "expires=" + fecha.toUTCString();
    document.cookie = nombre + "=" + valor + ";" + expiracion + ";path=/";
}


function getCookie(nombre) {
    const nombreC = nombre + "=";
    const cookies = decodeURIComponent(document.cookie).split(';');

    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i];
        while (cookie.charAt(0) === ' ') {
            cookie = cookie.substring(1);
        }
        if (cookie.indexOf(nombreC) === 0) {
            return cookie.substring(nombreC.length, cookie.length);
        }
    }

    return "";
}
