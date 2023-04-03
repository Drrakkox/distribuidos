//ESTO ES EL CODIGO PARA EL BOTON QUE OCULTA Y MUESTRA LOS COMENTARIOS
        const botonesMostrarComentarios = document.getElementsByClassName('mostrar-comentario');
        const zonasComentarios = document.getElementsByClassName('comentario');

        for (let i = 0; i < botonesMostrarComentarios.length; i++) {
            botonesMostrarComentarios[i].addEventListener('click', function() {
                if (zonasComentarios[i].style.display === 'none') {
                    zonasComentarios[i].style.display = 'block';
                    botonesMostrarComentarios[i].innerText = 'Ocultar comentario';
                } else {
                    zonasComentarios[i].style.display = 'none';
                    botonesMostrarComentarios[i].innerText = 'Mostrar comentario';
                }
            });
        }