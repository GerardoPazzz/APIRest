package com.example.APIRest.model.DAO.Entrenador;

import com.example.APIRest.model.Entidades.Roles.EntrenadorRoles;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EntrenadorDatosActualizados(
        @NotNull
        Long id,
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
        String nombre,

        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
        String dni,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
        String telefono,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        String email,

        @NotNull(message = "El rol de entrenador es obligatorio")
        EntrenadorRoles rol,

        @NotNull(message = "La fecha del último pago es obligatoria")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate ultimoPago
        ) {
}
