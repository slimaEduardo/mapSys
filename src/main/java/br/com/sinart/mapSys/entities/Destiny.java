package br.com.sinart.mapSys.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="destino")
public class Destiny implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_destino")
    private Integer id;
    @Column(name="nome_destino")
    private String name;
    @Column(name="distancia_destino")
    private Integer distance;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_tipo_linha_destino", referencedColumnName = "id_linha")
     private LineCategory category;


    public Destiny() {
    }

    public Destiny(String name, Integer distance, LineCategory category) {
        this.name = name;
        this.distance = distance;
        this.category = category;
    }

    public Integer getId() {
        return id;
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

   public LineCategory getCategory() {
        return category;
    }

    public void setCategory(LineCategory category) {
      this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destiny)) return false;
        Destiny destiny = (Destiny) o;
        return Objects.equals(getId(), destiny.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
