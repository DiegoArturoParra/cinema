package com.edu.cundi.cinema.services.implement;

import java.util.List;
import java.util.Optional;

import com.edu.cundi.cinema.DTOs.LibroDTO;
import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.entity.Libro;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.ILibroRepository;
import com.edu.cundi.cinema.services.interfaces.ILibroService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibroService implements ILibroService {
    private RespuestaDTO respuesta = new RespuestaDTO();
    @Autowired
    private ILibroRepository _libroRepository;
    @Autowired
    private AutorService autorService;
    private ModelMapper _mapper = new ModelMapper();

    @Override
    public RespuestaDTO getAll() throws ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PaginarDTO getPaginado(int page, int pageSize) throws ModelNotFoundException {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Libro> paginado = _libroRepository.findAll(pageable);

        List<LibroDTO> autoresDTOs = (List<LibroDTO>) _mapper.map(paginado.getContent(),
                new TypeToken<List<LibroDTO>>() {
                }.getType());

        PaginarDTO paginar = new PaginarDTO(pageable.getPageNumber(), paginado.getTotalElements(),
                pageable.getPageSize(), paginado.getTotalPages(), paginado.getNumberOfElements(), autoresDTOs);
        return paginar;
    }

    @Override
    public RespuestaDTO getById(Integer Id) throws ModelNotFoundException {
        respuesta.setData(getLibroById(Id));
        respuesta.setMensaje("encontrado");
        return respuesta;
    }

    @Override
    public RespuestaDTO create(Libro entidad) throws ConflictException, ModelNotFoundException {
        Autor autor = autorService.getAutorById(entidad.getAutor().getId());
        entidad.setAutor(autor);
        _libroRepository.save(entidad);
        respuesta.setMensaje("creado");
        return respuesta;
    }

    private Libro getLibroById(Integer Id) throws ModelNotFoundException {
        Optional<Libro> libro = _libroRepository.findById(Id);
        if (!libro.isPresent()) {
            throw new ModelNotFoundException("Esta libro no existe.");
        }
        return libro.get();
    }

    @Override
    public RespuestaDTO edit(Libro entidad) throws ConflictException, ModelNotFoundException {
        autorService.getAutorById(entidad.getAutor().getId());
        Libro libro = getLibroById(entidad.getId());
        libro.setDescripcion(entidad.getDescripcion());
        libro.setFechaDeSalida(entidad.getFechaDeSalida());
        libro.setNombre(entidad.getNombre());
        libro.setNumeroPaginas(entidad.getNumeroPaginas());
        _libroRepository.save(libro);
        respuesta.setMensaje("editado");
        return respuesta;
    }

    @Override
    public RespuestaDTO delete(Integer Id) throws ModelNotFoundException {
        getLibroById(Id);
        _libroRepository.deleteById(Id);
        respuesta.setMensaje("se ha eliminado.");
        return respuesta;
    }

}
