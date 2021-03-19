package br.com.sinart.mapSys.resources;


import br.com.sinart.mapSys.dto.DestinyDTO;
import br.com.sinart.mapSys.dto.TravelMapDTO;
import br.com.sinart.mapSys.entities.Destiny;
import br.com.sinart.mapSys.services.DestinyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/destinies")
public class DestinyResource {

    @Autowired
    private DestinyService service;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<Destiny> list = service.findAll();
        List<DestinyDTO> listDto = list.stream().map(obj -> new DestinyDTO(obj)).collect((Collectors.toList()));
        return ResponseEntity.ok().body(listDto);
    }

    
    @GetMapping(value = "/{id}")
    public ResponseEntity<DestinyDTO> findById(@PathVariable Integer id) {
        Destiny obj = service.findById(id);
        DestinyDTO objDto = new DestinyDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @PostMapping
    public ResponseEntity<Destiny> insert( @RequestBody Destiny obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Destiny> update(@PathVariable Integer id,@RequestBody Destiny obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}