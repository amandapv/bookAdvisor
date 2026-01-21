package com.example.bookAdvisor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookAdvisor.domain.Libro;

@Service
public class LibroServiceImpl implements LibroService{
    private List<Libro> repositorio = new ArrayList<>();

    //CRUD
    public List<Libro> obtenerTodos() {
        return repositorio;
    }

    public Libro obtenerPorId(long id) throws RuntimeException {
        for (Libro libro : repositorio) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        throw new RuntimeException("No se ha encontrado el libro con ese ID");
    }

    public Libro añadir (Libro libro) throws RuntimeException {
        if (repositorio.contains(libro)) {
            throw new RuntimeException("Libro ya encontrado");
        }
        repositorio.add(libro);
        return libro;
    }

    public Libro editar (Libro libro) throws RuntimeException {
        int pos = repositorio.indexOf(libro);
        if (pos == -1) {
            throw new RuntimeException("Libro no encontrado");
        }
        repositorio.set(pos, libro);
        return libro;
    }

    public void borrar (Long id) throws RuntimeException {
        Libro libro = this.obtenerPorId(id);

        if (libro != null) {
            repositorio.remove(libro);
        } else {
            throw new RuntimeException("No se ha podido encontrar el curso");
        }
    }

    
}
