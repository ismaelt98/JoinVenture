document.getElementById("projectForm").addEventListener("submit", function (event) {
    event.preventDefault();

    let projectName = document.getElementById("projectName").value;

    let maxMembers = document.getElementById("maxMembers").value

    let sector = document.getElementById("sector").options[document.getElementById("sector").selectedIndex].text;

    let demand = document.getElementById("demand").options[document.getElementById("demand").selectedIndex].text;


    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "name": projectName,
        "numMembers": parseInt(maxMembers)
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
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

    
    
})