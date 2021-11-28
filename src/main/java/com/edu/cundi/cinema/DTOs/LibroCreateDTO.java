package com.edu.cundi.cinema.DTOs;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class LibroCreateDTO {
    private Integer id;
    @NotNull(message = "Nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres")
    private String nombre;
    @NotBlank(message = "descripción es obligatorio")
    private String descripcion;
    @Min(value = 15, message = "Minimo de paginas 15")
    @NotNull(message = "numero de paginas obligatorio")
    private Integer numeroPaginas;
    @Past
    @NotNull(message = "fecha de salida obligatorio")
    private LocalDate fechaDeSalida;
    private AutorIdDTO autor;
}
