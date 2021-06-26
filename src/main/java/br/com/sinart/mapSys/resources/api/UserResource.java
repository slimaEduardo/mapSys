package br.com.sinart.mapSys.resources.api;

import br.com.sinart.mapSys.dto.request.UserCreateRequest;
import br.com.sinart.mapSys.dto.request.UserUpdateRequest;
import br.com.sinart.mapSys.dto.response.UserResponse;
import br.com.sinart.mapSys.mapper.UserMapper;
import br.com.sinart.mapSys.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.sinart.mapSys.mapper.UserMapper.getUserMapper;

@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "User Resource")
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    private final UserMapper userMapper = getUserMapper();

    @ApiOperation("Find all users")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(userMapper.toResponseList(service.findAll()));
    }

    @ApiOperation("Find an user by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(userMapper.toResponse(service.findById(id)));
    }

    @ApiOperation("Create an user")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<UserResponse> insert(@Valid @RequestBody UserCreateRequest request) {
        return new ResponseEntity<>(userMapper.toResponse(service.create(request)), HttpStatus.CREATED);
    }

    @ApiOperation("Update an user")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> putUpdate(@PathVariable Integer id, @RequestBody UserUpdateRequest request) {
        return new ResponseEntity<>(userMapper.toResponse(service.update(id, request)), HttpStatus.OK);
    }

    @ApiOperation("Update an user")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserResponse> patchUpdate(@PathVariable Integer id, @RequestBody UserUpdateRequest request) {
        return new ResponseEntity<>(userMapper.toResponse(service.update(id, request)), HttpStatus.OK);
    }

    @ApiOperation("Delete an user")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
