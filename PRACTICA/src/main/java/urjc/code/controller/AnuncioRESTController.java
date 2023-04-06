package urjc.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/")
@RestController
public class AnuncioRESTController {
    @Autowired
    AnuncioService anuncioService;

    @PostMapping("/addLike/{id}/")
    public ResponseEntity<Map<String, Object>> addLike(@PathVariable Long id) {
        Anuncio anuncio = anuncioService.getAnuncio(id);
        Map<String, Object> response = new HashMap<>();
        if(anuncio == null){
            response.put("mensaje", "No se ha podido agregar un like al anuncio con ID " + id);
            return ResponseEntity.badRequest().body(response);
        }
        anuncioService.addLike(id);
        response.put("likes", anuncio.getLikes());
        response.put("mensaje", "Like agreagado al anuncio con ID " + id + " correctamente");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/anuncios/")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(anuncioService.getAll(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/anuncios/")
    public Anuncio createAnuncio(@RequestBody Anuncio anuncio) {
        anuncioService.createAnuncio(anuncio);
        return anuncio;
    }

}
