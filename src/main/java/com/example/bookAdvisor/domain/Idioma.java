package com.example.bookAdvisor.domain;

public enum Idioma {
    
    ESPANHOL("Español"),
    INGLES("Inglés"),
    OTROS("Otros");

    private final String nombreVisible;

    Idioma(String nombreVisible) {
        this.nombreVisible = nombreVisible;
    }

    public String getNombreVisible() {
        return nombreVisible;
    }
    
}
