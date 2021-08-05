package br.com.sinart.mapSys.resources.api;



import br.com.sinart.mapSys.entities.LineCategory;
import br.com.sinart.mapSys.services.LineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/lines")
public class LineCategoryResource {

    @Autowired
    private LineCategoryService service;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<LineCategory> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/active")
    public ResponseEntity<List> findAllActive(){
        List<LineCategory> list = service.findAllActive();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<LineCategory> findById(@PathVariable Integer id) {
        LineCategory obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<LineCategory> insert( @RequestBody LineCategory obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<LineCategory> update(@PathVariable Integer id,@RequestBody LineCategory obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
