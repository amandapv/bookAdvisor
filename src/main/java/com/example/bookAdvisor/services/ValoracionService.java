package com.example.bookAdvisor.services;

import java.util.List;

import com.example.bookAdvisor.domain.Valoracion;

public interface ValoracionService {

    List<Valoracion> obtenerTodos();
    Valoracion obtenerPorId (long id);
    Valoracion añadir (Valoracion valoracion);
    Valoracion editar (Valoracion valoracion);
    void borrar (long id);
    Valoracion buscarValoracion (long idUsuario, long idLibro);
}
