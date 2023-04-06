package urjc.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;

    @GetMapping("/Home")
    public String paginaPrincipal(Model model){
        return "index.html";
    }

    @GetMapping("/Resenas")
    public String paginaResenas(Model model){
        return "anuncios_template.html";
    }
    @GetMapping("/IniciarSesion")
    public String paginaIniciarSesion(Model model){
        return "IniciarSesion.html";
    }
    @GetMapping("/NuevaResena")
    public String formCreateAnuncio(Model model){
        return "formAnuncio.html";
    }



    @GetMapping("/anuncios/")
    public String getAll(Model model){
        model.addAttribute("anuncios", anuncioService.getAll());
        return "anuncios_template";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/anuncios/")
    public String createAnuncio(Model model, Anuncio anuncio){
        anuncioService.createAnuncio(anuncio);
        model.addAttribute("anuncios", anuncioService.getAll());
        return "anuncios_template";
    }
}
