package com.example.bookAdvisor.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
// @AllArgsConstructor //Crea un constructor para todos los campos. LO COMENTO PORQUE HE CREADO MI CONSTRUCTOR YO A MEDIDA
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD.
@EqualsAndHashCode(of = "id")


public class Libro {

    @NotNull(message = "El ID no puede estar vacío")
    @Min(value = 0, message = "El ID no puede ser inferior a 0")
    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    private String titulo;

    private int anho;

    @NotNull(message = "Debe seleccionar un idioma")
    private Genero genero;

    private String autor;

    @NotNull(message = "Debe seleccionar un idioma")
    private Idioma idioma;
    
    private String sinopsis;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlta = LocalDate.now();

    private String portada = "DEFAULT.png"; //así si no establezco una portada por defecto se establece la por defecto


    // Constructor manual SIN la fecha para hacer automáticamente el LocalDate.now()
    public Libro(Long id, String titulo, int anho, Genero genero, String autor, Idioma idioma, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.genero = genero;
        this.autor = autor;
        this.idioma = idioma;
        this.sinopsis = sinopsis;
        // No tocamos fechaAlta, así que usará LocalDate.now() por defecto
        // No tocamos portada, así que usará la imagen por defecto con ese nombre
    }
}
