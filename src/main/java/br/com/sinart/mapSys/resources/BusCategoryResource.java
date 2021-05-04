package br.com.sinart.mapSys.resources;



import br.com.sinart.mapSys.entities.BusCategory;
import br.com.sinart.mapSys.services.BusCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/categories")
public class BusCategoryResource {

    @Autowired
    private BusCategoryService service;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<BusCategory> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    
    @GetMapping(value = "/{id}")
    public ResponseEntity<BusCategory> findById(@PathVariable Integer id) {
        BusCategory obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BusCategory> insert( @RequestBody BusCategory obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BusCategory> update(@PathVariable Integer id,@RequestBody BusCategory obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
