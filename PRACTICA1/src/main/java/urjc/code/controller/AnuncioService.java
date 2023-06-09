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

    public List<Anuncio> getAllSortedByName() {
        return anuncioHashMap.values().stream()
                .sorted(Comparator.comparing(Anuncio::getNombre))
                .collect(Collectors.toList());
    }

    public List<Anuncio> getAllSortedByPrecio() {
        return anuncioHashMap.values().stream()
                .sorted(Comparator.comparingDouble(Anuncio::getPrecio))
                .collect(Collectors.toList());
    }

}
