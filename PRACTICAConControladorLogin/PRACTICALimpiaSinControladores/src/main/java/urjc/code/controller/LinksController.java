package urjc.code.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*Indica que este método maneja solicitudes HTTP GET a la ruta "/Resena".
Cuando un usuario ingresa esa ruta en un navegador web, la solicitud es manejada por este método.*/

public class LinksController {
    @GetMapping("/")
    public String lobby(Model model) {
        return "index";
    }
    @GetMapping("/CuentaUsuarioAdmin")
    public String CuentaUsuarioAdmin(Model model) {
        return "/CuentaUsuarioAdmin";
    }

    @GetMapping("/Inscribirse")
    public String Inscribirse(Model model) {
        return "/Inscribirse";
    }

    @GetMapping("/NuevaResena")
    public String NuevaResena(Model model) {
        return "/NuevaResena";
    }

    @GetMapping("/Resena")
    public String Resena(Model model) {
        return "/Resena";
    }

}
