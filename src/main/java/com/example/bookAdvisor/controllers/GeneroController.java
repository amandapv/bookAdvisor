package com.example.bookAdvisor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookAdvisor.domain.Genero;
import com.example.bookAdvisor.services.GeneroService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/public/generos")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;

    private String txtMsg;

    @GetMapping({"", "/"})
    public String showListGeneros(Model model) {
        model.addAttribute("listaGeneros", generoService.obtenerTodos());
        if (txtMsg != null) {
            model.addAttribute("msg", txtMsg);
            txtMsg = null;
        }
        return "genero/generoListView"; 
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        try {
            Genero genero = generoService.obtenerPorId(id);
            model.addAttribute("generoForm", genero);
            model.addAttribute("listaGeneros", generoService.obtenerTodos());
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/generos/";
        }
        return "genero/editFormGenero";
    }
    
    @PostMapping("/editar/{id}/submit")
    public String showEditSubmit(@PathVariable long id, @Valid @ModelAttribute("generoForm") Genero generoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genero/editFormGenero";
        }
        try {
            generoService.editar(generoForm);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/generos/";
        }
        return "redirect:/public/generos/";
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        try {
            generoService.borrar(id);
            txtMsg = "Operación realizada con éxito";
        } catch (Exception e) {
            txtMsg = e.getMessage();
            return "redirect:/public/generos/";
        }
        return "redirect:/public/generos/";
    }
}
