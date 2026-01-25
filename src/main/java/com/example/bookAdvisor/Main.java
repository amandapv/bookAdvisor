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
			libroService.añadir(new Libro(1l, "El señor de los anillos", 1954, Genero.FANTASIA, "J.R.R. Tolkien", Idioma.ESPANHOL, "La trama gira en torno al Anillo Único, forjado por el Señor Oscuro Sauron para dominar a todos los pueblos libres.", LocalDate.now()));
			libroService.añadir(new Libro(2l, "Harry Potter y la piedra filosofal", 1997, Genero.FANTASIA, "J.K. Rowling", Idioma.ESPANHOL, "Un niño huérfano que vive con sus tíos, hasta que, en su undécimo cumpleaños, recibe una carta de admisión al Colegio Hogwarts de Magia y Hechicería.", LocalDate.now()));
			libroService.añadir(new Libro(3l, "El nombre del viento", 2009, Genero.FANTASIA, "Patrick Rothfuss", Idioma.ESPANHOL, "Kvothe, un hombre legendario que todos creían muerto, vive en una posada apartada bajo el nombre de Kote.", LocalDate.now()));
			libroService.añadir(new Libro(4l, "Las calles del parque del pánico", 2014, Genero.TERROR, "R.L. Stine", Idioma.ESPANHOL, "Niños atrapados en HorrorLandia creen que el Parque del Pánico es su única salida, pero descubren que es un lugar aún más peligroso.", LocalDate.now()));
			libroService.añadir(new Libro(5l, "Orgullo y prejuicio", 2014, Genero.ROMANCE, "Jane Austen", Idioma.ESPANHOL, "Cinco hermanas en una familia de la clase media inglesa, cuya madre busca casarlas bien.", LocalDate.now()));
		};
	}

}
