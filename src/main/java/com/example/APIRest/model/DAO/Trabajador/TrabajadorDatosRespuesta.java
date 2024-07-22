package com.example.APIRest.model.DAO.Trabajador;


import com.example.APIRest.model.Entidades.Roles.TrabajadorRoles;

public record TrabajadorDatosRespuesta(Long id,
                                       String nombre,
                                       String dni,
                                       TrabajadorRoles rol_trabajador) {
}
