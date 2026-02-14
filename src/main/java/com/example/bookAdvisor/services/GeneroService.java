package com.example.bookAdvisor.services;

import java.util.List;

import com.example.bookAdvisor.domain.Genero;

public interface GeneroService {
    
    List<Genero> obtenerTodos();
    Genero obtenerPorId (long id);
    Genero añadir (Genero genero);
    Genero editar (Genero genero);
    void borrar (long id);
    
}
