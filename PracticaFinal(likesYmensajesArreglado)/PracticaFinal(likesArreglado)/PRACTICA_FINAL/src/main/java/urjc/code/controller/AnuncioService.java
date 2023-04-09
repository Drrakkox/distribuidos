package urjc.code.controller;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AnuncioService {

    private Map<Long, Anuncio> anuncioHashMap = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    public AnuncioService(){
        long tem= id.incrementAndGet();
        anuncioHashMap.put(tem, new Anuncio(tem, "noemi@gmail.com",6, "Noemi", "Calle Saturno","Parla", 4000,57000, "Mucha profesionalidad en su trabajo",4));
    }

    public Collection<Anuncio> getAll()
    {
        return anuncioHashMap.values();
    }

    public Anuncio getAnuncio(Long id){
        return anuncioHashMap.get(id);
    }

    public void addLike(Long id){
        Anuncio anuncio = anuncioHashMap.get(id);
        anuncio.addLike();
    }

    public Anuncio createAnuncio(Anuncio anuncio){
        long tem = id.incrementAndGet();
        anuncio.setId(tem);
        anuncioHashMap.put(tem, anuncio);
        return anuncio;
    }


    public List<Anuncio> getAllSortedByPrecio() {
        return anuncioHashMap.values().stream()
                .sorted(Comparator.comparingDouble(Anuncio::getPrecio))
                .collect(Collectors.toList());
    }
    public Anuncio getAnuncioById(Long id) {
        return anuncioHashMap.get(id);
    }

    public void deleteAnuncio(Long id) {
        anuncioHashMap.remove(id);
    }

    public void updateAnuncio(Long id, Anuncio updatedAnuncio) {
        Anuncio existingAnuncio = getAnuncioById(id);
        if (existingAnuncio != null) {
            existingAnuncio.setNombre(updatedAnuncio.getNombre());
            existingAnuncio.setDireccion(updatedAnuncio.getDireccion());
            existingAnuncio.setLocalidad(updatedAnuncio.getLocalidad());
            existingAnuncio.setM2(updatedAnuncio.getM2());
            existingAnuncio.setPrecio(updatedAnuncio.getPrecio());
            existingAnuncio.setComentario(updatedAnuncio.getComentario());
            existingAnuncio.setValoracion(updatedAnuncio.getValoracion());
        }
    }



}
