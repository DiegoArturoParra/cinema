package com.edu.cundi.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@Column(name = "documento", nullable = false, length = 30, unique = true)
	private String documento;

	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 25)
	private String apellido;

	@Column(name = "nick", nullable = false, unique = true)
	private String nick;

	@Column(columnDefinition = "TEXT", name = "clave", nullable = false)
	private String clave;

	@ManyToOne
	@JoinColumn(name = "idRol", foreignKey = @ForeignKey(name = "FK_rol"))
	private Rol rol;
}