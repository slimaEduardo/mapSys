package br.com.sinart.mapSys.resources;


import br.com.sinart.mapSys.dto.TravelMapDTO;
import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.services.TravelMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

   private DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");


  //  @GetMapping
  //  public ResponseEntity<Page> findAll(){
  //      List<TravelMap> list = service.findAll();
 //       Page<TravelMapDTO> listDto = list.stream().map(obj -> new TravelMapDTO(obj)).collect((Collectors.toList()));
 //       return ResponseEntity.ok().body(listDto);
 //   }

    
    @GetMapping(value = "/{id}")
    public ResponseEntity<TravelMapDTO> findById(@PathVariable Integer id) {
        TravelMap obj = service.findById(id);
        TravelMapDTO objDto = new TravelMapDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @PostMapping
    public ResponseEntity<TravelMapDTO> insert( @RequestBody TravelMap obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        TravelMapDTO objDto = new TravelMapDTO(obj);
        return ResponseEntity.created(uri).body(objDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TravelMap> update(@PathVariable Integer id,@RequestBody TravelMap obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<Page<?>> findPage(
          @RequestParam(value="initialLocalDate",  defaultValue = "") LocalDate initialLocalDate,
          @RequestParam(value="finalLocalDate",  defaultValue = "") LocalDate finalLocalDate,
           @RequestParam(value="page", defaultValue = "0") Integer page,
           @RequestParam(value="linesPerPage", defaultValue = "50") Integer linesPerPage) {
    initialLocalDate = YearMonth.now().atDay(1);
    finalLocalDate = LocalDate.now();
    Page<TravelMap> pageP = service.search(initialLocalDate,finalLocalDate, page,linesPerPage);
    Page<TravelMapDTO> pageDTO = pageP.map(obj -> new TravelMapDTO(obj));
    return ResponseEntity.ok().body(pageDTO);
  }


}
