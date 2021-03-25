package br.com.sinart.mapSys.resources;


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


@RestController
@RequestMapping(value="/companies")
public class CompanyResource {

    @Autowired
    private CompanyService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable Integer id) {
        Company obj = service.findById(id);
        CompanyDTO objDto = new CompanyDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Company> insert( @RequestBody Company obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Company> update(@PathVariable Integer id,@RequestBody Company obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<Page<?>> findInMonth(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "25") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC")String direction) {
        Page<Company> pageP = service.findAll(page,linesPerPage, orderBy, direction);
        Page<CompanyDTO> pageDTO = pageP.map(obj -> new CompanyDTO(obj));
        return ResponseEntity.ok().body(pageDTO);
    }

}
