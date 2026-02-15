package com.example.bookAdvisor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.domain.Valoracion;
import com.example.bookAdvisor.services.LibroService;
import com.example.bookAdvisor.services.UsuarioService;
import com.example.bookAdvisor.services.ValoracionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/public/valoraciones")
public class ValoracionController {
    
    @Autowired
    private ValoracionService valoracionService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private UsuarioService usuarioService;

    private String txtMsg;

    @GetMapping("/{idLibro}")
    public String showListValoraciones(@PathVariable long idLibro, Model model) {
        Libro libro = libroService.obtenerPorId(idLibro);
        List<Valoracion> listaValoraciones = valoracionService.buscarValoraciones(idLibro); //me traigo las valoraciones de un libro
        model.addAttribute("listaValoraciones", listaValoraciones);
        model.addAttribute("libro", libro);
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "valoracion/valoracionListView";
    }

    @GetMapping("/nuevo/{idLibro}")
    public String showNewValoracion(@PathVariable long idLibro, Model model) {
        Libro libro = libroService.obtenerPorId(idLibro);
        model.addAttribute("valoracionForm", new Valoracion());
        model.addAttribute("libro", libro);
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodos());
        return "valoracion/newValoracion";
    } 

    @PostMapping("/nuevo/{idLibro}/submit")
    public String showNewSubmit(@PathVariable long idLibro, @Valid @ModelAttribute("valoracionForm") Valoracion valoracionForm, BindingResult bindingResul, Model model) {
        if (bindingResul.hasErrors()) {
            //si me da error en el envío de formulario por las validaciones de este hay que ENVIAR de nuevo los datos que estoy intentando pintar en la vista
            Libro libro = libroService.obtenerPorId(idLibro); //obtengo el libro actual para poder decirle a la valoración a qué libro hace referencia
            model.addAttribute("libro", libro);
            model.addAttribute("listaUsuarios", usuarioService.obtenerTodos());
            return "valoracion/newValoracion";
        }
        try {
            Libro libro = libroService.obtenerPorId(idLibro); //obtengo el libro actual para poder decirle a la valoración a qué libro hace referencia
            valoracionForm.setLibro(libro); //una vez obtenido, setteo su libro al actual ya rellenado (si no me diría null porque aún no le he dicho a cuál le estoy añadiendo una valoración)
            //me aseguro de que el USUARIO está completo. Si el form solo envió el ID, lo volvemos a buscar para que Hibernate tenga el objeto completo con su "nombre"
            if (valoracionForm.getUsuario() != null) {
                Long idUsuario = valoracionForm.getUsuario().getId();
                valoracionForm.setUsuario(usuarioService.obtenerPorId(idUsuario));
            }
            valoracionService.añadir(valoracionForm);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/valoraciones/" + idLibro;
        }
        return "redirect:/public/valoraciones/" + idLibro;
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        long idLibro;
        try {
            Valoracion valoracion = valoracionService.obtenerPorId(id);
            idLibro = valoracion.getLibro().getId();
            valoracionService.borrar(valoracion);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/libros/";
        }
        return "redirect:/public/valoraciones/" + idLibro;
    }
}
