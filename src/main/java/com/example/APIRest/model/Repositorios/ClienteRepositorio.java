package com.example.APIRest.model.Repositorios;

import com.example.APIRest.model.Entidades.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Page<Cliente> findByActivoTrue(Pageable pageable);
}
