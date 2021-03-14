package br.com.sinart.mapSys.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserProfile {

    ADMIN(1,"ROLE_ADMIN"),
    USER(2,"ROLE_USER");

    private Integer cod;
    private String description;

    UserProfile(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return this.cod;
    }

    public String getDescription() {
        return description;
    }


    public static UserProfile toEnum(Integer cod){
        if(cod == null){
            return  null;
        }

        for (UserProfile x : UserProfile.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Id inválido: " + cod);
    }
}
