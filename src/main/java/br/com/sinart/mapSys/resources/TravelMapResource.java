package br.com.sinart.mapSys.resources;


import br.com.sinart.mapSys.dto.TravelMapDTO;
import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.services.TravelMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/maps")
public class TravelMapResource {

    @Autowired
    private TravelMapService service;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<TravelMap> list = service.findAll();
        List<TravelMapDTO> listDto = list.stream().map(obj -> new TravelMapDTO(obj)).collect((Collectors.toList()));
        return ResponseEntity.ok().body(listDto);
    }

    
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


}
