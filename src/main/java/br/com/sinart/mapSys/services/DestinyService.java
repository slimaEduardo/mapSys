package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.Destiny;
import br.com.sinart.mapSys.repositories.DestinyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinyService {

    @Autowired
    private DestinyRepository repository;

    public List<Destiny> findAll(){
        return repository.findAll();
    }

    public Optional<Destiny> findById(Integer id) {
        Optional<Destiny> obj = repository.findById(id);
        return obj;
    }

    public Destiny insert(Destiny obj) {

         return repository.save(obj);
    }

    public void delete(Integer id){

            repository.deleteById(id);

    }

    public Destiny update(Integer id, Destiny obj) {

            Destiny entity = repository.getOne(id);
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(Destiny entity, Destiny obj) {
        entity.setName(obj.getName());
        
    }
}
