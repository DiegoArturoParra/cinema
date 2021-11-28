package com.edu.cundi.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.cundi.cinema.entity.Editorial;

@Repository
public interface IEditorialRepository extends JpaRepository<Editorial, Integer> {
    @Query(value = "SELECT CASE  WHEN count(e)> 0 THEN true ELSE false END FROM libros.editorial e where e.id = ?", nativeQuery = true)
    public Boolean existeEditorial(Integer id);
}
