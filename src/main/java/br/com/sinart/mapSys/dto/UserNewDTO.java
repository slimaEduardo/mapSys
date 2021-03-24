package br.com.sinart.mapSys.dto;

import br.com.sinart.mapSys.resources.utils.UserInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@UserInsert
public class UserNewDTO implements Serializable {

    @NotEmpty(message = "Campo Obrigatório.")
    @Length(min = 3, max = 40, message = "O nome deve ter entre três e quarenta caracteres.")
    private String name;
    @NotEmpty(message = "Campo Obrigatório.")
    private String userName;
    @NotEmpty(message = "Campo Obrigatório.")
    private String password;
    @NotNull(message = "Campo Obrigatório.")
    @Min(value = 1, message = "O valor tem quer ser 1 ou 2")
    @Max(value = 2, message = "O valor tem quer ser 1 ou 2")
    private Integer profileId;

    public UserNewDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

}
