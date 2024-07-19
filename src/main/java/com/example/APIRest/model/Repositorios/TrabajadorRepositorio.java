package com.example.APIRest.model.Repositorios;

import com.example.APIRest.model.Entidades.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRepositorio extends JpaRepository<Trabajador, Long> {
}
