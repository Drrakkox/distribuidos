package es.codeurjc;

@Entity
public class UserModel {
    //Email del usuario
    private String email;

    //Contraseña del usuario
    private String password;

    //Rol de usuario, user por defecto
    private String role= "user";

    //CONSTRUCTOR
    public UserModel(String email, String password, String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
