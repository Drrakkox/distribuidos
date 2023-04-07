package urjc.code.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*Indica que este método maneja solicitudes HTTP GET a la ruta "/Resena".
Cuando un usuario ingresa esa ruta en un navegador web, la solicitud es manejada por este método.*/

public class LinksController {
    @GetMapping("/")
    public String lobby(Model model) {
        return "index.html";
    }

}
