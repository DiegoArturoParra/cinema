package com.edu.cundi.cinema.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "autor_editorial", schema = "libros")
@IdClass(AutorEditorialPK.class)
public class AutorEditorial {

	@Id
	private Autor autor;

	@Id
	private Editorial editorial;
	@Column(name = "fecha_creacion", nullable = false)
	private LocalDateTime fecha;
}
