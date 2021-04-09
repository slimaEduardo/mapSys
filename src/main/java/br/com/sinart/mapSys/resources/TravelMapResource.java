package br.com.sinart.mapSys.resources;


import br.com.sinart.mapSys.dto.TravelMapDTO;
import br.com.sinart.mapSys.dto.TravelMapNewDTO;
import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.resources.utils.URL;
import br.com.sinart.mapSys.services.TravelMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/maps")
public class TravelMapResource {

    @Autowired
    private TravelMapService service;

   private DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("ddMMyyyy");



    @GetMapping(value = "/{id}")
    public ResponseEntity<TravelMap> findById(@PathVariable Integer id) {
        TravelMap obj = service.findById(id);
        //TravelMapDTO objDto = new TravelMapDTO(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<TravelMapDTO> insert( @RequestBody TravelMapNewDTO objNewDto) {
        TravelMap obj = service.fromDTO(objNewDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        TravelMapDTO objDto = new TravelMapDTO(obj);
        return ResponseEntity.created(uri).body(objDto);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TravelMapDTO> update(@PathVariable Integer id,@RequestBody TravelMapNewDTO obj){
        TravelMap objUpdated = service.update(id, obj);
        TravelMapDTO objDto = new TravelMapDTO(objUpdated) ;//Aqui o mapa já foi atualizado. foi adicionado apenas para apresentar ao usuário
        return ResponseEntity.ok().body(objDto);
    }

   // @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<?>> findInMonth()
//          @RequestParam(value="initialLocalDate",  defaultValue = "") LocalDate initialLocalDate,
//          @RequestParam(value="finalLocalDate",  defaultValue = "") LocalDate finalLocalDate,
//           @RequestParam(value="page", defaultValue = "0") Integer page,
//           @RequestParam(value="linesPerPage", defaultValue = "50") Integer linesPerPage,
//          @RequestParam(value="orderBy", defaultValue = "boardingDate")String orderBy,
//          @RequestParam(value="direction", defaultValue = "DESC")String direction)
        {
       LocalDate initialLocalDate = YearMonth.now().atDay(1);
       LocalDate finalLocalDate = LocalDate.now();
    List<TravelMap> list = service.findAllInMonth(initialLocalDate,finalLocalDate);
    //List<TravelMapDTO> listDto = list.stream().map(obj -> new TravelMapDTO(obj)).collect(Collectors.toList());
    //Page<TravelMapDTO> pageDTO = pageP.map(obj -> new TravelMapDTO(obj));
    return ResponseEntity.ok().body(list);
  }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<Page<?>> findByInterval(
            @RequestParam(value="start",  defaultValue = "") String start,
            @RequestParam(value="end",  defaultValue = "") String end,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "50") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "boardingDate")String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC")String direction) {

       LocalDate initialLocalDate = LocalDate.parse(start,formatadorBarra);
        LocalDate finalLocalDate = LocalDate.parse(end,formatadorBarra);
        Page<TravelMap> pageP = service.search(initialLocalDate,finalLocalDate, page,linesPerPage, orderBy, direction);
        Page<TravelMapDTO> pageDTO = pageP.map(obj -> new TravelMapDTO(obj));
        return ResponseEntity.ok().body(pageDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search1")
    public ResponseEntity<Page<?>> findByDestiny(
            @RequestParam(value="term",  defaultValue = "") Integer destiny,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "50") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "boardingDate")String orderBy,
            @RequestParam(value="direction", defaultValue = "DESC")String direction) {
        Page<TravelMap> pageP = service.searchByDestiny(destiny, page,linesPerPage, orderBy, direction);
        Page<TravelMapDTO> pageDTO = pageP.map(obj -> new TravelMapDTO(obj));
        return ResponseEntity.ok().body(pageDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search2")
    public ResponseEntity<Page<?>> findByCompany(
            @RequestParam(value="term",  defaultValue = "") Integer company,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "50") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "boardingDate")String orderBy,
            @RequestParam(value="direction", defaultValue = "DESC")String direction) {
        Page<TravelMap> pageP = service.searchByCompany(company, page,linesPerPage, orderBy, direction);
        Page<TravelMapDTO> pageDTO = pageP.map(obj -> new TravelMapDTO(obj));
        return ResponseEntity.ok().body(pageDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search3")
    public ResponseEntity<Page<?>> findByCategory(
            @RequestParam(value="term",  defaultValue = "") Integer category,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "50") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "boardingDate")String orderBy,
            @RequestParam(value="direction", defaultValue = "DESC")String direction) {
        Page<TravelMap> pageP = service.searchByCategory(category, page,linesPerPage, orderBy, direction);
        Page<TravelMapDTO> pageDTO = pageP.map(obj -> new TravelMapDTO(obj));
        return ResponseEntity.ok().body(pageDTO);
    }

}
