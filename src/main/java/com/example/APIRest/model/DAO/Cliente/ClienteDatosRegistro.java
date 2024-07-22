package com.example.APIRest.model.DAO.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDatosRegistro(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
        String nombre,
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
        String telefono,

        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
        String dni

) {
}
