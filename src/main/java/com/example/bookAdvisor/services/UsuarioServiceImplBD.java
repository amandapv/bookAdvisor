package com.example.bookAdvisor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookAdvisor.domain.Usuario;
import com.example.bookAdvisor.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImplBD implements UsuarioService{
 
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId (long id) throws RuntimeException{
        return usuarioRepository.findById(id).orElseThrow( ()-> new RuntimeException("No se encuentra el usuario") );
    }

    public Usuario añadir (Usuario usuario) throws RuntimeException{
        if (usuario.getId() != null && usuarioRepository.existsById(usuario.getId())) {
            throw new RuntimeException("Usuario ya existente");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario editar (Usuario usuario) throws RuntimeException{
        obtenerPorId(usuario.getId());
        return usuarioRepository.save(usuario);
    }

    public void borrar (long id) {
        obtenerPorId(id);
        usuarioRepository.deleteById(id);
    }
}
