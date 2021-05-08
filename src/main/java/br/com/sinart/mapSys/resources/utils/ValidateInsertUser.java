package br.com.sinart.mapSys.resources.utils;

import br.com.sinart.mapSys.dto.UserNewDTO;
import br.com.sinart.mapSys.entities.User;
import br.com.sinart.mapSys.repositories.UserRepository;
import br.com.sinart.mapSys.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ValidateInsertUser implements ConstraintValidator<UserInsert, UserNewDTO> {

    @Autowired
    private UserRepository repo;

    @Override
    public void initialize(UserInsert ann) {
    }

    @Override
    public boolean isValid(UserNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        User aux = repo.findByUserNameIgnoreCase(objDto.getUserName());
        if (aux != null) {
            list.add(new FieldMessage("userName", "usuário já existente"));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
