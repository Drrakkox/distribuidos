package es.dws.AnuncioService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anuncio {
    private long id;
    private String email;
    private float valoracion;

    private String nombre;
    private String direccion;
    private String localidad;
    private int m2;
    private int precio;
    private String comentario;
    private int likes;



    public void addLike(){
        this.likes++;
    }
}
