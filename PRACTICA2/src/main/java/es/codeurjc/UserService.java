package es.codeurjc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    FakeDatabase database;
    public boolean registrarUsuario(UserModel userModel){
        Boolean usuarioRegistrado= database.addUser(userModel);
        return usuarioRegistrado;
    }
}
