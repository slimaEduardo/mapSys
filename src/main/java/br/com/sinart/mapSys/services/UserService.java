package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.dto.UserNewDTO;
import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.entities.enums.UserProfile;
import br.com.sinart.mapSys.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public Optional<User> findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj;
    }

    public User insert(User obj) {
         obj.setUserProfile(UserProfile.toEnum(obj.getProfileId()));
         return repository.save(obj);
    }

    public void delete(Integer id){

            repository.deleteById(id);

    }

    public User update(Integer id, User obj) {
        System.out.println("Aqui2: " + id +" "+ obj.getName());
            User entity = repository.getOne(id);
            System.out.println("Aqui 4: "+ entity.getName());
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(User entity, User obj) {
        System.out.println("Aqui 3: "+ " " + entity.getName() + " " + obj.getName());
        entity.setName(obj.getName());
        //entity.setUserProfile(UserProfile.toEnum(obj.getProfileId()));
    }

    public User fromDTO(UserNewDTO objDto) {
        User usr = new User(objDto.getName(), objDto.getUserName(),objDto.getPassword(), objDto.getProfileId());
        return usr;
    }
}
