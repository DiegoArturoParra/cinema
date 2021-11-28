package com.edu.cundi.cinema.services.implement;

import java.util.Optional;

import com.edu.cundi.cinema.DTOs.AutorEditorialDTO;
import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Editorial;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IAutorEditorialRepository;
import com.edu.cundi.cinema.repository.IEditorialRepository;
import com.edu.cundi.cinema.services.interfaces.IEditorialService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public class EditorialService implements IEditorialService {

    private RespuestaDTO respuesta = new RespuestaDTO();
    @Autowired
    private AutorService _autorService;
    @Autowired
    private IAutorEditorialRepository _autorEditorialRepository;

    @Autowired
    private IEditorialRepository _editorialRepository;

    private ModelMapper _mapper = new ModelMapper();

    @Override
    public RespuestaDTO getAll() throws ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PaginarDTO getPaginado(int page, int pageSize) throws ModelNotFoundException {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Editorial> paginado = _editorialRepository.findAll(pageable);

        PaginarDTO paginar = new PaginarDTO(pageable.getPageNumber(), paginado.getTotalElements(),
                pageable.getPageSize(), paginado.getTotalPages(), paginado.getNumberOfElements(),
                paginado.getContent());
        return paginar;
    }

    public Editorial existEditorialById(Integer Id) throws ModelNotFoundException {
        Optional<Editorial> editorial = _editorialRepository.findById(Id);
        if (!editorial.isPresent()) {
            throw new ModelNotFoundException("Esta editorial no existe.");
        }
        return editorial.get();
    }

    @Override
    public RespuestaDTO getById(Integer Id) throws ModelNotFoundException {
        respuesta.setData(existEditorialById(Id));
        respuesta.setMensaje("encontrado");
        return respuesta;
    }

    @Override
    public RespuestaDTO create(Editorial entidad) throws ConflictException, ModelNotFoundException {
        _editorialRepository.save(entidad);
        respuesta.setMensaje("creado");
        return respuesta;
    }

    @Override
    public RespuestaDTO edit(Editorial entidad) throws ConflictException, ModelNotFoundException {
        Editorial editorial = existEditorialById(entidad.getId());
        editorial.setNombre(entidad.getNombre());
        editorial.setNit(entidad.getNit());
        _editorialRepository.save(editorial);
        respuesta.setMensaje("editado");
        return respuesta;
    }

    @Override
    public RespuestaDTO delete(Integer Id) throws ModelNotFoundException {
        existeEditorial(Id);
        _editorialRepository.deleteById(Id);
        respuesta.setMensaje("se ha eliminado.");
        return respuesta;
    }

    private void existeEditorial(Integer id) throws ModelNotFoundException {
        boolean existe = _editorialRepository.existeEditorial(id);
        if (existe) {
            throw new ModelNotFoundException("Esta editorial no existe.");
        }
    }

    @Override
    public PaginarDTO getPaginarAutoresByEditorial(int page, int pageSize) throws ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO createEditorialWithAutor(AutorEditorialDTO objeto)
            throws ConflictException, ModelNotFoundException {
        _autorService.ExisteAutor(objeto.getIdAutor());
        existEditorialById(objeto.getIdEditorial());
        _autorEditorialRepository.guardarNativo(objeto.getIdAutor(), objeto.getIdEditorial(), objeto.getFecha());
        respuesta.setMensaje("creado");
        return respuesta;
    }

}
