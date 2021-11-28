package com.edu.cundi.cinema.services.implement;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import com.edu.cundi.cinema.DTOs.AutorDTO;
import com.edu.cundi.cinema.DTOs.AutorIdModel;
import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.controller.AutorController;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IAutorRepository;
import com.edu.cundi.cinema.services.interfaces.IAutorService;
import com.google.common.reflect.TypeToken;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

@Service
public class AutorService implements IAutorService {

    private RespuestaDTO respuesta = new RespuestaDTO();
    @Autowired
    private IAutorRepository _autorRepository;
    private ModelMapper _mapper = new ModelMapper();

    @Override
    public RespuestaDTO getAll() throws ModelNotFoundException {
        List<Autor> autores = _autorRepository.findAll();
        if (autores.size() == 0) {
            respuesta.setMensaje("no hay autores");
        } else {
            respuesta.setMensaje("hay: " + autores.size());
        }
        List<AutorDTO> autoresDTOs = (List<AutorDTO>) _mapper.map(autores, new TypeToken<List<AutorDTO>>() {
        }.getType());

        for (AutorDTO autorDTO : autoresDTOs) {
            autorDTO.add(LinkAutor(autorDTO.getId()));
        }
        respuesta.setData((autoresDTOs));
        return respuesta;
    }

    public Autor ExistAutorById(Integer Id) throws ModelNotFoundException {
        Optional<Autor> autor = _autorRepository.findById(Id);
        if (!autor.isPresent()) {
            throw new ModelNotFoundException("Este autor no existe.");
        }
        return autor.get();
    }

    @Override
    public RespuestaDTO getById(Integer Id) throws ModelNotFoundException {
        respuesta.setData(ExistAutorById(Id));
        respuesta.setMensaje("encontrado");
        return respuesta;
    }

    @Override
    public RespuestaDTO create(Autor entidad) throws ConflictException, ModelNotFoundException {
        existeCedula(entidad, false);
        existeCorreo(entidad, false);
        if (entidad.getLibro() != null) {
            entidad.getLibro().forEach(libro -> {
                libro.setAutor(entidad);
            });
        }
        Autor autor = _autorRepository.save(entidad);
        AutorIdModel autorDto = new AutorIdModel();
        autorDto.setId(autor.getId());
        autorDto.add(LinkAutor(autor.getId()));
        respuesta.setMensaje("creado");
        respuesta.setData(autorDto);
        return respuesta;
    }

    private void existeCorreo(Autor autor, boolean editOrCreate) throws ConflictException {
        if (!editOrCreate) {
            if (_autorRepository.existsByCorreo(autor.getCorreo())) {
                throw new ConflictException("el correo ya existe digite otro.");
            }

        } else {
            long count = _autorRepository.buscarByCorreo(autor.getId(), autor.getCorreo());
            if (count > 0) {
                throw new ConflictException("el correo ya existe digite otro.");
            }
        }
    }

    public Link LinkAutor(Integer Id) throws ModelNotFoundException {
        return linkTo(methodOn(AutorController.class).getAutor(Id)).withRel("Autor");
    }

    public void existeCedula(Autor autor, boolean editOrCreate) throws ConflictException {
        Optional<Autor> existe = _autorRepository.findByCedula(autor.getCedula());

        if (!editOrCreate) {
            if (_autorRepository.existsByCedula(autor.getCedula())) {
                throw new ConflictException("La cedula ya existe digite otra.");
            }
        } else {
            if (existe.isPresent() && existe.get().getId() != autor.getId()) {
                throw new ConflictException("La cedula ya existe digite otra.");
            }
        }
    }

    @Override
    public RespuestaDTO edit(Autor entidad) throws ConflictException, ModelNotFoundException {
        Autor autor = (Autor) getById(entidad.getId()).getData();
        existeCedula(entidad, true);
        existeCorreo(entidad, true);
        autor.setApellido(entidad.getApellido());
        autor.setCedula(entidad.getCedula());
        autor.setNombre(entidad.getNombre());
        autor.setEdad(entidad.getEdad());
        autor.setCorreo(entidad.getCorreo());
        _autorRepository.save(autor);
        respuesta.setMensaje("editado");
        return respuesta;
    }

    @Override
    public RespuestaDTO delete(Integer Id) throws ModelNotFoundException {
        ExistAutorById(Id);
        _autorRepository.deleteById(Id);
        respuesta.setMensaje("se ha eliminado.");
        return respuesta;
    }

    @Override
    public PaginarDTO getPaginado(int page, int pageSize) throws ModelNotFoundException {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Autor> paginado = _autorRepository.findAll(pageable);

        List<AutorDTO> autoresDTOs = (List<AutorDTO>) _mapper.map(paginado.getContent(),
                new TypeToken<List<AutorDTO>>() {
                }.getType());
        for (AutorDTO autorDTO : autoresDTOs) {
            autorDTO.add(LinkAutor(autorDTO.getId()));
        }
        PaginarDTO paginar = new PaginarDTO(pageable.getPageNumber(), paginado.getTotalElements(),
                pageable.getPageSize(), paginado.getTotalPages(), paginado.getNumberOfElements(), autoresDTOs);
        return paginar;
    }

    public void ExisteAutor(Integer idAutor) throws ModelNotFoundException {
        boolean existe = _autorRepository.existeAutor(idAutor);
        if (!existe) {
            throw new ModelNotFoundException("Este autor no existe.");
        }  
    }
}
