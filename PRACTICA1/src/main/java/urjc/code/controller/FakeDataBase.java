package urjc.code.controller;

/*Importa la clase Component del framework Spring para anotar
esta clase como un componente que se debe reistrar en el contexto de la aplicacion*/
import org.springframework.stereotype.Component;
/*Importa la clase HashMap de Java, que se utilizará para simular la BD en memoria*/
import java.util.Collection;
import java.util.HashMap;

@Component //anota esta clase como componente de Spring, lo que permitirá que se inyecte en otras clases

public class FakeDataBase {
    //Simulación de BD con un mapa, la clave sería correo (único) y el valor el propio usuario.
    /*Define una propiedad llamada usuarios, que es un HashMap que asocia una clave (el correo electrónico del usuario)
    con un objeto UserModel que representa al usuario. Este HashMap se utiliza para simular una base de datos en memoria.*/
    private HashMap<String, User> usuarios = new HashMap<String, User>();

    public void inicializarBaseDeDatos(){
        User admin = new User("admin@admin", "admin", "admin");
        this.addUser(admin);
    }

    public Collection<User> getAllUsers() {
        return usuarios.values();
    }

    public User getUserByEmail(String email) {
        return usuarios.get(email);
    }


    /*Define un método llamado addUser que acepta un objeto UserModel como parámetro y devuelve un valor booleano que
    indica si el registro se realizó con éxito.*/
    public Boolean addUser(User usuario){
        boolean registroExitoso = false;
        //Usuario no registrado
        if (!this.usuarios.containsKey(usuario.getEmail())){ //Evalúa si el correo electrónico del usuario ya está registrado en la base de datos simulada.
            //Si el correo electrónico no está registrado, agrega el objeto UserModel al HashMap utilizando el correo electrónico como clave.
            this.usuarios.put(usuario.getEmail(), usuario);
            registroExitoso = true;
        }
        return registroExitoso; //Devuelve el valor booleano que indica si el registro se realizó con éxito o no.
    }

    public User authenticateUser(String email, String password) {
        User user = usuarios.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


}