package com.example.bookAdvisor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.LibroDTO;
import com.example.bookAdvisor.services.GeneroService;
import com.example.bookAdvisor.services.LibroService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller

@RequestMapping("/public/libros") //el resto del proyecto parte de /public (NO SÉ SI ES NECESARIO, SIGO LA ESTRUCTURA DE LA FOTO DEL EJEMPLO)


public class LibroController {
    
    @Autowired
    private LibroService libroService;

    @Autowired
    private GeneroService generoService;

    private String txtMsg;

    @GetMapping({"", "/"})
    public String showListLibros(Model model) {
        List<Libro> listaLibros = libroService.obtenerTodos();
        model.addAttribute("listaLibros", libroService.convertLibroToDto(listaLibros)); //le paso los datos del DTO de libro
        model.addAttribute("libroForm", new Libro()); //hay que añadirle el libro porque en el archivo libroListView lo requiere para el filtro de la busqueda
        model.addAttribute("listaGeneros",generoService.obtenerTodos());
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
            // List<Libro> listaLibros = libroService.obtenerTodos(); //obtengo la lista de libros para convertirlos a DTO y poder usar la variable puntuacionMedia
            Double puntuacionMedia;
            if (libro.getCantidadVotantes() == 0) {
                puntuacionMedia = 0.0;
            } else {
                puntuacionMedia = libro.getSumaPuntos() / libro.getCantidadVotantes();
            }
            model.addAttribute("puntuacionMedia", puntuacionMedia);
            model.addAttribute("libro", libro);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/libros/";
        }
        return "libro/bookView";
    }

    @GetMapping("/nuevo")
    public String showNewLibro(Model model) {
        model.addAttribute("libroForm", new Libro());
        model.addAttribute("listaGeneros",generoService.obtenerTodos());
        return "libro/newLibroView";
    }

    @PostMapping("/nuevo/submit")
    public String showNewSubmit(@Valid @ModelAttribute("libroForm") Libro libroForm, BindingResult bindingResul, @RequestParam("portadaFichero") MultipartFile fichero, Model model) {
        if (bindingResul.hasErrors()) {
            // model.addAttribute("libroForm", libroForm); // No hace falta añadir al model si el nombre coincide con @ModelAttribute
            model.addAttribute("listaGeneros",generoService.obtenerTodos()); //si al validar el formulario, tengo que volver a enviarle los datos del género para que los tenga
            return "/libro/newLibroView";
        }
        try {
            String nombreImagen = libroService.añadirPortadaLibro(fichero);
            // Le ponemos el nombre resultante al objeto libro
            libroForm.setPortada(nombreImagen);
            libroService.añadir(libroForm);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/libros/";
        }
        return "redirect:/public/libros/"; //no retorno a libro/newLibroView" porque no quiero mostrar esa vista, ya que esto es el post, es decir, una vez que le de a crear el libro, me enviará a la vista de todos los libros
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            Libro libro = libroService.obtenerPorId(id);
            model.addAttribute("libroForm", libro);
            model.addAttribute("listaGeneros",generoService.obtenerTodos());
            model.addAttribute("generoSeleccionado", libro.getGenero().getNombre());
        } catch (Exception e) {
            txtMsg= e.getMessage();
            return "redirect:/public/libros/";
        }
        return "libro/editFormBook";
    }

    @PostMapping("/editar/{id}/submit")
    public String showEditSubmit(@PathVariable long id, @Valid @ModelAttribute("libroForm") Libro libroForm, BindingResult bindingResul) {
        if (bindingResul.hasErrors()) {
            // model.addAttribute("libroForm", libroForm); // No hace falta añadir al model si el nombre coincide con @ModelAttribute
            return "libro/editFormBook";
        }
        try {
            libroService.editar(libroForm);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/libros/";
        }
        return "redirect:/public/libros/";
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        try {
            libroService.borrar(id);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/libros/";
        }
        return "redirect:/public/libros";
    }



    @PostMapping("/findByTitulo")
    public String showFindByTematica(@ModelAttribute("libroForm") Libro libro, Model model) {
        List<Libro> listaLibros = libroService.buscarPorTituloLibro(libro.getTitulo()); 
        model.addAttribute("listaLibros", libroService.convertLibroToDto(listaLibros));
        return "libro/bookListView";
    }

    @GetMapping("/findByGenero/{genero}")
    public String showFindByGenero(@PathVariable Genero genero, Model model) {
        List<Libro> listaLibros = libroService.buscarPorGeneroLibro(genero);
        model.addAttribute("listaLibros", libroService.convertLibroToDto(listaLibros));
        model.addAttribute("listaGeneros",generoService.obtenerTodos());
        model.addAttribute("generoSeleccionado", genero.getNombre());
        model.addAttribute("libroForm", new Libro());
        return "libro/bookListView";
    }

}
