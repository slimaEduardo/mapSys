package br.com.sinart.mapSys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class TravelMapNewDTO implements Serializable {

    @NotEmpty(message = "Campo Obrigatório.")
    private Integer companyId;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer busId;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer destinyId;
    @NotEmpty(message = "Campo Obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate boardingDate;
    @NotEmpty(message = "Campo Obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime boardingTime;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer passQtt;

    public TravelMapNewDTO() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getDestinyId() {
        return destinyId;
    }

    public void setDestinyId(Integer destinyId) {
        this.destinyId = destinyId;
    }

    public LocalDate getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(LocalDate boardingDate) {
        this.boardingDate = boardingDate;
    }

    public LocalTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public Integer getPassQtt() {
        return passQtt;
    }

    public void setPassQtt(Integer passQtt) {
        this.passQtt = passQtt;
    }
}
