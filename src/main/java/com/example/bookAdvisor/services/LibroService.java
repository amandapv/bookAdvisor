package com.example.bookAdvisor.services;

import java.util.List;

import com.example.bookAdvisor.domain.Libro;

public interface LibroService {
    List<Libro> obtenerTodos();

    Libro obtenerPorId(long id);

    Libro añadir(Libro libro);

    Libro editar(Libro libro);

    void borrar(Long id);
}
