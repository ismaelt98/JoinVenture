// Array con diferentes títulos y párrafos
const titulos = ['JoinVenture: Transformando Ideas en Realidad!', 'Nuestra Misión: Unir Fuerzas para el Éxito', 'JoinVenture: Impulsando Ideas Hacia la Realidad', 'Nuestra Filosofía: Unir, Crear, Prosperar'];
const parrafos = ['En el dinámico mundo de los negocios y la innovación, surge JoinVenture como un faro de colaboración y ejecución. Somos más que una empresa, somos el puente que conecta visionarios con expertos, convirtiendo ideas en proyectos tangibles y exitosos.', 'En JoinVenture, creemos en el poder de la colaboración. Nuestra misión es catalizar el potencial creativo al unir emprendedores con profesionales especializados.', 'En el vasto panorama de proyectos e innovación, JoinVenture destaca como un motor de transformación. Nos definimos como arquitectos de sueños empresariales, trabajando incansablemente para materializar ideas y convertirlas en proyectos prósperos.', 'En el corazón de JoinVenture yace una filosofía de unión. Creemos en el poder de la colaboración, donde visionarios y ejecutores se encuentran para dar forma a ideas que impulsan el cambio y el progreso.'];
const crearCuenta = document.getElementById('crearCuenta').innerText;
const formularioLogIn = document.getElementById('loginForm');
const formularioRegister = document.getElementById('miFormulario');
const botonProgramador = document.getElementById('programadorButton');
const botonEmpresa = document.getElementById('empresaButton');
let index = 0;
let prof = crearCuenta;
window.onload = obtenerNombres;
// Función para cambiar el contenido cada 5 segundos
function cambiarContenido() {
    document.getElementById('elh2').textContent = titulos[index];
    document.getElementById('elp').textContent = parrafos[index];

    index = (index + 1) % titulos.length;
}

async function obtenerNombres() {
    try {
        const response = await fetch('http://localhost:8080/programmers-roles'); // URL de tu API
        const data = await response.json();

        

        // Iterar sobre los datos y agregar opciones al select
        data.forEach((item) => {
            const option = document.createElement('option');
            option.value = item.id; // Puedes usar algún identificador si lo necesitas
            option.text = item.name; // Suponiendo que "nombre" es el campo que contiene los nombres en tu API
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error al obtener los nombres:', error);
    }
}

function showProgramador(button) {
   
  

   
    
    document.getElementById("programadorButton").classList.add("active");
    document.getElementById("empresaButton").classList.remove("active");
}

function showEmpresa(button) {
    
   
   

  
    document.getElementById("empresaButton").classList.add("active");
    document.getElementById("programadorButton").classList.remove("active");
}


// Cambiar el contenido cada 5 segundos
setInterval(cambiarContenido, 8000); // Cambia cada 5 segundos (5000 milisegundos)


document.getElementById('crearCuenta').addEventListener("click", function (event) {

    if (prof == "CREAR CUENTA") {
        document.getElementById('loginForm').style.display = "none";
        document.getElementById('miFormulario').style.display = "block";
        document.getElementById('h2CrearCuenta').style.display = "block";
        document.getElementById('divElige').style.display = "block";
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
        document.getElementById('h2CrearCuenta').style.display = "none";
        document.getElementById('divElige').style.display = "none";
        document.getElementById('google').style.display = "flex";
        document.getElementById('h2IniciSesio').style.display = "block";
        document.getElementById('h3CrearCuentaoInici').innerText = "¿No tienes una cuenta? ¡No te preocupes!";
        document.getElementById('crearCuenta').innerText = "CREAR CUENTA";
        prof = "CREAR CUENTA";
    }


})

formularioLogIn.addEventListener('submit', function (event) {
    event.preventDefault();
    const cookieEmail = document.getElementById('emailLogIn').value;
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
                deleteCookie("email");
                setCookie("email", cookieEmail, 30);
                window.location.replace("lop.html");
            } else {
                alert("Has iniciado incorrectamente");
            }
        })
        .catch(error => console.log('error', error));
});

function deleteCookie(nombre) {
    document.cookie = nombre + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

function setCookie(nombre, valor, dias) {
    const fecha = new Date();
    fecha.setTime(fecha.getTime() + (dias * 24 * 60 * 60 * 1000));
    const expiracion = "expires=" + fecha.toUTCString();
    document.cookie = nombre + "=" + valor + ";" + expiracion + ";path=/";
}

// Llamas a la función para establecer la cookie con el nombre de usuario


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
    let phone = datosFormulario.get('telefono');
    const password = datosFormulario.get('passwordRegister');
    const confirmPassword = datosFormulario.get('confirm-password');



    if (password === confirmPassword) {

        var myHeaders = new Headers();

        myHeaders.append("Content-Type", "application/json");

        if (botonProgramador.classList.contains('active')) {

            var raw = JSON.stringify({
                "username": datosFormulario.get('name'),
                "alias": datosFormulario.get('alias'),
                "email": email,
                "password": password,
                "phone": phone,
                "roleuser": {
                    "id":parseInt(botonProgramador.value)
                },
                "projectList": [],
                "listLanguage":[],
                "listFrameworks":[]
            });

        } else {
            var raw = JSON.stringify({
                "username": datosFormulario.get('name'),
                "alias": datosFormulario.get('alias'),
                "email": email,
                "password": password,
                "phone": phone,
                "roleuser": {
                    "id":parseInt(botonEmpresa.value)
                },
                "projectList": [],
                "listLanguage":[],
                "listFrameworks":[]
            });
        }


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
             
                metodoOla();


            })
            .catch(error => console.log('error', error));

    } else {
        alert('Las contraseñas no coinciden. Por favor, inténtalo de nuevo.');

    }

}

function metodoOla() {
    document.getElementById('name').value = '';
    document.getElementById('alias').value = '';
    document.getElementById('telefono').value = '';
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
    document.getElementById('h2CrearCuenta').style.display = "none";
    document.getElementById('divElige').style.display = "none";
    prof = "CREAR CUENTA";
}







