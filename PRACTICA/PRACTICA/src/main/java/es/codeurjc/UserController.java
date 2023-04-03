package es.codeurjc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //controlar que maneja peticiones HTTP
public class UserController { //define la clase UserController
    @GetMapping("/") //anota un método con la ruta de acceso "/" y el método GET. Maneje petición GET en la raíz de la URL del servidor
    //método que devuelve una cadena que representa el nombre de archivo HTML que debe mostrarse en respuesta a la solicitud GET
    public String personal_data(Model model) {
        return "index.html"; //devuelve nombre del archivo HTML como respuesta a la solicitud GET
    }

    @Autowired //campo para indicar que Spring debe inyectar una instancia de UserService en este campo
    private UserService userService; //campo llamado userService del tipo UserService, que se utilizará para acceder a métodos en UserService

    @PostMapping("/api/registarUsuario/") //método con ruta asociada y el método POST. Maneja una petición POST a la URL del servidor
    /*metodo que acepta solicitud con un cuerpo que contiene un objeto UserModel.
    * Utiliza este objeto para registrar un nuevo usuario en la BD a través del
    * servicio UserService y devuelve ResponseEntity*/
    public ResponseEntity<Object> registrarUsuario (@RequestBody UserModel user){
        /*invoca al método registrarUsuario del objeto UserService intecta en este
        * controlador. El método devuelve un valor booleano que indica si el usuario
        * se registró correctamente en la BD*/
        boolean usuarioRegistrado = this.userService.registrarUsuario(user);
        if (usuarioRegistrado)
            /*si el usuario se registró correctamente, devuelve una ResponseEntity
            * con un estado de éxito (OK)*/
            return ResponseEntity.ok().build();
        else
            /*devuelve una ResponseEntity con un estado de error interno del servidor*/
            return ResponseEntity.internalServerError().build();
    }

}
