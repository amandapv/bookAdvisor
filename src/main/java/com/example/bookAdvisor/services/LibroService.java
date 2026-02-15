package com.example.bookAdvisor.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.LibroDTO;

public interface LibroService {
    List<Libro> obtenerTodos();

    Libro obtenerPorId(long id);

    Libro añadir(Libro libro);

    Libro editar(Libro libro);

    void borrar(Long id);

    List<Libro> buscarPorTituloLibro(String tituloLibro);

    List<Libro> buscarPorGeneroLibro(Genero genero);

    String añadirPortadaLibro(MultipartFile fichero);

    List<LibroDTO> convertLibroToDto (List<Libro>listaLibros);

    void sumaValoracion(Libro libro, Double valoracionPersona);

    void restaValoracion(Libro libro, Double valoracionPersona);
}
