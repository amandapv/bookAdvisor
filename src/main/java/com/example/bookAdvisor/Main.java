package com.example.bookAdvisor;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.domain.Idioma;
import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.services.LibroService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(LibroService libroService) {
		return args -> {
			libroService.añadir(new Libro(1l, "El señor de los anillos", 1980, Genero.FANTASIA, "J.R. Tolkien", Idioma.ESPANHOL, "Lorem Impsum", LocalDate.now()));
			libroService.añadir(new Libro(2l, "El señor de los anillos", 1980, Genero.FANTASIA, "J.R. Tolkien", Idioma.ESPANHOL, "Lorem Impsum", LocalDate.now()));
			libroService.añadir(new Libro(3l, "El señor de los anillos", 1980, Genero.FANTASIA, "J.R. Tolkien", Idioma.ESPANHOL, "Lorem Impsum", LocalDate.now()));
			libroService.añadir(new Libro(4l, "El señor de los anillos", 1980, Genero.FANTASIA, "J.R. Tolkien", Idioma.ESPANHOL, "Lorem Impsum", LocalDate.now()));
		};
	}

}
