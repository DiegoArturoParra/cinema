package com.edu.cundi.cinema.services.interfaces;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Rol;
import com.edu.cundi.cinema.entity.Usuario;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;

public interface IUsuarioService extends ICRUD<Usuario, Integer> {
    public RespuestaDTO createRol(Rol objeto) throws ConflictException;

    public void existToken(String token) throws ModelNotFoundException;

}
