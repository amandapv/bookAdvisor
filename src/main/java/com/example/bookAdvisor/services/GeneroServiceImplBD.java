package com.example.bookAdvisor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.repositories.GeneroRepository;

@Service
public class GeneroServiceImplBD implements GeneroService{
    
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> obtenerTodos() {
        return generoRepository.findAll();
    }

    public Genero obtenerPorId (long id) throws RuntimeException{
        return generoRepository.findById(id).orElseThrow( ()-> new RuntimeException("No se encuentra el género") );
    }

    public Genero añadir (Genero genero) throws RuntimeException{
        if (genero.getId() != null && generoRepository.existsById(genero.getId())) {
            throw new RuntimeException("Género ya existente");
        }
        return generoRepository.save(genero);
    }

    public Genero editar (Genero genero) throws RuntimeException{
        obtenerPorId(genero.getId());
        return generoRepository.save(genero);
    }

    public void borrar (long id) {
        obtenerPorId(id);
        generoRepository.deleteById(id);
    }
    
}
