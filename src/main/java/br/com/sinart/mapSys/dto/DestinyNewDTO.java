package br.com.sinart.mapSys.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class DestinyNewDTO implements Serializable {

    @NotEmpty(message = "Campo Obrigatório.")
    private String name;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer distance;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer categoryId;
    private Boolean isActive;

    public DestinyNewDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        isActive = isActive;
    }
}
