package urjc.code.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;

    @GetMapping("/NuevaResena")
    public String formCreateAnuncio(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            model.addAttribute("email", email);
            return "formAnuncio.html";
        } else {
            return "redirect:/CuentasUsuarioAdmin.html?message=error&errorType=email"; // Redirigir al usuario a la página de inicio de sesión
        }
    }

    @GetMapping("/anuncios/")
    public String getAll(Model model){
        model.addAttribute("anuncios", anuncioService.getAll());
        return "anuncios_template";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/anuncios/")
    public String createAnuncio(HttpServletRequest request, Model model, Anuncio anuncio){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            anuncioService.createAnuncio(anuncio);
            model.addAttribute("anuncios", anuncioService.getAll());
            return "anuncios_template";
        } else {
            return "redirect:/IniciarSesion"; // Redirigir al usuario a la página de inicio de sesión
        }
    }
}
