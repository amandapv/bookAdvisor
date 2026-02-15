package com.example.bookAdvisor.services;

import java.util.List;

import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.Valoracion;

public interface ValoracionService {

    List<Valoracion> obtenerTodos();
    Valoracion obtenerPorId (long id);
    Valoracion añadir (Valoracion valoracion);
    Valoracion editar (Valoracion valoracion);
    void borrar (Valoracion valoracion);
    List<Valoracion> buscarValoraciones (long idLibro);
    List<Valoracion> buscarUsuariosValoracion (long idUsuario);
    Valoracion buscarValoracionUsuLibro (long idUsuario, long idLibro);
    // Libro puntuacionMediaPorLibro(Libro libro);
}
