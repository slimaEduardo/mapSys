package br.com.sinart.mapSys.dto;

import br.com.sinart.mapSys.entities.BusCategory;
import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.services.BusCategoryService;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

import java.time.LocalTime;


public class TravelMapDTO implements Serializable {

    private BusCategoryService busCategoryService;
    private Integer id;

    private String companyName;
    private String busCategoryName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate boardingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "UTC-3")
    private LocalTime boardingTime;
    private String destinyName;
    private Integer passQtt;

    public TravelMapDTO() {
    }

    public TravelMapDTO(TravelMap obj) {
        this.id = obj.getId();
        this.companyName = obj.getCompany().getName();
        this.busCategoryName = obj.getBusCategory().getName();
        this.boardingDate = obj.getBoardingDate();
        this.boardingTime = obj.getBoardingTime();
        this.destinyName = obj.getDestiny().getName();
        this.passQtt = obj.getPassQtt();
       }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusCategory() {
        return busCategoryName;
    }

    public void setBusCategory(String busCategoryName) {
        this.busCategoryName = busCategoryName;
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

    public String getDestinyName() {
        return destinyName;
    }

    public void setDestinyName(String destinyName) {
        this.destinyName = destinyName;
    }

    public Integer getPassQtt() {
        return passQtt;
    }

    public void setPassQtt(Integer passQtt) {
        this.passQtt = passQtt;
    }
}
