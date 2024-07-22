package com.example.APIRest.model.DAO.Trabajador;

import com.example.APIRest.model.Entidades.Trabajador;

import java.time.LocalDate;

public record TrabajadorListado (String nombre, String telefono, String rol_trabajador, LocalDate fecha_pago){

    public TrabajadorListado(Trabajador trabajador){
        this(trabajador.getNombre(), trabajador.getTelefono(), String.valueOf(trabajador.getRol()), trabajador.getFecha_pago());
    }
}
