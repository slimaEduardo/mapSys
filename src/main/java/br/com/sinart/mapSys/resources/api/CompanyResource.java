package br.com.sinart.mapSys.resources.api;


import br.com.sinart.mapSys.dto.CompanyDTO;
import br.com.sinart.mapSys.entities.Company;
import br.com.sinart.mapSys.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/companies")
public class CompanyResource {

    @Autowired
    private CompanyService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Company> findById(@PathVariable Integer id) {
        Company obj = service.findById(id);
        //CompanyDTO objDto = new CompanyDTO(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<Company> insert(@RequestBody Company obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Company> update(@PathVariable Integer id, @RequestBody Company obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        List<Company> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<Company>> findByName(@RequestParam(value = "term") String name) {
        List<Company> list = service.findByName(name);
        return ResponseEntity.ok().body(list);
    }
}
