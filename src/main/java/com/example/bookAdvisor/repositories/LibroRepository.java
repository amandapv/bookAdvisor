package com.example.bookAdvisor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.domain.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    
    //método para buscar por el título de un libro
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    //método para buscar por el género de un libro
    List<Libro> findByGenero(Genero genero);

    //método para saber si existe un genero en un libro, es decir si hay géneros asociados a un libro
    boolean existsByGenero(Genero genero);
}
