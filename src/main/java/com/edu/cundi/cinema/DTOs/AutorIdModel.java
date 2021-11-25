package com.edu.cundi.cinema.DTOs;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorIdModel extends RepresentationModel<AutorIdModel> {
    @NotBlank(message = "el id no puede estar vacio.")
    private Integer id;
    
}