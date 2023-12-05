// Obtener una referencia al elemento select en el HTML
const selectElementSector = document.getElementById('sector');
const selectElementDemand = document.getElementById('demand');
const email = getCookie("email");
console.log(email);

document.getElementById("projectForm").addEventListener("submit", function (event) {
    event.preventDefault();

    let projectName = document.getElementById("projectName").value;

    let maxMembers = document.getElementById("maxMembers").value

    let sector = document.getElementById("sector").options[document.getElementById("sector").selectedIndex].text;

    let demand = document.getElementById("demand").options[document.getElementById("demand").selectedIndex].text;

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
    
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/users/buscarEmail?email=${email}`, requestOptions)
        .then(response => {
            return response.json()
        })
        .then(data => {
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var raw = JSON.stringify({
                "name": projectName,
                "numMembers": parseInt(maxMembers),
                "sector": {
                  "id": selectElementSector.value
                },
                "demand": {
                  "id": selectElementDemand.value
                },
                "user": {
                  "id": data.id
                },
                "userList": [
                  {
                    "id": data.id
                  }
                ]
              });

           

            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: raw,
                redirect: 'follow'
            };

            fetch("http://localhost:8080/projects", requestOptions)
                .then(response => {
                    return response.json()
                })
                .then()
                .catch(error => console.log('error', error));



        }).catch(error => console.log('error', error));
})
    





// Hacer la solicitud a la API
fetch('http://localhost:8080/sectors')
    .then(response => response.json())
    .then(data => {
        // Iterar sobre los datos obtenidos y crear las opciones para el select

        data.forEach(sector => {
            const option = document.createElement('option');
            option.value = sector.id; // Asignar el valor del ID de la ciudad
            option.textContent = sector.name; // Asignar el nombre de la ciudad como texto visible

            // Agregar la opción al select
            selectElementSector.appendChild(option);
        });
    })



// Obtener una referencia al elemento select en el HTML


// Hacer la solicitud a la API
fetch('http://localhost:8080/demands')
    .then(response => response.json())
    .then(data => {
        // Iterar sobre los datos obtenidos y crear las opciones para el select

        data.forEach(demand => {
            const option = document.createElement('option');

            option.value = demand.id; // Asignar el valor del ID de la ciudad
            option.textContent = demand.name; // Asignar el nombre de la ciudad como texto visible

            // Agregar la opción al select
            selectElementDemand.appendChild(option);
        });
    })



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