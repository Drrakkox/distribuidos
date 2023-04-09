package urjc.code.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            return "redirect:/CuentasUsuarioAdmin.html?message=registro"; // Redirigir al usuario a la página de inicio de sesión
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
            return "redirect:/IniciarSesion?message=registro"; // Redirigir al usuario a la página de inicio de sesión
        }
    }

    private boolean ordenarNombreAscendente = true;
    private boolean ordenarPrecioAscendente = true;
    private boolean ordenarMetrosCuadradosAscendente = true;
    private boolean ordenarValoracionAscendente = true;

    @GetMapping("/anuncios/ordenarPorNombre")
    public String ordenarPorNombre(Model model) {
        List<Anuncio> anunciosOrdenados;
        if (ordenarNombreAscendente) {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
        } else {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getNombre, String.CASE_INSENSITIVE_ORDER).reversed())
                    .collect(Collectors.toList());
        }
        ordenarNombreAscendente = !ordenarNombreAscendente;
        model.addAttribute("anuncios", anunciosOrdenados);
        return "anuncios_template";
    }

    @GetMapping("/anuncios/ordenarPorPrecio")
    public String ordenarPorPrecio(Model model) {
        List<Anuncio> anunciosOrdenados;
        if (ordenarPrecioAscendente) {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getPrecio))
                    .collect(Collectors.toList());
        } else {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getPrecio).reversed())
                    .collect(Collectors.toList());
        }
        ordenarPrecioAscendente = !ordenarPrecioAscendente;
        model.addAttribute("anuncios", anunciosOrdenados);
        return "anuncios_template";
    }

    @GetMapping("/anuncios/ordenarPorMetrosCuadrados")
    public String ordenarPorMetrosCuadrados(Model model) {
        List<Anuncio> anunciosOrdenados;
        if (ordenarMetrosCuadradosAscendente) {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getM2))
                    .collect(Collectors.toList());
        } else {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getM2).reversed())
                    .collect(Collectors.toList());
        }
        ordenarMetrosCuadradosAscendente = !ordenarMetrosCuadradosAscendente;
        model.addAttribute("anuncios", anunciosOrdenados);
        return "anuncios_template";
    }

    @GetMapping("/anuncios/ordenarPorValoracion")
    public String ordenarPorValoracion(Model model) {
        List<Anuncio> anunciosOrdenados;
        if (ordenarValoracionAscendente) {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getValoracion).reversed())
                    .collect(Collectors.toList());
        } else {
            anunciosOrdenados = anuncioService.getAll().stream()
                    .sorted(Comparator.comparing(Anuncio::getValoracion))
                    .collect(Collectors.toList());
        }
        ordenarValoracionAscendente = !ordenarValoracionAscendente;
        model.addAttribute("anuncios", anunciosOrdenados);
        return "anuncios_template";
    }

    @DeleteMapping("/reseñas/{id}")
    public String deleteAnuncio(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            Anuncio anuncio = anuncioService.getAnuncioById(id);
            if (anuncio != null && anuncio.getEmail().equals(email)) {
                anuncioService.deleteAnuncio(id);
            }
        }
        return "redirect:/reseñas";
    }

    @GetMapping("/reseñas/{id}/editar")
    public String editAnuncioForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            Anuncio anuncio = anuncioService.getAnuncioById(id);
            if (anuncio != null && anuncio.getEmail().equals(email)) {
                model.addAttribute("anuncio", anuncio);
                return "editar_reseña";
            }
        }
        return "redirect:/reseñas";
    }

    @PostMapping("/reseñas/{id}/editar")
    public String updateAnuncio(@PathVariable Long id, Anuncio anuncio, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            Anuncio anuncioActual = anuncioService.getAnuncioById(id);
            if (anuncioActual != null && anuncioActual.getEmail().equals(email)) {
                anuncioService.updateAnuncio(id, anuncio);
            }
        }
        return "redirect:/reseñas";
    }

}
