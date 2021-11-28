package com.edu.cundi.cinema.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.edu.cundi.cinema.entity.AutorEditorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorEditorialRepository extends JpaRepository<AutorEditorial, Integer> {

	// Para INSERT UPDATE Y DELETE DEBEN COLOCAR EL @Transactional Y EL @Modifying
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO libros.autor_editorial(id_autor, id_editorial, fecha_creacion) VALUES(:idAutor, :idEditorial, :fecha)", nativeQuery = true)
	void guardarNativo(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial,
			@Param("fecha") LocalDateTime fecha);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM libros.autor_editorial where id_autor = :idAutor and id_editorial = :idEditorial ", nativeQuery = true)
	void eliminarNativa(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial);
}
