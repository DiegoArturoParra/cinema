package com.edu.cundi.cinema.repository;

import com.edu.cundi.cinema.entity.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    Boolean existsByNombre(String nombre);
}