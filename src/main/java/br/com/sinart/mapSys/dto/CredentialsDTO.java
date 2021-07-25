package br.com.sinart.mapSys.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {

    private String userName;
    private String password;
    private Boolean isActive;

    public CredentialsDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
