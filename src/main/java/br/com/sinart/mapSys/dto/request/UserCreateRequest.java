package br.com.sinart.mapSys.dto.request;

import br.com.sinart.mapSys.entities.enums.RoleName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserCreateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Campo Obrigatório.")
    @Length(min = 3, max = 40, message = "O nome deve ter entre três e quarenta caracteres.")
    private String name;

    @NotEmpty(message = "Campo Obrigatório.")
    private String userName;

    @NotEmpty(message = "Campo Obrigatório.")
    private String password;

    @NotNull(message = "Campo Obrigatório.")
    private RoleName role;
}
