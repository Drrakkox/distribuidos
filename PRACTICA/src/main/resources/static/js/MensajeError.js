    var urlParams = new URLSearchParams(window.location.search);
    var message = urlParams.get('message');
    var errorType = urlParams.get('errorType');

    if (message === "error") {
        if (errorType === "email") {
            alert("El email ingresado ya existe. Por favor, intente con un email diferente.");
        } else {
            alert("Ha ocurrido un error al registrarse. Por favor, intente de nuevo más tarde.");
        }
    } else if (message === "success") {
        alert("Operación realizada con éxito (Registro / Inicio Sesión finalizado)");
    }