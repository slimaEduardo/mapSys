package br.com.sinart.mapSys.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name="mapas")
public class TravelMap implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_viagem")
    private Integer id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_empresa_mapas",  referencedColumnName ="id_empresa")
    private Company company;
    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_categoria_mapas", referencedColumnName = "id_categoria")
    private BusCategory busCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="data_viagem")
    private LocalDate boardingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name="hora_viagem")
    private LocalTime boardingTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino_mapas")
    private Destiny destiny;
    @Column(name="qnt_passageiros")
    private Integer passQtt;


    //@Transient
    //private Integer companyId;
   // @Transient
    //private Integer busId;
    //@Transient
    //private Integer destinyId;

    public TravelMap() {
    }

    public TravelMap(LocalDate boardingDate, LocalTime boardingTime, Integer passQtt, Company company, BusCategory busCategory, Destiny destiny) {
        this.boardingDate = boardingDate;
        this.boardingTime = boardingTime;
        this.passQtt = passQtt;
        this.company = company;
        this.busCategory = busCategory;
        this.destiny = destiny;
       // this.companyId = companyId;
        //this.busId = busId;
        //this.destinyId = destinyId;

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

    public LocalTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalTime boardingTime) {
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

    //public Integer getCompanyId() {
   //     return companyId;
  //  }

   // public void setCompanyId(Integer companyId) {
   //     this.companyId = companyId;
  //  }

   // public Integer getBusId() {
    //    return busId;
   // }

   // public void setBusId(Integer busId) {
   //     this.busId = busId;
   // }

   // public Integer getDestinyId() {
   //     return destinyId;
  //  }

  //  public void setDestinyId(Integer destinyId) {
   //     this.destinyId = destinyId;
  //  }

    public Integer getId() {
        return id;
    }


    public LocalDate getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(LocalDate boardingDate) {
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
