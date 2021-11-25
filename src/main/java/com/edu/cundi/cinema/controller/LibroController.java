package com.edu.cundi.cinema.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.edu.cundi.cinema.DTOs.LibroCreateDTO;
import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Libro;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.services.interfaces.ILibroService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/libros/")
@Validated
public class LibroController {
        @Autowired
        private ILibroService service;
        private ModelMapper _mapper = new ModelMapper();

        @ApiOperation(value = "Busca Una libro por id", response = Libro.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "libro Encontrada"),
                        @ApiResponse(code = 401, message = "No tienes libroizacion para ver esta libro"),
                        @ApiResponse(code = 403, message = "Está prohibido acceder a esta libro"),
                        @ApiResponse(code = 404, message = "libro no encontrada") })
        @GetMapping(value = "{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<RespuestaDTO> getlibro(
                        @ApiParam("id del libro") @PathVariable @Min(1) Integer Id)
                        throws ModelNotFoundException {

                return ResponseEntity.ok(service.getById(Id));
        }

        @ApiOperation(value = "Obtiene un listado de libros", response = Libro.class)
        @GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "libros Encontradas"),
                        @ApiResponse(code = 404, message = "libros no encontradas") })
        public ResponseEntity<RespuestaDTO> getlibros() throws ModelNotFoundException {
                return ResponseEntity.ok(service.getAll());
        }

        @ApiOperation(value = "Obtiene paginado de libros", response = PaginarDTO.class)
        @GetMapping(value = "paginar", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "libros Encontradas"),
                        @ApiResponse(code = 404, message = "libros no encontradas") })
        public ResponseEntity<PaginarDTO> getPaginarlibros(@RequestParam int page, @RequestParam int pageSize)
                        throws ModelNotFoundException {

                return ResponseEntity.ok(service.getPaginado(page, pageSize));
        }

        @ApiOperation(value = "Crear libro")
        @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "libro Creada"),
                        @ApiResponse(code = 415, message = "Formato no valido")

        })
        public ResponseEntity<RespuestaDTO> Crearlibro(@Valid @RequestBody LibroCreateDTO entity)
                        throws ConflictException, ModelNotFoundException {
                Libro libro = _mapper.map(entity, Libro.class);
                RespuestaDTO response = service.create(libro);
                return new ResponseEntity<RespuestaDTO>(response, HttpStatus.CREATED);
        }

        @ApiOperation(value = "Editar una libro")
        @PutMapping(value = "editar")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "libro Editada"),
                        @ApiResponse(code = 415, message = "Formato no valido"),
                        @ApiResponse(code = 404, message = "libro no encontrada"),
                        @ApiResponse(code = 400, message = "Url Erronea")

        })
        public ResponseEntity<String> Editarlibro(@Valid @RequestBody LibroCreateDTO entity)
                        throws ConflictException, ModelNotFoundException {
                Libro libro = _mapper.map(entity, Libro.class);
                return ResponseEntity.ok(service.edit(libro).getMensaje());
        }

        @ApiOperation(value = "Eliminar libro")
        @ApiResponses(value = { @ApiResponse(code = 204, message = "libro Editada"),
                        @ApiResponse(code = 404, message = "libro no encontrada") })
        @DeleteMapping(value = "{Id}")
        public ResponseEntity<?> Eliminarlibro(
                        @ApiParam("id del libro") @Valid @PathVariable @Min(1) @NotNull Integer Id)
                        throws ModelNotFoundException {
                service.delete(Id);
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
}
