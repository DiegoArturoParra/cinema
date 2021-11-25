package com.edu.cundi.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.cundi.cinema.entity.Editorial;


@Repository
public interface IEditorialRepo extends JpaRepository<Editorial, Integer> {

}

