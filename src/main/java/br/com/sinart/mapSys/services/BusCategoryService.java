package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.BusCategory;
import br.com.sinart.mapSys.repositories.BusCategoryRepository;
import br.com.sinart.mapSys.services.exceptions.DataIntegrityException;
import br.com.sinart.mapSys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusCategoryService {

    @Autowired
    private BusCategoryRepository repository;

    public List<BusCategory> findAll(){
        return repository.findAll();
    }

    public BusCategory findById(Integer id) {
        Optional<BusCategory> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria com id " + id + " não encontrada."));
    }

    public BusCategory insert(BusCategory obj) {

         return repository.save(obj);
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
           throw new DataIntegrityException("Não é possível excluir uma categoria com destinos cadastrados.");
        }

    }

    public BusCategory update(Integer id, BusCategory obj) {

            BusCategory entity = repository.getOne(id);
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(BusCategory entity, BusCategory obj) {
        entity.setName(obj.getName());
        
    }
}
