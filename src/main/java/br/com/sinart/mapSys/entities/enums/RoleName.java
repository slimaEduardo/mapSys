package br.com.sinart.mapSys.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleName {

    ADMIN(0, "ROLE_ADMIN"),
    USER(1, "ROLE_USER");

    private Integer code;
    private String description;

    RoleName(String description) {
        this.description = description;
    }
}
