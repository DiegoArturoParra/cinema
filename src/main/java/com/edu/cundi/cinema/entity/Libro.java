package com.edu.cundi.cinema.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "libro", schema = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres")
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @NotNull(message = "descripcion es obligatorio")
    @Column(name = "descripcion", columnDefinition = "text", nullable = false)
    private String descripcion;

    @NotNull(message = "numero de paginas obligatorio")
    @Min(value = 15, message = "Minimo de paginas 15")
    @Column(name = "numero_paginas", nullable = false)
    private Integer numeroPaginas;
    @Past
    @NotNull(message = "fecha de salida obligatorio")
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaDeSalida;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_autor", nullable = false, foreignKey = @ForeignKey(name = "FK_Autor_Libro"))
    private Autor autor;
}
