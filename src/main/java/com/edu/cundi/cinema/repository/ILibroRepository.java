package com.edu.cundi.cinema.repository;

import com.edu.cundi.cinema.entity.Libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer> {

}
