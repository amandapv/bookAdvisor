package com.example.bookAdvisor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.bookAdvisor.domain.Libro;
import com.example.bookAdvisor.services.LibroService;

import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

@RequestMapping("/public") //el resto del proyecto parte de /public (NO SÉ SI ES NECESARIO, SIGO LA ESTRUCTURA DE LA FOTO DEL EJEMPLO)


public class LibroController {
    
    @Autowired
    private LibroService libroService;

    private String txtMsg;

    @GetMapping("/{id}")
    public String showLibro(@PathVariable Long id, Model model) {
        try {
            Libro libro = libroService.obtenerPorId(id);
            model.addAttribute("libro", libro);
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/";
        }
        return "list";
    }
    

}
