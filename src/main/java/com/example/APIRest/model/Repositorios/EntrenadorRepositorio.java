package com.example.APIRest.model.Repositorios;

import com.example.APIRest.model.Entidades.Entrenador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepositorio extends JpaRepository<Entrenador,Long> {
    Page<Entrenador> findByActivoTrue(Pageable pageable);
}
