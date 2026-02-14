package com.example.bookAdvisor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookAdvisor.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    
}
