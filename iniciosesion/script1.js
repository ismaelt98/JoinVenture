const nombreUsuario = getCookie("email");
window.onload = verificarCookie;

var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");



var requestOptions = {
  method: 'GET',
  headers: myHeaders,
  
  redirect: 'follow'
};

fetch(`http://localhost:8080/users/buscarEmail?email=${nombreUsuario}`, requestOptions)
  .then(response => response.json())
  .then(data => {
   
        
        // Asignar los valores del JSON a las celdas de la fila
        document.getElementById('username').innerText = data.phone;
       
        // Asigna más celdas según la estructura de tu JSON
    
  })
  .catch(error => console.log('error', error));


document.getElementById("btnN").addEventListener('click', function(event){

    deleteCookie("email");
    window.location.href ="index.html";
    
})
function verificarCookie() {
    const nombreCookie = "email"; // Reemplaza con el nombre de tu cookie

    // Verifica si la cookie está presente
    if (document.cookie.indexOf(nombreCookie) === -1) {
      // Si la cookie no está presente, redirige al usuario a otra página
      window.location.replace("index.html"); // Reemplaza con la página de login
    }
  }

  // Llama a la función al cargar la página para verificar la cookie
  
function deleteCookie(nombre) {
    document.cookie = nombre + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}


  function getCookie(nombre) {
    const nombreC = nombre + "=";
    const cookies = decodeURIComponent(document.cookie).split(';');
    
    for(let i = 0; i < cookies.length; i++) {
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