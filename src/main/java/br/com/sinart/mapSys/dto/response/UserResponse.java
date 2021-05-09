package br.com.sinart.mapSys.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@JsonPropertyOrder({"id", "name", "userName"})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 1)
    private Integer id;
    @ApiModelProperty(position = 2)
    private String name;
    @ApiModelProperty(position = 3)
    private String userName;
    @ApiModelProperty(position = 4)
    private String password;
    @ApiModelProperty(position = 5)
    private String userProfile;
}
