const userEmail = getEmail();

function showHideButtons() {
    const modifyButtons = document.querySelectorAll('.btnModificarReseña');
    const deleteButtons = document.querySelectorAll('.btnEliminarReseña');

    modifyButtons.forEach(button => {
        const creatorEmail = button.getAttribute('data-creator');
        if (creatorEmail === userEmail) {
            button.style.display = 'inline';
            button.addEventListener('click', editReview);
        } else {
            button.style.display = 'none';
        }
    });

    deleteButtons.forEach(button => {
        const creatorEmail = button.getAttribute('data-creator');
        if (creatorEmail === userEmail) {
            button.style.display = 'inline';
            button.addEventListener('click', deleteReview);
        } else {
            button.style.display = 'none';
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    showHideButtons();
});

function editReview(event) {
    const id = event.target.getAttribute('data-id');
    window.location.href = `/reseñas/${id}/editar`;
}

function deleteReview(event) {
    const id = event.target.getAttribute('data-id');
    fetch(`/reseñas/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('No se pudo eliminar la reseña');
        }
    });
}