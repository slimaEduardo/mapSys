package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.repositories.TravelMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelMapService {

    @Autowired
    private TravelMapRepository repository;

    public List<TravelMap> findAll(){
        return repository.findAll();
    }

    public Optional<TravelMap> findById(Integer id) {
        Optional<TravelMap> obj = repository.findById(id);
        return obj;
    }

    public TravelMap insert(TravelMap obj) {

         return repository.save(obj);
    }

    public void delete(Integer id){

            repository.deleteById(id);

    }

    public TravelMap update(Integer id, TravelMap obj) {

            TravelMap entity = repository.getOne(id);
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(TravelMap entity, TravelMap obj) {
        entity.setDestiny(obj.getDestiny());

    }
}
