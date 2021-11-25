package com.edu.cundi.cinema.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.cundi.cinema.entity.AutorEditorial;

@Repository
public interface IAutorEditorialRepo  extends JpaRepository<AutorEditorial, Integer> {

	//Para INSERT UPDATE Y DELETE DEBEN COLOCAR EL @Transactional Y EL @Modifying
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO autor_editorial(id_autor, id_editorial, fecha) VALUES(:idAutor, :idEditorial, :fecha)"
				,nativeQuery = true)
	void guardarNativo(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial, @Param("fecha") LocalDate fecha);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM autor_editorial where id_autor = :idAutor and id_editorial = :idEditorial ",  nativeQuery = true)
	void eliminarNativa(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial);
}

