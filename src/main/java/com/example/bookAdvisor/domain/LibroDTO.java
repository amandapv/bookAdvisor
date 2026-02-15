package com.example.bookAdvisor.domain;

import lombok.Data;

@Data

//Dto para la vista que muestra todos los libros bookListView.html
public class LibroDTO {
    private Long id;
    private String portada = "DEFAULT.png"; //así si no establezco una portada por defecto se establece la por defecto
    private String titulo;
    private String autor;
    private String sinopsis;
    private Idioma idioma;
    private Double puntuacionMedia;
}
