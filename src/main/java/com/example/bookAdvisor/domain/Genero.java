package com.example.bookAdvisor.domain;

public enum Genero {

    ACCION("Acción"), 
    COMEDIA("Comedia"), 
    DRAMA("Drama"), 
    AVENTURA("Aventura"),
    CIENCIA_FICCION("Ciencia Ficción"),
    TERROR("Terror"),
    FANTASIA("Fantsaía"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    MISTERIO("Misterio");


    private final String nombreVisible;


    Genero(String nombreVisible) {
        this.nombreVisible = nombreVisible;
    }

    
    public String getNombreVisible() {
        return nombreVisible;
    }
    
}