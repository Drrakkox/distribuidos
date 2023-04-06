package AnuncioService;

import java.util.Objects;

public class Anuncio {
    private long id;
    private String nombre;
    private String asunto;
    private String comentario;
    private int likes;

    public Anuncio(long id, String nombre, String asunto, String comentario, int likes) {
        this.id = id;
        this.nombre = nombre;
        this.asunto = asunto;
        this.comentario = comentario;
        this.likes = likes;
    }
    public Anuncio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", asunto='" + asunto + '\'' +
                ", comentario='" + comentario + '\'' +
                ", likes=" + likes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anuncio anuncio)) return false;
        return getId() == anuncio.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void addLike(){
        this.likes++;
    }
}
