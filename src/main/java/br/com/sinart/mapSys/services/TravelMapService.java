package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.dto.TravelMapDTO;
import br.com.sinart.mapSys.dto.TravelMapNewDTO;
import br.com.sinart.mapSys.entities.BusCategory;
import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.repositories.TravelMapRepository;
import br.com.sinart.mapSys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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
    @Autowired
    private BusCategoryService busCategoryService;

    public List<TravelMap> findAll(){
        return repository.findAll();
    }

    public TravelMap findById(Integer id) {
        Optional<TravelMap> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Mapa com id " + id + " não encontrado."));
    }

    public TravelMap insert(TravelMap obj) {
        return repository.save(obj);
    }

    public void delete(Integer id){

            repository.deleteById(id);

    }

    public TravelMap update(Integer id, TravelMapNewDTO obj) {
            TravelMap entity = repository.getOne(id);
            updateData(entity,obj);
            return repository.save(entity);
    }

    private void updateData(TravelMap entity, TravelMapNewDTO obj) {
        entity.setDestiny(destinyService.findById(obj.getDestinyId()));
        entity.setCompany(companyService.findById(obj.getCompanyId()));
        entity.setBusCategory(busCategoryService.findById(obj.getBusId()));
        entity.setPassQtt(obj.getPassQtt());
        entity.setBoardingDate(obj.getBoardingDate());
        entity.setBoardingTime(obj.getBoardingTime());

    }

    public List<TravelMap> search (LocalDate initialLocaldate,LocalDate finalLocalDate){
        //PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAllByBoardingDate(initialLocaldate,finalLocalDate);
    }

    public List<TravelMap> searchByDestiny(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer destiny){
        return repository.findAllByDestinyId(initialLocaldate,finalLocalDate,destiny);
    }

    public List<TravelMap> searchByCompany(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer company){
       // PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAllByCompanyId(initialLocaldate,finalLocalDate,company);
    }

    public List<TravelMap> searchByCategory(LocalDate initialLocaldate,LocalDate finalLocalDate,Integer category){
       // PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAllByBusCategoryId(initialLocaldate,finalLocalDate,category);
    }

    public TravelMap fromDTO(TravelMapNewDTO objDto) {
        return new TravelMap(objDto.getBoardingDate(),
                objDto.getBoardingTime(),
                objDto.getPassQtt(),
                companyService.findById(objDto.getCompanyId()),
                busCategoryService.findById(objDto.getBusId()),
                destinyService.findById(objDto.getDestinyId()));

    }

    public List<TravelMap> findAllInMonth(LocalDate initialLocalDate, LocalDate finalLocalDate) {
        return repository.findAllinMonth(initialLocalDate, finalLocalDate);
    }
}
