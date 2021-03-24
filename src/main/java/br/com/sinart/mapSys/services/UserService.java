package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.dto.UserNewDTO;
import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.entities.enums.UserProfile;
import br.com.sinart.mapSys.repositories.UserRepository;
import br.com.sinart.mapSys.services.exceptions.DataIntegrityException;
import br.com.sinart.mapSys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + id + " não encontrado."));
    }

    public User insert(User obj) {

         return repository.save(obj);
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir um usuário com mapas cadstrados.");
        };
    }

    public User update(Integer id, UserNewDTO obj) {
            User entity = repository.getById(id);
            updateData(entity,obj);
            return repository.save(entity);

    }

    private void updateData(User entity, UserNewDTO obj) {
        entity.setName(obj.getName());
        entity.setUserProfile(UserProfile.toEnum(obj.getProfileId()));
        entity.setPassword(obj.getPassword());

    }

    public User fromDTO(UserNewDTO objDto) {
        User usr = new User(objDto.getName(),
                            objDto.getUserName(),
                            objDto.getPassword(),
                            UserProfile.toEnum(objDto.getProfileId()));
        return usr;
    }

    public User findByUserName(String userName) {
        User obj = repository.findByUserName(userName);
        return obj;
    }
}
