package urjc.code.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //cuando alguien se registra
    private Map<String, String> users = new HashMap<>(); // HashMap para guardar usuario y contraseña

    @GetMapping("/api/users")
    @ResponseBody
    public Collection<String> getAllUsers() {
        return users.keySet();
    }

    @GetMapping("/IniciarSesion")
    public String CuentasUsuarioAdmin(Model model){
        return "CuentasUsuarioAdmin.html";
    }

    @PostMapping("/login/")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            HttpServletRequest request,
                            Model model) {
        User user = userService.authenticateUser(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            return "redirect:/index.html?message=success";
        }
        return "redirect:/CuentasUsuarioAdmin.html?message=error";
    }


    @PostMapping("/register/")
    public String register(@RequestParam String email, @RequestParam String password,
                           @RequestParam(required = false, defaultValue = "user") String role,
                           HttpServletRequest request) {

        User newUser = new User(email, password, role);

        if (userService.registrarUsuario(newUser)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            return "redirect:/index.html?message=success";
        } else {
            return "redirect:/CuentasUsuarioAdmin.html?message=error&errorType=email";
        }
    }


    @PostMapping("/logout/")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/index.html?message=logout";
    }


    @PostMapping("/admin_login/")
    public String adminlogin(@RequestParam String email, @RequestParam String password, @RequestParam String role,
                             HttpServletResponse response,
                             @CookieValue(value = "userEmailSession", required = false) String userEmail) {

        // Comprobar si el usuario y contraseña son correctos
        if (users.containsKey(email) && users.get(role).equals("admin")) {

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

}
