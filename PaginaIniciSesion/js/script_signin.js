const formulario = document.getElementById('miFormulario');

/*document.getElementById("btnSign").addEventListener("submit", function(event){
    document.getElementById('popupMessage').style.display = 'none';
})*/




formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    var formdata = new FormData();
    formdata.append("email", document.getElementById('email').value);
    formdata.append("password", document.getElementById('password').value);


    var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/users/login", requestOptions)
        .then(response => response.text())
        .then(result => {           
            if (result == "true") {
                console.log(result);
                //window.location.href = "./prueba.html"
            } else {
                alert("EMAIL O CONTRASEÑA INCORRECTAS");
            }
        })
        .catch(error => console.log('error', error));


})