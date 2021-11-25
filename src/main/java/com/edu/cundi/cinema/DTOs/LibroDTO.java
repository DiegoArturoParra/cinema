package com.edu.cundi.cinema.DTOs;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LibroDTO {
    @NotNull(message = "id es obligatorio")
    private Integer id;
    @NotNull(message = "Nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres")
    private String nombre;
    @NotNull(message = "descripcion es obligatorio")
    private String descripcion;
    @Min(value = 15, message = "Minimo de paginas 15")
    @NotNull(message = "numero de paginas obligatorio")
    private Integer numeroPaginas;
    @Past
    @NotNull(message = "fecha de salida obligatorio")
    private LocalDate fechaDeSalida;
}
