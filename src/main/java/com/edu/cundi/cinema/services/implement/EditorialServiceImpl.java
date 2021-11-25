package com.edu.cundi.cinema.services.implement;


import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Editorial;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IAutorRepository;
import com.edu.cundi.cinema.services.interfaces.IEditorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class EditorialServiceImpl implements IEditorialService {

	@Autowired
	private IAutorRepository repo;

	@Override
	public RespuestaDTO getAll() throws ModelNotFoundException {
		
		return null;
	}

	@Override
	public PaginarDTO getPaginado(int page, int pageSize) throws ModelNotFoundException {
		
		return null;
	}

	@Override
	public RespuestaDTO getById(Integer Id) throws ModelNotFoundException {
		
		return null;
	}

	@Override
	public RespuestaDTO create(Editorial entidad) throws ConflictException, ModelNotFoundException {
		
		return null;
	}

	@Override
	public RespuestaDTO edit(Editorial entidad) throws ConflictException, ModelNotFoundException {
		
		return null;
	}

	@Override
	public RespuestaDTO delete(Integer Id) throws ModelNotFoundException {
		
		return null;
	}
	

}
