package com.example.APIRest.model.Entidades;
import java.time.LocalDate;
import com.example.APIRest.model.Entidades.Roles.TrabajadorRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "trabajadores")
@Entity(name = "trabajador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String dni;
    private boolean activo;
    private LocalDate fecha_pago;

    @Enumerated(EnumType.STRING)
    private TrabajadorRoles rol;
}
