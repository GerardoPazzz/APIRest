package com.example.APIRest.model.DAO.Cliente;

import com.example.APIRest.model.Entidades.Cliente;

import java.time.LocalDate;

public record ClienteListar(String nombre, Boolean activo, LocalDate fechaInicio, LocalDate fechaFinal) {

    public ClienteListar (Cliente cliente){
        this(cliente.getNombre(), cliente.isActivo(), cliente.getFechaInicio(), cliente.getFechaFinal());
    }
}
