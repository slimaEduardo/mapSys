package br.com.sinart.mapSys.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "destino")
public class Destiny implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destino")
    private Integer id;

    @Column(name = "nome_destino")
    private String name;

    @Column(name = "distancia_destino")
    private Integer distance;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_tipo_linha_destino", referencedColumnName = "id_linha")
    private LineCategory category;
}
