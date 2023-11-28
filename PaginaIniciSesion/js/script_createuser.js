const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const formulario = document.getElementById('miFormulario');




function validarPassword(password) {
    return regex.test(password);
}



document.getElementById("cerrarFormulario").addEventListener("click", function () {
    window.location.href = '../iniciosesion.html';
});

formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    let email1 = document.getElementById('email').value;
    let existe;
    fetch(`http://localhost:8080/users/checkEmail?email=${email1}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            existe = data; // Muestra los datos recibidos en la consola

            if (!existe) {
                crearUsuario1(formulario);
            } else {
                document.getElementById("emailError").innerText = "El correo electrónico ya existe";
                document.getElementById("emailError").style.display = "block";

                setTimeout(function () {
                    document.getElementById("emailError").style.display = "none";
                }, 3000);
            }
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });



});


function crearUsuario1(formulario) {

    let data1;
    const datosFormulario = new FormData(formulario);
    let email1 = datosFormulario.get('email');
    let email12 = email1.replace(/^['"]|['"]$/g, '');

    const password1 = datosFormulario.get('password');
    const confirmPassword = datosFormulario.get('confirm-password');

    if (validarPassword(password1)) {
        if (password1 === confirmPassword) {



            const datos = {
                email: email1,
                password: password1,
                name: datosFormulario.get('name'),
                surname: datosFormulario.get('surname')


            };
            fetch('http://localhost:8080/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datos)
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Respuesta de la API:', data);
                    // Aquí puedes manejar la respuesta de la API, mostrar un mensaje, etc.
                    window.location.href = '../iniciosesion.html';
                })
                .catch(error => {
                    console.error('Error al llamar a la API:', error);
                    // Manejo de errores
                });

        } else {
            alert('Las contraseñas no coinciden. Por favor, inténtalo de nuevo.');
            // Aquí podrías mostrar un mensaje de error al usuario o realizar otras acciones
        }
    } else {
        document.getElementById("pasError").innerText = "No cumple con los requisitos";
        document.getElementById("pasError").style.display = "block";
    }




    // Aquí se simula la llamada a una API usando la función fetch



}
