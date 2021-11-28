package com.edu.cundi.cinema.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditEditorialDTO {
    @NotNull(message = "el id de la editorial no puede estar vacio.")
    private Integer id;
    @NotBlank(message = "descripción es obligatorio")
	private String nombre;
}
