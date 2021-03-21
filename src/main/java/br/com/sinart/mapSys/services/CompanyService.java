package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.Company;
import br.com.sinart.mapSys.repositories.CompanyRepository;
import br.com.sinart.mapSys.resources.exceptions.ResourceNotFoundException;
import br.com.sinart.mapSys.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> findAll(){
        return repository.findAll();
    }

    public Company findById(Integer id) {
        Optional<Company> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Company insert(Company obj) {

         return repository.save(obj);
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma empresa com mapas cadstrados.");
        };

    }

    public Company update(Integer id, Company obj) {

            Company entity = repository.getOne(id);
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(Company entity, Company obj) {
        entity.setName(obj.getName());
        
    }
}
