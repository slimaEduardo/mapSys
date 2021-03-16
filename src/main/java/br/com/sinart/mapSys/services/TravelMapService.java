package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.entities.enums.BusCategory;
import br.com.sinart.mapSys.repositories.CompanyRepository;
import br.com.sinart.mapSys.repositories.DestinyRepository;
import br.com.sinart.mapSys.repositories.TravelMapRepository;
import br.com.sinart.mapSys.resources.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelMapService {

    @Autowired
    private TravelMapRepository repository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DestinyService destinyService;

    public List<TravelMap> findAll(){
        return repository.findAll();
    }

    public TravelMap findById(Integer id) {
        Optional<TravelMap> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public TravelMap insert(TravelMap obj) {
        obj.setCompany(companyService.findById(obj.getCompanyId()));
        obj.setBusCategory(BusCategory.toEnum(obj.getBusId()));
        obj.setDestiny(destinyService.findById(obj.getDestinyId()));
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
