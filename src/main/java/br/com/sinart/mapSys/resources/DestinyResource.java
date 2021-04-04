package br.com.sinart.mapSys.resources;


import br.com.sinart.mapSys.dto.DestinyDTO;
import br.com.sinart.mapSys.dto.DestinyNewDTO;
import br.com.sinart.mapSys.entities.Destiny;
import br.com.sinart.mapSys.services.DestinyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value="/destinies")
public class DestinyResource {

    @Autowired
    private DestinyService service;

    @GetMapping
    public ResponseEntity<Page<?>> findAll(@RequestParam(value="page", defaultValue = "0") Integer page,
                                           @RequestParam(value="linesPerPage", defaultValue = "25") Integer linesPerPage,
                                           @RequestParam(value="orderBy", defaultValue = "name")String orderBy,
                                           @RequestParam(value="direction", defaultValue = "ASC")String direction){
        Page<Destiny> destinyPage = service.findAll(page,linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(destinyPage);
    }

    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Destiny obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Integer> insert( @RequestBody DestinyNewDTO objNew) {
        Destiny obj = service.fromDTO(objNew);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj.getId());
    }

   // @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Destiny> update(@PathVariable Integer id,@RequestBody DestinyNewDTO objNew){
        Destiny obj = service.update(id, objNew);
        return ResponseEntity.ok().body(obj);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public  ResponseEntity<List<Destiny>> findByName(@RequestParam(value="therm") String name){
        List<Destiny> list = service.findByName(name);
        return ResponseEntity.ok().body(list);
    }
}
