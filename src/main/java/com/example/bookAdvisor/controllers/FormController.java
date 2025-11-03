package com.example.bookAdvisor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.bookAdvisor.Formulario;
import com.example.bookAdvisor.services.EmailService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FormController {

    @Autowired
    EmailService emailService;

    private String txtError;


    @GetMapping("/public/contacta")
    public String mostrarFormContacto(Model model) {

        if (txtError != null) {
            model.addAttribute("txtError", txtError);
            txtError = null; // vacía la variable para usarla de nuevo
        }

        model.addAttribute("form", new Formulario());
        return "formView";
    }



    @PostMapping("/public/contacta/submit")
    public String envioFormContacto(@Valid @ModelAttribute("form") Formulario form, BindingResult bindingResult, Model model) {
        
        // model.addAttribute("form", form); 

        if (bindingResult.hasErrors()) {
            return "formView";
        }

        try {
            String destinatario = form.getEmail();
            String asunto = "Formulario de contacto para: " + form.getNombre();
            String cuerpoMensaje = "Ha solicitado realizar: " + form.getTipoContacto() + ". Comentarios: " + form.getComentario();

            emailService.sendEmail(destinatario, cuerpoMensaje, asunto); //enviar el email - si no lo consigue se lanzará un MessagingException
        } catch (Exception e) {
            txtError = e.getMessage();
            return "redirect:/public/contacta";
        }
        
        model.addAttribute("formEnviado", "Formulario enviado correctamente");
        return "formView";
    }

}
