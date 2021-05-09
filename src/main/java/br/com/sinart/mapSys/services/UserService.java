package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.dto.request.UserCreateRequest;
import br.com.sinart.mapSys.dto.request.UserUpdateRequest;
import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.entities.enums.RoleName;
import br.com.sinart.mapSys.mapper.UserMapper;
import br.com.sinart.mapSys.repositories.UserRepository;
import br.com.sinart.mapSys.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static br.com.sinart.mapSys.mapper.UserMapper.getUserMapper;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = getUserMapper();

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    public User create(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setUserName(request.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setUserProfile(RoleName.valueOf(request.getRole().name()).getCode());
        return userRepository.saveAndFlush(user);
    }

    public User update(Integer id, UserUpdateRequest request) {
        User user = this.findById(id);
        this.verifyPassword(request);
        userMapper.updateUserRequestFromRequest(request, user);
        this.verifyRole(request, user);
        return userRepository.saveAndFlush(user);
    }

    public void delete(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um usuário com mapas cadastrados.");
        }
        ;
    }

    private void verifyRole(UserUpdateRequest request, User user) {
        if (request.getRole() != null) {
            if (request.getRole().equals(RoleName.ADMIN)) {
                user.setUserProfile(1);
            } else if (request.getRole().equals(RoleName.USER)) {
                user.setUserProfile(0);
            }
        }
    }

    private void verifyPassword(UserUpdateRequest request) {
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            request.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        }
    }
}
