package es.codeurjc;

import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/posting/")
    public ResponseEntity<Object> registrarUsuario(@RequestBody UserModel user){
        boolean usuarioRegistrado= this.userService.registrarUsuario(user);
        if(usuarioRegistrado)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.internalServerError().build();
    }
}
