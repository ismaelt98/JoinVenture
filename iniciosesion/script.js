// Array con diferentes títulos y párrafos
const titulos = ['JoinVenture: Transformando Ideas en Realidad!', 'Nuestra Misión: Unir Fuerzas para el Éxito', 'JoinVenture: Impulsando Ideas Hacia la Realidad', 'Nuestra Filosofía: Unir, Crear, Prosperar'];
const parrafos = ['En el dinámico mundo de los negocios y la innovación, surge JoinVenture como un faro de colaboración y ejecución. Somos más que una empresa, somos el puente que conecta visionarios con expertos, convirtiendo ideas en proyectos tangibles y exitosos.', 'En JoinVenture, creemos en el poder de la colaboración. Nuestra misión es catalizar el potencial creativo al unir emprendedores con profesionales especializados.', 'En el vasto panorama de proyectos e innovación, JoinVenture destaca como un motor de transformación. Nos definimos como arquitectos de sueños empresariales, trabajando incansablemente para materializar ideas y convertirlas en proyectos prósperos.', 'En el corazón de JoinVenture yace una filosofía de unión. Creemos en el poder de la colaboración, donde visionarios y ejecutores se encuentran para dar forma a ideas que impulsan el cambio y el progreso.'];
const crearCuenta = document.getElementById('crearCuenta').innerText;
const formularioLogIn = document.getElementById('loginForm');
const formularioRegister = document.getElementById('miFormulario');
let index = 0;
let prof = crearCuenta;

// Función para cambiar el contenido cada 5 segundos
function cambiarContenido() {
    document.getElementById('elh2').textContent = titulos[index];
    document.getElementById('elp').textContent = parrafos[index];

    index = (index + 1) % titulos.length;
}

// Cambiar el contenido cada 5 segundos
setInterval(cambiarContenido, 8000); // Cambia cada 5 segundos (5000 milisegundos)


document.getElementById('crearCuenta').addEventListener("click", function (event) {

    if (prof == "CREAR CUENTA") {
        document.getElementById('loginForm').style.display = "none";
        document.getElementById('miFormulario').style.display = "block";
        document.getElementById('ralla').style.display = "none";
        document.getElementById('google').style.display = "none";
        document.getElementById('h2IniciSesio').style.display = "none";
        document.getElementById('h3CrearCuentaoInici').innerText = "¿Ya tienes cuenta? ¡Inicia sesión ahora mismo y forma parte de nuestra comunidad!";
        document.getElementById('crearCuenta').innerText = "INICIAR SESION";
        prof = "INICIAR SESION";
    } else {
        document.getElementById('loginForm').style.display = "block";
        document.getElementById('miFormulario').style.display = "none";
        document.getElementById('ralla').style.display = "block";
        document.getElementById('google').style.display = "flex";
        document.getElementById('h2IniciSesio').style.display = "block";
        document.getElementById('h3CrearCuentaoInici').innerText = "¿No tienes una cuenta? ¡No te preocupes!";
        document.getElementById('crearCuenta').innerText = "CREAR CUENTA";
        prof = "CREAR CUENTA";
    }


})

formularioLogIn.addEventListener('submit', function (event) {
    event.preventDefault();

    var formdata = new FormData();
    formdata.append("email", document.getElementById('emailLogIn').value);
    formdata.append("password", document.getElementById('passwordLogIn').value);

    var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/users/login", requestOptions)
        .then(response => response.text())
        .then(result => {
            if (result == "true") {
                alert("Has iniciado correctamente");
            } else {
                alert("Has iniciado incorrectamente");
            }
        })
        .catch(error => console.log('error', error));
})

formularioRegister.addEventListener('submit', function (event) {
    event.preventDefault();
    let email = document.getElementById('emailRegister').value;
    let existe;
    fetch(`http://localhost:8080/users/checkEmail?email=${email}`, {
        method: 'GET', // Método de la solicitud (GET, POST, etc.)
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*' // Agrega el encabezado para permitir cualquier origen (No recomendado en producción)
            // Si conoces el origen específico, puedes reemplazar '*' con 'http://mi-sitio.com' por ejemplo
        }
    })
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
                crearUsuario1(formularioRegister);
            } else {
                alert("El correo ya existe!!!");
            }
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });



});


function crearUsuario1(formularioReg) {


    const datosFormulario = new FormData(formularioReg);
    let email = datosFormulario.get('emailRegister');
    const password = datosFormulario.get('passwordRegister');
    const confirmPassword = datosFormulario.get('confirm-password');


    if (password === confirmPassword) {

        var myHeaders = new Headers();
        myHeaders.append("X-RapidAPI-Key", "0bdf6514e1msh5b44844e8072240p1ff1d9jsna4309d6f33e5");
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({
            "username": datosFormulario.get('name'),
            "lastname": datosFormulario.get('surname'),
            "email": email,
            "password": password
        });

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("http://localhost:8080/users", requestOptions)
            .then(response => response.text())
            .then(result => {

                alert("El usuario se creo correctamente");
                document.getElementById('name').value = '';
                document.getElementById('lastname').value = '';
                document.getElementById('emailRegister').value = '';
                document.getElementById('passwordRegister').value = '';
                document.getElementById('confirm-password').value = '';
                
                document.getElementById('loginForm').style.display = "block";
                document.getElementById('miFormulario').style.display = "none";
                document.getElementById('ralla').style.display = "block";
                document.getElementById('google').style.display = "flex";
                document.getElementById('h2IniciSesio').style.display = "block";
                document.getElementById('h3CrearCuentaoInici').innerText = "¿No tienes una cuenta? ¡No te preocupes!";
                document.getElementById('crearCuenta').innerText = "CREAR CUENTA";
                prof = "CREAR CUENTA";
            })
            .catch(error => console.log('error', error));

    } else {
        alert('Las contraseñas no coinciden. Por favor, inténtalo de nuevo.');

    }

}




