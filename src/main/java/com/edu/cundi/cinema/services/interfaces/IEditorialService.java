package com.edu.cundi.cinema.services.interfaces;

import com.edu.cundi.cinema.DTOs.AutorEditorialDTO;
import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Editorial;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;

public interface IEditorialService extends ICRUD<Editorial, Integer> {

    public PaginarDTO getPaginarAutoresByEditorial(int page, int pageSize, Integer editorial) throws ModelNotFoundException;

    public RespuestaDTO createEditorialWithAutor(AutorEditorialDTO objeto)
            throws ConflictException, ModelNotFoundException;

}
