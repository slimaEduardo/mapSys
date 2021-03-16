package br.com.sinart.mapSys.entities;

import br.com.sinart.mapSys.entities.enums.LineCategory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_destinies")
public class Destiny implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double distance;
    private LineCategory category;
    @Transient
    private Integer categoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destiny")
   private Set<TravelMap> mapDestiny = new HashSet<>();

    public Destiny() {
    }

    public Destiny(String name, Double distance, Integer categoryId) {
        this.name = name;
        this.distance = distance;
        this.categoryId = categoryId;
        this.category = LineCategory.toEnum(this.categoryId);
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public LineCategory getCategory() {
        return category;
    }

    public void setCategory(LineCategory category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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
