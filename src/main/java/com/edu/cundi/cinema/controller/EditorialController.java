package com.edu.cundi.cinema.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.edu.cundi.cinema.DTOs.AutorEditorialDTO;
import com.edu.cundi.cinema.DTOs.EditEditorialDTO;
import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Editorial;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.services.interfaces.IEditorialService;

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
@RequestMapping("/editoriales/")
@Validated
public class EditorialController {

        @Autowired
        private IEditorialService service;
        private ModelMapper _mapper = new ModelMapper();

        @ApiOperation(value = "Obtiene paginado de editoriales", response = Editorial.class)
        @GetMapping(value = "paginar", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "editoriales Encontradas"),
                        @ApiResponse(code = 404, message = "editoriales no encontradas") })
        public ResponseEntity<PaginarDTO> getPaginarEditoriales(@RequestParam int page, @RequestParam int pageSize)
                        throws ModelNotFoundException {

                return ResponseEntity.ok(service.getPaginado(page, pageSize));
        }

        @ApiOperation(value = "Obtiene paginado de editoriales", response = Editorial.class)
        @GetMapping(value = "paginar-autores-by-editorial", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "editoriales Encontradas"),
                        @ApiResponse(code = 404, message = "editoriales no encontradas") })
        public ResponseEntity<PaginarDTO> getPaginarAutoresPorEditorial(@RequestParam int page,
                        @RequestParam int pageSize)
                        throws ModelNotFoundException {
                return ResponseEntity.ok(service.getPaginarAutoresByEditorial(page, pageSize));
        }

        @ApiOperation(value = "Crear editorial con autores")
        @PostMapping(value = "crear-autor-editorial", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "editorial con autor creado"),
                        @ApiResponse(code = 415, message = "Formato no valido")

        })
        public ResponseEntity<RespuestaDTO> CrearAutorConEditorial(@Valid @RequestBody AutorEditorialDTO objeto)
                        throws ConflictException, ModelNotFoundException {
                RespuestaDTO response = service.createEditorialWithAutor(objeto);
                return new ResponseEntity<RespuestaDTO>(response, HttpStatus.CREATED);
        }

        @ApiOperation(value = "Crear editorial")
        @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "editorial Creada"),
                        @ApiResponse(code = 415, message = "Formato no valido")

        })
        public ResponseEntity<RespuestaDTO> CrearEditorial(@Valid @RequestBody Editorial entity)
                        throws ConflictException, ModelNotFoundException {
                RespuestaDTO response = service.create(entity);
                return new ResponseEntity<RespuestaDTO>(response, HttpStatus.CREATED);
        }

        @ApiOperation(value = "Editar una editorial")
        @PutMapping(value = "editar")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "editorial Editada"),
                        @ApiResponse(code = 415, message = "Formato no valido"),
                        @ApiResponse(code = 404, message = "editorial no encontrada"),
                        @ApiResponse(code = 400, message = "Url Erronea")

        })
        public ResponseEntity<String> Editareditorial(@Valid @RequestBody EditEditorialDTO entity)
                        throws ConflictException, ModelNotFoundException {
                Editorial editorial = _mapper.map(entity, Editorial.class);
                return ResponseEntity.ok(service.edit(editorial).getMensaje());
        }

        @ApiOperation(value = "editorial")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "editorial encontada"),
                        @ApiResponse(code = 404, message = "editorial no encontrada") })
        @GetMapping(value = "{Id}")
        public ResponseEntity<?> GetEditorial(
                        @ApiParam("id del editorial") @Valid @PathVariable @Min(1) @NotNull Integer Id)
                        throws ModelNotFoundException {
                service.getById(Id);
                return ResponseEntity.ok(service.getById(Id));
        }

        @ApiOperation(value = "Eliminar editorial")
        @ApiResponses(value = { @ApiResponse(code = 204, message = "editorial eliminada"),
                        @ApiResponse(code = 404, message = "editorial no encontrada") })
        @DeleteMapping(value = "{Id}")
        public ResponseEntity<?> Eliminareditorial(
                        @ApiParam("id del editorial") @Valid @PathVariable @Min(1) @NotNull Integer Id)
                        throws ModelNotFoundException {
                service.delete(Id);
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
}
