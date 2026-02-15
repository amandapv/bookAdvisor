package com.example.bookAdvisor;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.domain.Idioma;
import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.Usuario;
import com.example.bookAdvisor.domain.Valoracion;
import com.example.bookAdvisor.services.GeneroService;
import com.example.bookAdvisor.services.LibroService;
import com.example.bookAdvisor.services.UsuarioService;
import com.example.bookAdvisor.services.ValoracionService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(LibroService libroService, GeneroService generoService, UsuarioService usuarioService, ValoracionService valoracionService) {
		return args -> {

			//AÑADE LIBROS SIN PORTADAS -
			// libroService.añadir(new Libro(1l, "El señor de los anillos", 1954, Genero.FANTASIA, "J.R.R. Tolkien", Idioma.ESPANHOL, "La trama gira en torno al Anillo Único, forjado por el Señor Oscuro Sauron para dominar a todos los pueblos libres."));
			// libroService.añadir(new Libro(2l, "Harry Potter y la piedra filosofal", 1997, Genero.FANTASIA, "J.K. Rowling", Idioma.ESPANHOL, "Un niño huérfano que vive con sus tíos, hasta que, en su undécimo cumpleaños, recibe una carta de admisión al Colegio Hogwarts de Magia y Hechicería."));
			// libroService.añadir(new Libro(3l, "El nombre del viento", 2009, Genero.FANTASIA, "Patrick Rothfuss", Idioma.ESPANHOL, "Kvothe, un hombre legendario que todos creían muerto, vive en una posada apartada bajo el nombre de Kote."));
			// libroService.añadir(new Libro(4l, "Las calles del parque del pánico", 2014, Genero.TERROR, "R.L. Stine", Idioma.ESPANHOL, "Niños atrapados en HorrorLandia creen que el Parque del Pánico es su única salida, pero descubren que es un lugar aún más peligroso."));
			// libroService.añadir(new Libro(5l, "Orgullo y prejuicio", 2014, Genero.ROMANCE, "Jane Austen", Idioma.ESPANHOL, "Cinco hermanas en una familia de la clase media inglesa, cuya madre busca casarlas bien."));

			//Añadir géneros ---
			Genero fantasia = new Genero(null, "Fantasía");
			generoService.añadir(fantasia);
			Genero terror = new Genero(null, "Terror");
			generoService.añadir(terror);
			Genero romance = new Genero(null, "Romance");
			generoService.añadir(romance);
			Genero comedia = new Genero(null, "Comedia");
			generoService.añadir(comedia);
			Genero misterio = new Genero(null, "Misterio");
			generoService.añadir(misterio);

			//AÑADE LIBROS CON PORTADAS -
			// Libro 1
			Libro libro1 = new Libro(null, "El señor de los anillos", 1954, fantasia, "J.R.R. Tolkien", Idioma.ESPANHOL, "La trama gira en torno al Anillo Único, forjado por el Señor Oscuro Sauron para dominar a todos los pueblos libres.");
			libro1.setPortada("lotr.webp");
			libroService.añadir(libro1);
			
			// Libro 2
			Libro libro2 = new Libro(null, "Harry Potter y la piedra filosofal", 1997, fantasia, "J.K. Rowling", Idioma.ESPANHOL, "Un niño huérfano que vive con sus tíos, hasta que, en su undécimo cumpleaños, recibe una carta de admisión al Colegio Hogwarts de Magia y Hechicería.");
			libro2.setPortada("harry.png");
			libroService.añadir(libro2);
			
			// Libro 3
			Libro libro3 = new Libro(null, "El nombre del viento", 2009, fantasia, "Patrick Rothfuss", Idioma.ESPANHOL, "Kvothe, un hombre legendario que todos creían muerto, vive en una posada apartada bajo el nombre de Kote.");
			libro3.setPortada("viento.webp");
			libroService.añadir(libro3);
			
			// Libro 4
			Libro libro4 = new Libro(null, "Las calles del parque del pánico", 2014, terror, "R.L. Stine", Idioma.ESPANHOL, "Niños atrapados en HorrorLandia creen que el Parque del Pánico es su única salida, pero descubren que es un lugar aún más peligroso.");
			libro4.setPortada("horrorland.webp");
			libroService.añadir(libro4);
			
			// Libro 5
			Libro libro5 = new Libro(null, "Orgullo y prejuicio", 2014, romance, "Jane Austen", Idioma.ESPANHOL, "Cinco hermanas en una familia de la clase media inglesa, cuya madre busca casarlas bien.");
			libro5.setPortada("prejuicio.webp");
			libroService.añadir(libro5);


			//Añadir usuarios ---
			Usuario usu1 = new Usuario(null, "Panda", null);
			usuarioService.añadir(usu1);
			Usuario usu2 = new Usuario(null, "Kyoma", null);
			usuarioService.añadir(usu2);

			//Añadir valoraciones ---
			Valoracion val1 = new Valoracion(null, usu1, libro1, 10.0, "El mejor libro de fantasía de la historia");
			valoracionService.añadir(val1);
			Valoracion val2 = new Valoracion(null, usu1, libro4, 7.5, "Me ha gustado pero no me ha encantado");
			valoracionService.añadir(val2);
			Valoracion val3 = new Valoracion(null, usu2, libro1, 6.5, "Es bueno, pero soy un 100tifico y no sé leer muaaaahahaha");
			valoracionService.añadir(val3);
			Valoracion val4 = new Valoracion(null, usu2, libro2, 6.8, "Está bien");
			valoracionService.añadir(val4);
		};
	}

}
