package com.edu.cundi.cinema.DTOs;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorIdDTO {
    @NotNull(message = "el id del autor no puede estar vacio.")
    private Integer id;
}