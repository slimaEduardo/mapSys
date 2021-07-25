package br.com.sinart.mapSys.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tipolinha")
public class LineCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linha")
    private Integer id;

    @Column(name = "nome_linha")
    private String name;

    @Column(name = "flag_linha_ativada")
    private Boolean isActive;
}
