package com.example.APIRest.model.DAO.Entrenador;

import com.example.APIRest.model.Entidades.Roles.EntrenadorRoles;

public record EntrenadorDatosRespuesta (Long id,
                                        String nombre,
                                        String dni,
                                        EntrenadorRoles rol){
}
