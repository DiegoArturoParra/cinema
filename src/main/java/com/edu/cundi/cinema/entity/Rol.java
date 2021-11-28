package com.edu.cundi.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "rol", schema = "usuarios")
public class Rol {

    @Id
    private Integer idRol;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "descripcion", length = 30)
    private String descripcion;
}
