var urlParams = new URLSearchParams(window.location.search);
var message = urlParams.get('message');
var errorType = urlParams.get('errorType');

if (message === "error") {
    if (errorType === "email") {
        alert("El email ingresado ya existe. Por favor, intente con un email diferente.");
    } else if(errorType === "session_already_active") {
        alert("Ya hay un inicio de sesión. Por favor, cierre la otra sesión.");
    } else {
        alert("Ha ocurrido un error al registrarse. Por favor, intente de nuevo más tarde.");
    }
} else if (message === 'logout') {
    alert("Sesión cerrada");
} else if (message === "success") {
    alert("Operación realizada con éxito (Registro / Inicio Sesión finalizado)");
}
