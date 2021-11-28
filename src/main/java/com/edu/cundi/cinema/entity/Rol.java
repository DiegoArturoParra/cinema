package com.edu.cundi.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "rol", schema = "usuarios")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;
    @NotBlank(message = "nombre es obligatorio")
    @Column(name = "nombre", columnDefinition = "text")
    private String nombre;
    @NotBlank(message = "descripción es obligatorio")
    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;
}
