package com.example.APIRest.model.DAO.Cliente;

import com.example.APIRest.model.Entidades.Cliente;

import java.time.LocalDate;

public record ClienteDatosRespuesta(Long id,
                                    String nombre,
                                    String dni,
                                    LocalDate fecha_inicio,
                                    LocalDate fecha_fin
                               ) {
}
