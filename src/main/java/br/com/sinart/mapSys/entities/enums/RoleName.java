package br.com.sinart.mapSys.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleName {
    USER(0, "ROLE_USER"),
    ADMIN(1, "ROLE_ADMIN");

    private Integer code;
    private String description;

    RoleName(String description) {
        this.description = description;
    }
}
