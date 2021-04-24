package br.com.sinart.mapSys.dto;

import java.io.Serializable;

public class DestinationDTO implements Serializable {

    private Integer passg;
    private Integer qtt;

    public DestinationDTO(Integer passg, Integer qtt) {
        this.passg = passg;
        this.qtt = qtt;
    }

    public Integer getPassg() {
        return passg;
    }

    public void setPassg(Integer passg) {
        this.passg = passg;
    }

    public Integer getQtt() {
        return qtt;
    }

    public void setQtt(Integer qtt) {
        this.qtt = qtt;
    }
}
