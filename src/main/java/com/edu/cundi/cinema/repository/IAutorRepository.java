package com.edu.cundi.cinema.repository;

import java.util.Optional;

import com.edu.cundi.cinema.entity.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Integer> {

    @Query(value = "SELECT count(id) FROM libros.autor au where au.id != ? and au.correo =?", nativeQuery = true)
    public long buscarByCorreo(Integer id, String cedula);

    @Query(value = "SELECT count(u.cedula) FROM Autor u WHERE u.cedula = ?1")
    public long buscarByCedula(String cedula);

    Optional<Autor> findByCedula(String cedula);

    public Boolean existsByCedula(String cedula);

    public Boolean existsByCorreo(String correo);

    @Query(value = "SELECT CASE  WHEN count(aut)> 0 THEN true ELSE false END FROM libros.autor aut where aut.id = ?", nativeQuery = true)
    public Boolean existeAutor(Integer id);

}
