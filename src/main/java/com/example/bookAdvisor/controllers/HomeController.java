package com.example.bookAdvisor.controllers;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // anotación controlador

@RequestMapping("/public") //el resto del proyecto parte de /public (NO SÉ SI ES NECESARIO, SIGO LA ESTRUCTURA DE LA FOTO DEL EJEMPLO)

public class HomeController {
    
    @GetMapping({"", "/", "/home"})
    public String showHome(
        @RequestParam(required = false, defaultValue = "") String userName,
        Model model) {
        
        model.addAttribute("userName", userName);
        
        LocalDate date = LocalDate.now();
        model.addAttribute("currentDate", date.getYear());

        return "indexView";
    }


    @GetMapping("/quienes-somos")
    public String quienesSomos() {
        // proceso
        return "quienesSomos";
    }
    
}