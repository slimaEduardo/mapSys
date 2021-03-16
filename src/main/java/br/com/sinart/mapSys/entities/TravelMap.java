package br.com.sinart.mapSys.entities;

import br.com.sinart.mapSys.entities.enums.BusCategory;
import br.com.sinart.mapSys.services.CompanyService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="tb_maps")
public class TravelMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tb_id_Company")
    private Company company;
    private BusCategory busCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date boardingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date boardingTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tb_id_destiny")
    private Destiny destiny;
    private Integer passQtt;

    @Transient
    private Integer companyId;
    @Transient
    private Integer busId;
    @Transient
    private Integer destinyId;

    public TravelMap() {
    }

    public TravelMap(Date boardingDate, Date boardingTime, Integer passQtt, Integer companyId, Integer busId, Integer destinyId) {
        this.boardingDate = boardingDate;
        this.boardingTime = boardingTime;
        this.passQtt = passQtt;
        this.companyId = companyId;
        this.busId = busId;
        this.destinyId = destinyId;

    }



    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BusCategory getBusCategory() {
        return busCategory;
    }

    public void setBusCategory(BusCategory busCategory) {
        this.busCategory = busCategory;
    }

    public Date getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(Date boardingTime) {
        this.boardingTime = boardingTime;
    }

    public Destiny getDestiny() {
        return destiny;
    }

    public void setDestiny(Destiny destiny) {
        this.destiny = destiny;
    }

    public Integer getPassQtt() {
        return passQtt;
    }

    public void setPassQtt(Integer passQtt) {
        this.passQtt = passQtt;
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

    public Integer getId() {
        return id;
    }


    public Date getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(Date boardingDate) {
        this.boardingDate = boardingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelMap)) return false;
        TravelMap travelMap = (TravelMap) o;
        return Objects.equals(id, travelMap.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
