package com.edu.cundi.cinema.services.interfaces;


import com.edu.cundi.cinema.entity.AutorEditorial;
import com.edu.cundi.cinema.exception.ModelNotFoundException;

public interface IAutorEditorialService  extends ICRUD<AutorEditorial, Integer> {

	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException;	
}

