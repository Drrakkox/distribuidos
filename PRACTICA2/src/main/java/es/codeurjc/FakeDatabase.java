package es.codeurjc;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class FakeDatabase {
    private HashMap<String, UserModel> usuarios= new HashMap<String, UserModel>();

    public boolean addUser(UserModel usuario){
        boolean registroExitoso= false;
        if (!this.usuarios.containsKey(usuario.getEmail())){
            this.usuarios.put(usuario.getEmail(), usuario);
            registroExitoso=true;
        }
        return registroExitoso;
    }
}
