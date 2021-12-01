package com.edu.cundi.cinema.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.edu.cundi.cinema.DTOs.PaginarDTO;
import com.edu.cundi.cinema.DTOs.RespuestaDTO;
import com.edu.cundi.cinema.entity.Rol;
import com.edu.cundi.cinema.entity.Usuario;
import com.edu.cundi.cinema.exception.ConflictException;
import com.edu.cundi.cinema.exception.ModelNotFoundException;
import com.edu.cundi.cinema.repository.IRolRepository;
import com.edu.cundi.cinema.repository.IUsuarioRepository;
import com.edu.cundi.cinema.services.interfaces.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {
    private RespuestaDTO respuesta;

    public UsuarioService() {
        respuesta = new RespuestaDTO();
    }

    @Autowired
    private IUsuarioRepository _usuarioRepo;

    @Autowired
    private IRolRepository _rolRepo;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = _usuarioRepo.findOneByNick(username);
        if (usuario == null)
            new UsernameNotFoundException("----Usuario no encontrado");

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));

        UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);
        return ud;
    }

    @Override
    public RespuestaDTO getAll() throws ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    private void existeNickOfUser(String nick) throws ConflictException {
        boolean existe = _usuarioRepo.existsByNick(nick);
        if (existe) {
            throw new ConflictException("el nick name ya existe digite otro.");
        }
    }

    @Override
    public PaginarDTO getPaginado(int page, int pageSize) throws ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO getById(Integer Id) throws ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO create(Usuario entidad) throws ConflictException, ModelNotFoundException {
        respuesta = null;
        existeNickOfUser(entidad.getNick());
        existeDocumento(entidad.getDocumento());
        Rol rol = existRolById(entidad.getRol().getIdRol());
        entidad.setRol(rol);
        entidad.setClave(bcrypt.encode(entidad.getClave()));
        _usuarioRepo.save(entidad);
        respuesta.setMensaje("usuario registrado satisfactoriamente.");
        return respuesta;
    }

    private void existeDocumento(String documento) throws ConflictException {
        boolean existe = _usuarioRepo.existsByDocumento(documento);
        if (existe) {
            throw new ConflictException("el documento ya existe digite otro.");
        }
    }

    @Override
    public RespuestaDTO edit(Usuario entidad) throws ConflictException, ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RespuestaDTO delete(Integer Id) throws ModelNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    private Rol existRolById(Integer Id) throws ModelNotFoundException {
        Optional<Rol> rol = _rolRepo.findById(Id);
        if (!rol.isPresent()) {
            throw new ModelNotFoundException("Este rol no existe.");
        }
        return rol.get();
    }

    private void existeNombreOfRol(String nombre) throws ConflictException {
        boolean existe = _rolRepo.existsByNombre(nombre);
        if (existe) {
            throw new ConflictException("el nombre del rol ya existe digite otro.");
        }
    }

    @Override
    public RespuestaDTO createRol(Rol objeto) throws ConflictException {
        existeNombreOfRol(objeto.getNombre());
        _rolRepo.save(objeto);
        respuesta.setData(objeto);
        respuesta.setMensaje("rol registrado.");
        return respuesta;
    }

    @Override
    public void existToken(String tokenEntrada) throws ModelNotFoundException {

        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
            if (!token.equals(tokenEntrada)) {
                throw new ModelNotFoundException("Error: El token es invalido.");
            }
        }
    }
}
