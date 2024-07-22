package com.example.APIRest.model.DAO.Cliente;

import com.example.APIRest.model.Entidades.Cliente;

import java.time.LocalDate;

public record ClienteListado(String nombre, Boolean activo, LocalDate fechaInicio, LocalDate fechaFinal) {

    public ClienteListado(Cliente cliente){
        this(cliente.getNombre(), cliente.isActivo(), cliente.getFechaInicio(), cliente.getFechaFinal());
    }
}
