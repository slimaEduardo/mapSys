package br.com.sinart.mapSys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TravelMapNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Campo Obrigatório.")
    private Integer companyId;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer busId;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer destinyId;
    @NotEmpty(message = "Campo Obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate boardingDate;
    @NotEmpty(message = "Campo Obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime boardingTime;
    @NotEmpty(message = "Campo Obrigatório.")
    private Integer passQtt;
}
