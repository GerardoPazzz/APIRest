package com.example.APIRest.model.Entidades;

import com.example.APIRest.model.Entidades.Roles.EntrenadorRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name = "entrenadores")
@Entity(name = "entrenador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador extends Trabajador{

    @Enumerated(EnumType.STRING)
    private EntrenadorRoles rol_entrenador;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Size(max = 7)
    private List<Cliente> clientes;


}
