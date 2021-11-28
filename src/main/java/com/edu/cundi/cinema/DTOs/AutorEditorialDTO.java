package com.edu.cundi.cinema.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorEditorialDTO {
    @NotNull(message = "el id del autor no puede estar vacio.")
    private Integer idAutor;
    @NotNull(message = "el id de la editorial no puede estar vacio.")
    private Integer idEditorial;
    LocalDate hoy = LocalDate.now();
    LocalTime ahora = LocalTime.now();
    private LocalDateTime fecha = LocalDateTime.of(hoy, ahora);
}
