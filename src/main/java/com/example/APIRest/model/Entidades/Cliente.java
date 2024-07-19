package com.example.APIRest.model.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "clientes")
@Entity(name = "cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String dni;
    private boolean activo;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;
}
