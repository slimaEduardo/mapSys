package br.com.sinart.mapSys.dto.request;

import br.com.sinart.mapSys.entities.enums.RoleName;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String userName;
    private String password;
    private RoleName role;
    private Boolean isActive;
}
