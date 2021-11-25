package com.edu.cundi.cinema.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Autor;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.services.interfaces.IAutorService;

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
@RequestMapping("/autores/")
@Validated
public class AutorController {
        @Autowired
        private IAutorService service;

        @ApiOperation(value = "Busca Una Autor por id", response = Autor.class)

        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autor Encontrada"),
                        @ApiResponse(code = 401, message = "No tienes autorizacion para ver esta Autor"),
                        @ApiResponse(code = 403, message = "Está prohibido acceder a esta Autor"),
                        @ApiResponse(code = 404, message = "Autor no encontrada") })
        @GetMapping(value = "{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<RespuestaDTO> getAutor(
                        @ApiParam("id del autor") @PathVariable @Min(1) @NotNull Integer Id)
                        throws ModelNotFoundException {

                return ResponseEntity.ok(service.getById(Id));
        }

        @ApiOperation(value = "Obtiene un listado de Autores", response = Autor.class)
        @GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autors Encontradas"),
                        @ApiResponse(code = 404, message = "Autors no encontradas") })
        public ResponseEntity<RespuestaDTO> getAutores() throws ModelNotFoundException {
                return ResponseEntity.ok(service.getAll());
        }

        @ApiOperation(value = "Obtiene paginado de Autores", response = Autor.class)
        @GetMapping(value = "paginar", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autores Encontradas"),
                        @ApiResponse(code = 404, message = "Autores no encontradas") })
        public ResponseEntity<PaginarDTO> getPaginarAutores(@RequestParam int page, @RequestParam int pageSize)
                        throws ModelNotFoundException {

                return ResponseEntity.ok(service.getPaginado(page, pageSize));
        }

        @ApiOperation(value = "Crear Autor")
        @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Autor Creada"),
                        @ApiResponse(code = 415, message = "Formato no valido")

        })
        public ResponseEntity<RespuestaDTO> CrearAutor(@Valid @RequestBody Autor entity)
                        throws ConflictException, ModelNotFoundException {
                RespuestaDTO response = service.create(entity);
                return new ResponseEntity<RespuestaDTO>(response, HttpStatus.CREATED);
        }

        @ApiOperation(value = "Editar una Autor")
        @PutMapping(value = "editar")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Autor Editada"),
                        @ApiResponse(code = 415, message = "Formato no valido"),
                        @ApiResponse(code = 404, message = "Autor no encontrada"),
                        @ApiResponse(code = 400, message = "Url Erronea")

        })
        public ResponseEntity<String> EditarAutor(@Valid @RequestBody Autor entity)
                        throws ConflictException, ModelNotFoundException {
                return ResponseEntity.ok(service.edit(entity).getMensaje());
        }

        @ApiOperation(value = "Eliminar autor")
        @ApiResponses(value = { @ApiResponse(code = 204, message = "Autor Editada"),
                        @ApiResponse(code = 404, message = "Autor no encontrada") })
        @DeleteMapping(value = "{Id}")
        public ResponseEntity<?> EliminarAutor(
                        @ApiParam("id del autor") @Valid @PathVariable @Min(1) @NotNull Integer Id)
                        throws ModelNotFoundException {
                service.delete(Id);
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
}
