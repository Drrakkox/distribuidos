package urjc.code.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private Map<String, String> users = new HashMap<>(); // HashMap para guardar usuario y contraseña

    @PostMapping("/login/")
    public String login(@RequestParam String email, @RequestParam String password,
                        HttpServletResponse response,
                        @CookieValue(value = "userEmailSession", required = false) String userEmail) {

        // Comprobar si el usuario y contraseña son correctos
        if (users.containsKey(email) && users.get(email).equals(password)) {

            // Guardar el email del usuario en una cookie de sesión que expire en 1 día
            Cookie cookie = new Cookie("userEmailSession", email);
            cookie.setMaxAge(60 * 60 * 24); // Expire en 1 día
            response.addCookie(cookie);

            // Redirigir al usuario a la página de inicio con mensaje de éxito
            return "redirect:/index.html?message=success";
        }

        // Si el usuario y/o contraseña no son correctos, redirigir a la página de registro con mensaje error
        return "redirect:/CuentasUsuarioAdmin.html?message=error&errorType=email";
    }

    @PostMapping("/register/")
    public String register(HttpServletResponse response, @RequestParam String email, @RequestParam String pswd,
                           @RequestParam(required = false, defaultValue = "user") String role
    ) {

        // Comprobar si el email ya existe en el HashMap
        if (users.containsKey(email)) {
            // Si el email ya existe, redirigir al usuario a la página de registro con un mensaje de error
            //return "redirect:/register?error=email";
            return "redirect:/CuentasUsuarioAdmin.html?message=error&errorType=email";

        } else {
            // Si el email no existe, crear un nuevo objeto User con los datos proporcionados por el usuario
            User newUser = new User(email, pswd, role);

            // Agregar el nuevo usuario al HashMap "users"
            users.put(email, pswd);

            // Guardar el email del usuario en una cookie de sesión que expire en 1 día
            Cookie cookie = new Cookie("userEmailSession", email);
            cookie.setMaxAge(60 * 60 * 24); // Expire en 1 día
            response.addCookie(cookie);

            // Redirigir al usuario a la página de inicio de sesión
            //return "redirect:/index.html";
            return "redirect:/index.html?message=success";
        }
    }

    @PostMapping("/logout/")
    public String logout(HttpServletResponse response, @CookieValue(value = "userEmailSession", defaultValue = "") String userEmail) {
        // Eliminar las cookies de la sesión actual
        Cookie cookieUserEmail = new Cookie("userEmail", "");
        cookieUserEmail.setMaxAge(0);
        response.addCookie(cookieUserEmail);

        Cookie cookieUserEmailSession = new Cookie("userEmailSession", "");
        cookieUserEmailSession.setMaxAge(0);
        response.addCookie(cookieUserEmailSession);

        // Obtener el nombre de usuario a partir de la cookie de sesión
        String username = "";
        if (!userEmail.isEmpty()) {
            username = userEmail.split("@")[0];
        }

        // Mostrar mensaje de éxito
        return "redirect:/index.html?message=" + username + " se ha cerrado su sesión";
    }

}
