package com.example.bookAdvisor.services;

import java.util.List;

import com.example.bookAdvisor.domain.Usuario;

public interface UsuarioService {
    
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId (long id);
    Usuario añadir (Usuario usuario);
    Usuario editar (Usuario usuario);
    void borrar (long id);

}