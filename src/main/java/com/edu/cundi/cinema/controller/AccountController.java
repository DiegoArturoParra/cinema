package com.edu.cundi.cinema.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Rol;
import com.edu.cundi.cinema.entity.Usuario;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.services.interfaces.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/account/")
@Validated
public class AccountController {

    @Autowired
    private IUsuarioService service;
    @Autowired
    private ConsumerTokenServices tokenServices;

    @GetMapping("/signOut/{tokenId:.*}")
	public void revocarToken(@PathVariable("tokenId") String token) throws ModelNotFoundException {
        service.existToken(token);
		tokenServices.revokeToken(token);
	}	

    @ApiOperation(value = "registrar usuario")
    @PostMapping(value = "registrarse", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Autor Creada"),
            @ApiResponse(code = 415, message = "Formato no valido")

    })
    public ResponseEntity<RespuestaDTO> Registrarse(@Valid @RequestBody Usuario entity)
            throws ConflictException, ModelNotFoundException {
        RespuestaDTO response = service.create(entity);
        return new ResponseEntity<RespuestaDTO>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "registrar rol")
    @PostMapping(value = "crear-rol", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "rol Creado"),
            @ApiResponse(code = 415, message = "Formato no valido")

    })
    public ResponseEntity<RespuestaDTO> CrearRol(@Valid @RequestBody Rol entity)
            throws ConflictException {
        RespuestaDTO response = service.createRol(entity);
        return new ResponseEntity<RespuestaDTO>(response, HttpStatus.CREATED);
    }
}
