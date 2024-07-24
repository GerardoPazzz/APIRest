package com.example.APIRest.model.Repositorios;

import com.example.APIRest.model.Entidades.Entrenador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrenadorRepositorio extends JpaRepository<Entrenador,Long> {
    Page<Entrenador> findByActivoTrue(Pageable pageable);

    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.entrenador.id = :entrenadorId AND c.activo = true")
    Long countClientesByEntrenadorId(@Param("entrenadorId") Long entrenadorId);
}
