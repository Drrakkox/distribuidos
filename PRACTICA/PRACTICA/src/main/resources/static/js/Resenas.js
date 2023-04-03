   //BOTON
   function toggleComentarios(botonesMostrarComentarios, zonasComentarios) {
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
   }

   //ORDENAR POR PRECIO
    function ordenarPorPrecio() {
        //seleccionamos todas las reseñas
        const reseñas = document.querySelectorAll('.col-md-4');
        //creamos un array
        const reseñasArray = Array.from(reseñas);

        //vamos iterando para ir colocando de menor a mayor
        reseñasArray.sort((a, b) => {
            //p:nth-of-type(6) de las reseñas el elemento 6 es el del precio
            const precioA = parseFloat(a.querySelector('p:nth-of-type(6)').textContent.split(' ')[1]);
            const precioB = parseFloat(b.querySelector('p:nth-of-type(6)').textContent.split(' ')[1]);
            return precioA - precioB;
        });

        //colocamos las reseñas ordenadas en el contenedor de reseñas
        const contenedorReseñas = document.querySelector('.container .row');
        reseñasArray.forEach(reseña => contenedorReseñas.appendChild(reseña));
    }

//ORDENACION POR VALORACIONES, DE LA MEJOR A LA PEOR
    function valoracionMejor() {
    //seleccionamos todas las reseñas
    const reseñas = document.querySelectorAll('.col-md-4');
     //creamos un array
        const reseñasArray = Array.from(reseñas);

        //vamos iterando para ir colocando de mayor a menor valoracion
        reseñasArray.sort((a, b) => {
            const valoracionA = parseFloat(a.querySelector('p:nth-of-type(1)').textContent.split(' ')[1]);
            const valoracionB = parseFloat(b.querySelector('p:nth-of-type(1)').textContent.split(' ')[1]);
            return valoracionB - valoracionA;
        });

        //colocamos las reseñas ordenadas en el contenedor de reseñas
        const contenedorReseñas = document.querySelector('.container .row');
        reseñasArray.forEach(reseña => contenedorReseñas.appendChild(reseña));
    }

//ORDENACION ALFABETICAMENTE
    function ordenacionAlfabetica() {
        // Seleccionamos todas las reseñas y las convertimos en un array para poder ordenarlas
        const reseñas = Array.from(document.querySelectorAll('.col-md-4'));

        // Ordenamos las reseñas por el valor del campo "Nombre"
        reseñas.sort((a, b) => {
        const nombreA = a.querySelector('p:nth-of-type(2)').textContent.trim();
        const nombreB = b.querySelector('p:nth-of-type(2)').textContent.trim();
        return nombreA.localeCompare(nombreB);
        });

        // Añadimos las reseñas ordenadas al contenedor
        const contenedorReseñas = document.querySelector('.container .row');
        reseñas.forEach(reseña => contenedorReseñas.appendChild(reseña));
    }