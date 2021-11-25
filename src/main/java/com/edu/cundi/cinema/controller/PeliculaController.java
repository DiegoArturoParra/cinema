package com.edu.cundi.cinema.controller;

// import javax.validation.Valid;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

// import com.edu.cundi.cinema.DTOs.RespuestaDTO;
// import com.edu.cundi.cinema.entity.Pelicula;
// import com.edu.cundi.cinema.exception.ConflictException;
// import com.edu.cundi.cinema.exception.ModelNotFoundException;
// import com.edu.cundi.cinema.services.interfaces.ICRUD;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import io.swagger.annotations.ApiOperation;
// import io.swagger.annotations.ApiParam;
// import io.swagger.annotations.ApiResponse;
// import io.swagger.annotations.ApiResponses;

// @RestController
// @RequestMapping("/peliculas/")
// @Validated
public class PeliculaController {

        // @Autowired
        // @Qualifier("PeliculaService")
        // private ICRUD<Pelicula> service;

        // @ApiOperation(value = "Busca Una pelicula por id", response = Pelicula.class)

        // @ApiResponses(value = { @ApiResponse(code = 200, message = "Pelicula Encontrada"),
        //                 @ApiResponse(code = 401, message = "No tienes autorizacion para ver esta pelicula"),
        //                 @ApiResponse(code = 403, message = "Está prohibido acceder a esta pelicula"),
        //                 @ApiResponse(code = 404, message = "Pelicula no encontrada") })
        // @GetMapping(value = "{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
        // public ResponseEntity<RespuestaDTO> getPelicula(
        //                 @ApiParam(name = "Codigo", value = "Codigo de la pelicula", required = true) @Valid @PathVariable @Size(min = 4) @NotNull String Id)
        //                 throws ModelNotFoundException {

        //         return ResponseEntity.ok(service.getById(Id));
        // }

        // @ApiOperation(value = "Obtiene un listado de peliculas", response = Pelicula.class)
        // @GetMapping(value = "listado", produces = MediaType.APPLICATION_JSON_VALUE)
        // @ApiResponses(value = { @ApiResponse(code = 200, message = "Peliculas Encontradas"),
        //                 @ApiResponse(code = 404, message = "Peliculas no encontradas") })
        // public ResponseEntity<RespuestaDTO> getPeliculas() throws ModelNotFoundException {
        //         return ResponseEntity.ok(service.getAll());
        // }

        // @ApiOperation(value = "Crear Pelicula")
        // @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE)
        // @ApiResponses(value = { @ApiResponse(code = 201, message = "Pelicula Creada"),
        //                 @ApiResponse(code = 415, message = "Formato no valido")

        // })
        // public ResponseEntity<?> CrearPelicula(@Valid @RequestBody Pelicula entity)
        //                 throws ConflictException, ModelNotFoundException {
        //         RespuestaDTO respuestaDTO = service.create(entity);
        //         return new ResponseEntity<RespuestaDTO>(respuestaDTO, HttpStatus.CREATED);
        // }

        // @ApiOperation(value = "Editar una pelicula")
        // @PutMapping(value = "editar")
        // @ApiResponses(value = { @ApiResponse(code = 200, message = "Pelicula Editada"),
        //                 @ApiResponse(code = 415, message = "Formato no valido"),
        //                 @ApiResponse(code = 404, message = "Pelicula no encontrada"),
        //                 @ApiResponse(code = 400, message = "Url Erronea")

        // })
        // public ResponseEntity<String> EditarPelicula(@Valid @RequestBody Pelicula entity)
        //                 throws ConflictException, ModelNotFoundException {
        //         return ResponseEntity.ok(service.edit(entity).getMensaje());
        // }

        // @ApiOperation(value = "Eliminar Pelicula")
        // @ApiResponses(value = { @ApiResponse(code = 204, message = "Pelicula Editada"),
        //                 @ApiResponse(code = 404, message = "Pelicula no encontrada") })
        // @DeleteMapping(value = "{Id}")
        // public ResponseEntity<?> EliminarPelicula(
        //                 @Valid @ApiParam(name = "Codigo", value = "Codigo de la pelicula", required = true) @PathVariable @Size(min = 4) @NotNull String Id)
        //                 throws ModelNotFoundException {
        //         service.delete(Id);
        //         return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        // }

}
