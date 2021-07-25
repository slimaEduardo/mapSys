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
@Table(name = "usuario")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    private String name; // ToDO

    @Column(name = "nome_usuario")
    private String userName;

    @Column(name = "senha_usuario")
    private String password;

    // 0 = ROLE_ADMIN
    // 1 = ROLE_USER
    @Column(name = "flag_admin")
    private Integer userProfile;
    @Column(name = "flag_usuario_ativado")
    private Boolean isActive;
}
