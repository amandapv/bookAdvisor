package com.example.bookAdvisor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookAdvisor.domain.Usuario;
import com.example.bookAdvisor.domain.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long>{

    //método para buscar Usuarios
    List<Usuario> findByUsuario(Usuario usario);

    //método para buscar Valoración por usuario y libro
    Valoracion findByLibroIdAndUsuarioId(long idUsuario, long idLibro);
}
