package es.dws.AnuncioService;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AnuncioService {

    private Map<Long, Anuncio> anuncioHashMap = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    public AnuncioService(){
        long tem = id.incrementAndGet();
        anuncioHashMap.put(tem, new Anuncio(tem, "Anuncio1", "Asunto1", "Comentario1",2));
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

}
