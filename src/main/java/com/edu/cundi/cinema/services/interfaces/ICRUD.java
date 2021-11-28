package com.edu.cundi.cinema.services.interfaces;

import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;

public interface ICRUD<T, ID> {
    public RespuestaDTO getAll() throws ModelNotFoundException;

    public PaginarDTO getPaginado(int page, int pageSize) throws ModelNotFoundException;

    public RespuestaDTO getById(ID Id) throws ModelNotFoundException;

    public RespuestaDTO create(T entidad) throws ConflictException, ModelNotFoundException;

    public RespuestaDTO edit(T entidad) throws ConflictException, ModelNotFoundException;

    public RespuestaDTO delete(ID Id) throws ModelNotFoundException;
}
