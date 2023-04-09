// Verificar si hay datos de inicio de sesión almacenados
if (localStorage.getItem("usuario") && localStorage.getItem("contrasena")) {
  // Si hay datos almacenados, rellenar automáticamente los campos de inicio de sesión
  document.getElementById("usuario").value = localStorage.getItem("usuario");
  document.getElementById("contrasena").value = localStorage.getItem("contrasena");
}

// Manejar el evento de clic en el botón de inicio de sesión
document.getElementById("boton_inicio_sesion").addEventListener("click", function() {
  // Deshabilitar el botón de inicio de sesión
  document.getElementById("boton_inicio_sesion").setAttribute("disabled", "disabled");
  // Cambiar el texto del botón a "Iniciando sesión..."
  document.getElementById("boton_inicio_sesion").textContent = "Iniciando sesión...";

  // Almacenar los datos de inicio de sesión en localStorage
  localStorage.setItem("email", document.getElementById("email").value);
  localStorage.setItem("password", document.getElementById("password").value);

});