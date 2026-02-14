package com.example.bookAdvisor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.Usuario;
import com.example.bookAdvisor.domain.Valoracion;
import com.example.bookAdvisor.repositories.ValoracionRepository;

@Service
public class ValoracionServiceImplBD implements ValoracionService{
    
    @Autowired
    private ValoracionRepository valoracionRepository;

    // @Autowired
    // private UsuarioService usuarioService;

    // @Autowired
    // private LibroService libroService;

    public List<Valoracion> obtenerTodos() {
        return valoracionRepository.findAll();
    }

    public Valoracion obtenerPorId (long id) throws RuntimeException{
        return valoracionRepository.findById(id).orElseThrow( ()-> new RuntimeException("No se encuentra la valoración") );
    }

    public Valoracion añadir (Valoracion valoracion) throws RuntimeException{
        if (valoracion.getId() != null && valoracionRepository.existsById(valoracion.getId())) {
            throw new RuntimeException("Valoración ya existente");
        }
        return valoracionRepository.save(valoracion);
    }

    public Valoracion editar (Valoracion valoracion) throws RuntimeException{
        obtenerPorId(valoracion.getId());
        return valoracionRepository.save(valoracion);
    }

    public void borrar (long id) {
        obtenerPorId(id);
        valoracionRepository.deleteById(id);
    }

    //método para buscar valoracion por usuario y libro
    public Valoracion buscarValoracion (long idUsuario, long idLibro) {
        return valoracionRepository.findByLibroIdAndUsuarioId(idUsuario, idLibro);
    }

    //método para que un usuario realice valoraciones de un libro
    // public Valoracion añadir(long idUsuario, long idLibro) throws RuntimeException{
    //     //veo si existe el usuario y el libro
    //     Usuario usuario = usuarioService.obtenerPorId(idUsuario); 
    //     Libro libro = libroService.obtenerPorId(idLibro);

    //     //ver si ya existe una valoración hecha por un usuario a un libro
    //     if (buscarValoracion(idUsuario, idLibro) != null) {
    //         throw new RuntimeException("No puedes crear otra valoración para este libro");
    //     }
        
    //     //añadir la valoración en caso de que todo vaya bien
    //     return valoracionRepository.save(new Valoracion(null, usuario, libro, 0, ""));
    // }
}
