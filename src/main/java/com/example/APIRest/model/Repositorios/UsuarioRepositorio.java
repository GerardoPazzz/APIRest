package com.example.APIRest.model.Repositorios;

import com.example.APIRest.model.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
}
