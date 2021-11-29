package com.edu.cundi.cinema.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "autor", schema = "libros")
public class Autor {
    @Id
    @ApiModelProperty(notes = "Unique identificador del autor.", example = "1", position = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name = "nombre", nullable = false)
    @ApiModelProperty(notes = "Nombre del autor", required = true)
    @Size(min = 4, max = 50)
    private String nombre;
    @NotBlank
    @Column(name = "apellido", nullable = false)
    @ApiModelProperty(notes = "Apellido del autor", required = true)
    @Size(min = 4, max = 50)
    private String apellido;
    @Column(name = "cedula", nullable = false, unique = true)
    @ApiModelProperty(notes = "cédula del autor", required = true)
    @Size(min = 8, max = 12)
    private String cedula;
    @Column(name = "correo", nullable = false, unique = true)
    @ApiModelProperty(notes = "correo del autor", required = true)
    @Email(message = "Email incorrecto")
    private String correo;
    @Min(value = 18, message = "Minimo de años 18")
    @Max(value = 90, message = "Maximo de años 90")
    @Column(name = "edad", nullable = false)
    @ApiModelProperty(notes = "Edad del autor", required = true)
    private int edad;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "autor", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Libro> libro;

    public Autor(String nombre, String apellido, int edad, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.cedula = cedula;
    }
}
