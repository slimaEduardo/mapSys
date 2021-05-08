package br.com.sinart.mapSys.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "mapas")
public class TravelMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viagem")
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_empresa_mapas", referencedColumnName = "id_empresa")

    private Company company;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_categoria_mapas", referencedColumnName = "id_categoria")

    private BusCategory busCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    @Column(name = "data_viagem")
    private LocalDate boardingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")

    @Column(name = "hora_viagem")
    private LocalTime boardingTime;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_destino_mapas")
    private Destiny destiny;

    @Column(name = "qnt_passageiros")
    private Integer passQtt;
}
