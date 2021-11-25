package com.edu.cundi.cinema.services.implement;

import javax.transaction.Transactional;

import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.AutorEditorial;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IAutorEditorialRepo;
import com.edu.cundi.cinema.services.interfaces.IAutorEditorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class AutorEditorialServiceImpl implements IAutorEditorialService{

	@Autowired
	private IAutorEditorialRepo repo;

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
	public RespuestaDTO create(AutorEditorial entidad) throws ConflictException, ModelNotFoundException {
		
		return null;
	}

	@Override
	public RespuestaDTO edit(AutorEditorial entidad) throws ConflictException, ModelNotFoundException {
		
		return null;
	}

	@Override
	public RespuestaDTO delete(Integer Id) throws ModelNotFoundException {
		
		return null;
	}

	@Override
	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
		
		
	}

	
	@Transactional
	@Override
	public void asociarAutorEditoial() {
		this.repo.guardarNativo(1, 1, LocalDate.now());
		this.repo.guardarNativo(2, 1, LocalDate.now());
		this.repo.guardarNativo(3, 1, LocalDate.now());
		this.repo.guardarNativo(6, 1, LocalDate.now());
	}
	

}
