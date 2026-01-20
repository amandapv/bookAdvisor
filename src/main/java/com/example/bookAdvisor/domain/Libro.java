package com.example.bookAdvisor.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor //Crea un constructor para todos los campos
@NoArgsConstructor //en cuanto se añade el @AllArgsConstructor, Java "borra" el constructor vacío automático. Pero Hibernate/JPA necesita el constructor vacío para poder recuperar datos de la base de datos. Sin esta, tu aplicación daría error al intentar leer de la BD.
@EqualsAndHashCode(of = "id")


public class Libro {

    @NotNull(message = "El ID no puede estar vacío")
    @Min(value = 0, message = "El ID no puede ser inferior a 0")
    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    private String titulo;

    private int anho;

    private Genero genero;

    private String autor;

    private Idioma idioma;
    
    private String sinopsis;

    private LocalDate fechaAlta;

}
