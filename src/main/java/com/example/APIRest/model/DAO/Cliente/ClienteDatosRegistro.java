package com.example.APIRest.model.DAO.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDatosRegistro(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 0, max = 9)
        String telefono,

        @Pattern(regexp = "\\d{8}")
        @NotBlank
        String documento

) {
}
