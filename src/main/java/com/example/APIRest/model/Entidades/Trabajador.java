package com.example.APIRest.model.Entidades;
import java.time.LocalDate;

import com.example.APIRest.model.DAO.Trabajador.TrabajadorDatosActualizados;
import com.example.APIRest.model.DAO.Trabajador.TrabajadorDatosRegistro;
import com.example.APIRest.model.Entidades.Roles.TrabajadorRoles;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "trabajadores")
@Entity(name = "trabajador")
@Setter
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

    public Trabajador(TrabajadorDatosRegistro trabajadorDatosRegistro){
        this.nombre = trabajadorDatosRegistro.nombre();
        this.email = trabajadorDatosRegistro.email();
        this.telefono = trabajadorDatosRegistro.telefono();
        this.dni = trabajadorDatosRegistro.dni();
        this.activo = true;
        this.fecha_pago = trabajadorDatosRegistro.ultimoPago();
        this.rol = trabajadorDatosRegistro.rol_trabajador();
    }

    public void actualizarTrabajador(TrabajadorDatosActualizados datos) {
        if (datos.nombre() != null) {
            this.setNombre(datos.nombre());
        }
        if (datos.dni() != null) {
            this.setDni(datos.dni());
        }
        if (datos.telefono() != null) {
            this.setTelefono(datos.telefono());
        }
        if (datos.email() != null) {
            this.setEmail(datos.email());
        }
        if (datos.rol_trabajador() != null) {
            this.setRol(datos.rol_trabajador());
        }
        if (datos.ultimoPago() != null) {
            this.setFecha_pago(datos.ultimoPago());
        }
    }
    public void desactivarTrabajador(){
        this.setActivo(false);
    }
    public void activarTrabajador(){
        this.setActivo(true);
    }
}
