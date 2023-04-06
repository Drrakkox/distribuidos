function addLikeToAd(idAnuncio) {
    console.log()
    $.ajax({
        type: "POST",
        url: "/api/addLike/" + idAnuncio +"/",
        success: function(response) {
            console.log(response.likes);
            console.log(response.mensaje);
            // Actualizar el número de likes en el HTML
            var likesElement = $("#likes-" + idAnuncio);
            likesElement.text("Likes: " + response.likes);
        },
        error: function(xhr, status, error) {
            console.log("Error adding like to ad: " + error);
        }
    });
}

window.onload = function() {
  console.log("La pagina se ha cargado correctamente y esta conectado con ejemploAjax.js");
};
