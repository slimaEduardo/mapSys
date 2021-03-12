package br.com.sinart.mapSys.entities;

import br.com.sinart.mapSys.entities.enums.LineCategory;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Destiny implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double distance;
    private LineCategory category;
    @Transient
    private Integer categoryId;

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
