package com.edu.cundi.cinema.entity;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.edu.cundi.cinema.DTOs.AutorIdDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "peliculas")
public class Pelicula {
    @Id
    private String id;
    @NotBlank
    @Size(min = 5, max = 50)
    private String nombre;
    @NotNull
    @Valid
    private AutorIdDTO autor;
    @Past
    private LocalDate fechaDeSalida;
    @NotBlank
    @Size(min = 10, max = 500)
    private String reseñaDePelicula;

    public Pelicula(String nombre, AutorIdDTO autor, LocalDate fechaDeSalida, String reseñaDePelicula) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechaDeSalida = fechaDeSalida;
        this.reseñaDePelicula = reseñaDePelicula;
    }
}
