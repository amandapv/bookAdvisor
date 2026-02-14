package com.example.bookAdvisor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookAdvisor.domain.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long>{
    
}
