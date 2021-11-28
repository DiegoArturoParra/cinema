package com.edu.cundi.cinema.repository;

import com.edu.cundi.cinema.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findOneByNick(String nick);

    Boolean existsByNick(String nick);
}
