package com.edu.cundi.cinema.DTOs;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorDTO extends RepresentationModel<AutorDTO> {
    private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private int edad;

    public AutorDTO(Integer id) {
        this.id = id;
    }

}
