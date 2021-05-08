package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.dto.DestinyNewDTO;
import br.com.sinart.mapSys.entities.Destiny;
import br.com.sinart.mapSys.repositories.DestinyRepository;
import br.com.sinart.mapSys.services.exceptions.DataIntegrityException;
import br.com.sinart.mapSys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinyService {

    @Autowired
    private DestinyRepository repository;
    @Autowired
    private LineCategoryService lineCategoryService;

    public List<Destiny> findAll() {
        return repository.findAll();
    }

    public Destiny findById(Integer id) {
        Optional<Destiny> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrada: " + id));
    }

    public List<Destiny> findByName(String name) {
        List<Destiny> obj = repository.findAllByName(name);
        return obj;
    }

    public Destiny insert(Destiny obj) {

        return repository.save(obj);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um destino com mapas cadastrados.");
        }
    }

    public Destiny update(Integer id, DestinyNewDTO obj) {
        Destiny entity = repository.getOne(id);
        updateData(entity, obj);
        return repository.save(entity);

    }

    private void updateData(Destiny entity, DestinyNewDTO obj) {
        entity.setName(obj.getName());
        entity.setCategory(lineCategoryService.findById(obj.getCategoryId()));
        entity.setDistance(obj.getDistance());
    }

    public Destiny fromDTO(DestinyNewDTO obj) {

        return Destiny.builder()
                .name(obj.getName())
                .distance(obj.getDistance())
                .category(lineCategoryService.findById(obj.getCategoryId()))
                .build();
    }
}
