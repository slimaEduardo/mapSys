package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.Destiny;
import br.com.sinart.mapSys.repositories.DestinyRepository;
import br.com.sinart.mapSys.resources.exceptions.ResourceNotFoundException;
import br.com.sinart.mapSys.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinyService {

    @Autowired
    private DestinyRepository repository;
    @Autowired
    private LineCategoryService lineCategoryService;

    public List<Destiny> findAll(){
        return repository.findAll();
    }

    public Destiny findById(Integer id) {
        Optional<Destiny> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Destiny insert(Destiny obj) {
        obj.setCategory(lineCategoryService.findById(obj.getCategoryId()));
         return repository.save(obj);
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
         throw new DataIntegrityException("Não é possível excluir um destino com mapas cadastrados.");
        }
    }

    public Destiny update(Integer id, Destiny obj) {

            Destiny entity = repository.getOne(id);
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(Destiny entity, Destiny obj) {
        entity.setName(obj.getName());
        entity.setCategory(lineCategoryService.findById(obj.getCategoryId()));
        entity.setDistance(obj.getDistance());
    }
}
