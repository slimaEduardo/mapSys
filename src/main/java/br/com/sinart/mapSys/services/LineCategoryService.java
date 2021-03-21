package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.LineCategory;
import br.com.sinart.mapSys.repositories.LineCategoryRepository;
import br.com.sinart.mapSys.resources.exceptions.ResourceNotFoundException;
import br.com.sinart.mapSys.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineCategoryService {

    @Autowired
    private LineCategoryRepository repository;

    public List<LineCategory> findAll(){
        return repository.findAll();
    }

    public LineCategory findById(Integer id) {
        Optional<LineCategory> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public LineCategory insert(LineCategory obj) {

         return repository.save(obj);
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria com mapas cadastrados.");
        }
    }

    public LineCategory update(Integer id, LineCategory obj) {

            LineCategory entity = repository.getOne(id);
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(LineCategory entity, LineCategory obj) {
        entity.setName(obj.getName());
        
    }
}
