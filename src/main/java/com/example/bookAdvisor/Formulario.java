package com.example.bookAdvisor;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Formulario {
    @NotEmpty(message = "Introduzca el nombre")
    private String nombre;
    @NotEmpty(message = "Introduzca el email")
    @Email(message = "Formmato no válido")
    private String email;
    // @NotEmpty(message = "Seleccione por qué quiere contactar")
    private TipoContactoEnum tipoContacto;
    private String comentario;
    @AssertTrue(message = "Debe aceptar las condiciones")
    private boolean condiciones;
}

