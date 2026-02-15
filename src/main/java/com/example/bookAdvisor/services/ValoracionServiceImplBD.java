package com.example.bookAdvisor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.Usuario;
import com.example.bookAdvisor.domain.Valoracion;
import com.example.bookAdvisor.repositories.ValoracionRepository;

import lombok.val;

@Service
public class ValoracionServiceImplBD implements ValoracionService{
    
    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LibroService libroService;

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
        libroService.sumaValoracion(valoracion.getLibro(), valoracion.getPuntuacion()); //llamo al método del libro para que cuando añada una valoración, se guarde en el libro la suma de todas estas y así no haya que recorrerse las valoraciones cada vez que quiera pintar la media de las puntuaciones
        return valoracionRepository.save(valoracion);
    }

    public Valoracion editar (Valoracion valoracion) throws RuntimeException{
        Valoracion valoracionVieja = obtenerPorId(valoracion.getId()); //obtengo la valoración  y puantuación vieja
        libroService.restaValoracion(valoracionVieja.getLibro(), valoracionVieja.getPuntuacion()); //se la resto
        libroService.sumaValoracion(valoracion.getLibro(), valoracion.getPuntuacion()); //le sumo la nueva valoración
        return valoracionRepository.save(valoracion);
    }

    public void borrar (Valoracion valoracion) {
        obtenerPorId(valoracion.getId());
        libroService.restaValoracion(valoracion.getLibro(), valoracion.getPuntuacion()); //llamo al método de restar valoraciones que eliminará la valoración actual restando la puntacion de esa persona y restando un 1 (es decir, a esa persona)
        valoracionRepository.delete(valoracion);
    }

    //método para buscar valoraciones por un libro
    public List<Valoracion> buscarValoraciones (long idLibro) throws RuntimeException {
        libroService.obtenerPorId(idLibro); //ver si existe el libro
        return valoracionRepository.findByLibroId(idLibro);
    }

    //método para buscar usuarios
    public List<Valoracion> buscarUsuariosValoracion (long idUsuario) throws RuntimeException {
        usuarioService.obtenerPorId(idUsuario);
        return valoracionRepository.findByUsuarioId(idUsuario);
    }

    //método para buscar valoracion por usuario y libro
    public Valoracion buscarValoracionUsuLibro (long idUsuario, long idLibro) {
        return valoracionRepository.findByLibroIdAndUsuarioId(idUsuario, idLibro);
    }

    //método para que un usuario realice valoraciones de un libro
    // public Valoracion añadir(long idUsuario, long idLibro) throws RuntimeException{
    //     //veo si existe el usuario y el libro
    //     Usuario usuario = usuarioService.obtenerPorId(idUsuario); 
    //     Libro libro = libroService.obtenerPorId(idLibro);

    //     //ver si ya existe una valoración hecha por un usuario a un libro
    //     if (buscarValoracionUsuLibro(idUsuario, idLibro) != null) {
    //         throw new RuntimeException("No puedes crear otra valoración para este libro");
    //     }
        
    //     //añadir la valoración en caso de que todo vaya bien
    //     return valoracionRepository.save(new Valoracion(null, usuario, libro, 0, ""));
    // }


    
    //método para realizar la puntuación media de cada libro --- YA NO UTILIZO ESTE MÉTODO YA QUE AHORA CALCULO LA MEDIA DE LOS VOTOS EN EL SERVICIO DEL LIBRO (al añadir, editar o borrar un libro) y así poder mostrar los datos como la cantidad de votantes y la suma de puntos en el libro
    // public Libro puntuacionMediaPorLibro(Libro libro) {
    //     List<Valoracion> valoraciones = valoracionRepository.findByLibroId(libro.getId()); 
    //     Double sumaPuntuacion = 0.0;

    //     for(Valoracion valoracion : valoraciones) {
    //         sumaPuntuacion =+ valoracion.getPuntuacion();
    //     }

    //     Double media = sumaPuntuacion / valoraciones.size();
    //     libro.setPuntuacionMedia(media);
    //     return libro;
    // }
}
