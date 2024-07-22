package com.example.APIRest.model.DAO.Entrenador;

import com.example.APIRest.model.Entidades.Cliente;
import com.example.APIRest.model.Entidades.Entrenador;

import java.time.LocalDate;
import java.util.List;

public record EntrenadorListado(String nombre, Boolean activo, String rol_entrenador, LocalDate fecha_pago, List<Cliente> clientes) {

    public EntrenadorListado(Entrenador entrenador){
        this(entrenador.getNombre(), entrenador.isActivo(), String.valueOf(entrenador.getRol_entrenador()),entrenador.getFecha_pago(), entrenador.getClientes());
    }
}
