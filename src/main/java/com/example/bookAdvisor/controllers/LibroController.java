package com.example.bookAdvisor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.services.LibroService;

import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

@RequestMapping("/public/libros") //el resto del proyecto parte de /public (NO SÉ SI ES NECESARIO, SIGO LA ESTRUCTURA DE LA FOTO DEL EJEMPLO)


public class LibroController {
    
    @Autowired
    private LibroService libroService;

    private String txtMsg;

    @GetMapping({"", "/"})
    public String showListLibros(Model model ) {
        model.addAttribute("listaLibros", libroService.obtenerTodos());
        model.addAttribute("libroForm", new Libro()); //hay que añadirle el libro porque en el archivo libroListView lo requiere para el filtro de la busqueda
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "libro/bookListView";
    }
    

    @GetMapping("/{id}")
    public String showElementLibro(@PathVariable Long id, Model model) {
        try {
            Libro libro = libroService.obtenerPorId(id);
            model.addAttribute("libro", libro);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/";
        }
        return "libroView";
    }

    @GetMapping("/nuevo")
    public String showNewLibro(Model model) {
        model.addAttribute("libroForm", new Libro());
        return "newLibroView";
    }

    @PostMapping("/findByTitulo")
    public String showFindByTematica(@ModelAttribute("libroForm") Libro libro, Model model) {
        model.addAttribute("listaLibros", libroService.buscarPorTituloLibro(libro.getTitulo()));
        return "libro/bookListView";
    }

    @GetMapping("/findByGenero/{genero}")
    public String showFindByGenero(@PathVariable Genero genero, Model model) {
        model.addAttribute("listaLibros", libroService.buscarPorGeneroLibro(genero));
        model.addAttribute("generoSeleccionado", genero);
        model.addAttribute("libroForm", new Libro());
        return "libro/bookListView";
    }


}
