package com.edu.cundi.cinema.DTOs;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
@Data

public class PeliculaDTO extends RepresentationModel<PeliculaDTO>  {

    private String id;
    private String nombre;
    private AutorIdModel autor;
    private LocalDate fechaDeSalida;
    private String reseñaDePelicula;
}
