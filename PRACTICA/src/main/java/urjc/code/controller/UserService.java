package urjc.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    FakeDataBase database;

    public boolean registrarUsuario(User userModel){
        //True si se registró bien, false si no
        Boolean usuarioRegistrado = database.addUser(userModel);
        return usuarioRegistrado;
    }
}