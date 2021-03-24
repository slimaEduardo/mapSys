package br.com.sinart.mapSys.resources;

import br.com.sinart.mapSys.dto.UserNewDTO;
import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.entities.enums.UserProfile;
import br.com.sinart.mapSys.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //User u = new User("Maria Teste","Maria","123",2);
    // u.setUserProfile(UserProfile.toEnum(u.getProfileId()));//usar esse método no insert do usuário

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<User> findByUserName(@RequestParam(value="user", defaultValue = "") String userName) {
        User obj = service.findByUserName(userName);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@Valid @RequestBody UserNewDTO objDto) {
        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id,@RequestBody UserNewDTO obj){
       User objUpdated = service.update(id, obj);
        return ResponseEntity.ok().body(objUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
