package com.example.bookAdvisor.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.LibroDTO;
import com.example.bookAdvisor.repositories.LibroRepository;

@Service
public class LibroServiceImplBD implements LibroService{

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ModelMapper modelMapper; //llamo a mi modelMapper definido en la clase ModelMapperConfig en la carpeta config para poder convertir entidades a DTOs

    private final Path DIRECTORIO_PORTADAS = Paths.get("portadas");

    //CRUD
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(long id) throws RuntimeException {
        return libroRepository.findById(id).orElseThrow( ()-> new RuntimeException("No se ha encontrado el libro con ese ID") );
    }

    public Libro añadir (Libro libro) throws RuntimeException {
        if (libro.getId() != null && libroRepository.existsById(libro.getId())) {
            throw new RuntimeException("Libro ya existente");
        }
        return libroRepository.save(libro);
    }

    public Libro editar (Libro libro) throws RuntimeException {
        obtenerPorId(libro.getId()); //si no lo encuentra ya saltará una excepción
        return libroRepository.save(libro);
    }

    public void borrar (Long id) throws RuntimeException {
        obtenerPorId(id); //si no lo encuentra ya saltará una excepción
        libroRepository.deleteById(id);
    }


    //Filtros
    public List<Libro> buscarPorTituloLibro(String tituloLibro) {
        return libroRepository.findByTituloContainingIgnoreCase(tituloLibro);
    }

    public List<Libro> buscarPorGeneroLibro(Genero genero) {
        return libroRepository.findByGenero(genero);
    }


    //lectura de ficheros para añadir portadas a los libros
    public String añadirPortadaLibro(MultipartFile fichero) throws RuntimeException {
        if (fichero == null || fichero.isEmpty()) {
            return "DEFAULT.png"; // O una imagen por defecto
        }

        try {
            // Asegurarnos de que exista el directorio externo de portadas
            Files.createDirectories(DIRECTORIO_PORTADAS.toAbsolutePath());

            // 1. Generar un nombre único para evitar que fotos con el mismo nombre se borren
            String nombreUnico = System.currentTimeMillis() + "_" + fichero.getOriginalFilename();

            // 2. Definir ruta y guardar
            Path rutaAbsoluta = DIRECTORIO_PORTADAS.toAbsolutePath().resolve(nombreUnico);
            Files.write(rutaAbsoluta, fichero.getBytes());

            return nombreUnico; // Devolvemos el nombre para guardarlo en la base de datos

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
        }
    }

    //método para convertir la entidad Libro a DTO
    public List<LibroDTO> convertLibroToDto (List<Libro>listaLibros) {
        List<LibroDTO> listaLibrosDTO = new ArrayList<>();
        for(Libro libro : listaLibros) {
            listaLibrosDTO.add(modelMapper.map(libro, LibroDTO.class)); 
        }
        return listaLibrosDTO;
    }

}
