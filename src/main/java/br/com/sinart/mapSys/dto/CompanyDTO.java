package br.com.sinart.mapSys.dto;

import br.com.sinart.mapSys.entities.Company;

import java.io.Serializable;

public class CompanyDTO implements Serializable {

    private Integer id;
    private String name;
    private Boolean isActive;
    public CompanyDTO() {
    }

    public CompanyDTO(Company obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.isActive = obj.getIsActive();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
