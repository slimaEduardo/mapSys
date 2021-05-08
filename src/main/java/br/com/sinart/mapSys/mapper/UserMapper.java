package br.com.sinart.mapSys.mapper;

import br.com.sinart.mapSys.dto.request.UserUpdateRequest;
import br.com.sinart.mapSys.dto.response.UserResponse;
import br.com.sinart.mapSys.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    static UserMapper getUserMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserRequestFromRequest(UserUpdateRequest request, @MappingTarget User user);

}
