package br.com.sinart.mapSys.dto;

import br.com.sinart.mapSys.entities.Destiny;


import java.io.Serializable;

public class DestinyDTO implements Serializable {

    private Integer id;
    private String name;
    private Integer distance;
    private String typeLine;

    public DestinyDTO() {
    }

    public DestinyDTO(Destiny obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.distance = obj.getDistance();
        this.typeLine = obj.getCategory().getName();
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String name) {
        this.typeLine = name;
    }
}
