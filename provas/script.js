document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/roleset') // Reemplaza con la URL de tu API
    .then(response => response.json())
    .then(data => {
        const tablaBody = document.getElementById('datosTabla');

        // Iterar sobre los datos y agregar filas a la tabla
        data.forEach(item => {
            const fila = document.createElement('tr');

            const celdaNombre = document.createElement('td');
            celdaNombre.textContent = item.user_name;
            fila.appendChild(celdaNombre);

            const celdaAlias = document.createElement('td');
            celdaAlias.textContent = item.alias;
            fila.appendChild(celdaAlias);

            const celdaEmail = document.createElement('td');
            celdaEmail.textContent = item.email;
            fila.appendChild(celdaEmail);

            const celdaPhone = document.createElement('td');
            celdaPhone.textContent = item.phone;
            fila.appendChild(celdaPhone);

            const celdaUserRole = document.createElement('td');
            celdaUserRole.textContent = item.name_role_user;
            fila.appendChild(celdaUserRole);

            const celdaProgramerRole = document.createElement('td');
            celdaProgramerRole.textContent = item.name_role_programmer;
            fila.appendChild(celdaProgramerRole);

            const celdaBoton = document.createElement('td');
            celdaBoton.innerHTML = '<button onclick="eliminarFila(this)">DEL</button><button style=margin-left:10px; onclick="eliminarFila(this)">UPD</button>';
            fila.appendChild(celdaBoton);

            tablaBody.appendChild(fila);
        });
    })
    .catch(error => {
        console.error('Error al obtener datos:', error);
    });
});
