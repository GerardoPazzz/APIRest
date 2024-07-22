package com.example.APIRest.model.Entidades;

import com.example.APIRest.model.DAO.Entrenador.EntrenadorDatosActualizados;
import com.example.APIRest.model.DAO.Entrenador.EntrenadorDatosRegistro;
import com.example.APIRest.model.DAO.Trabajador.TrabajadorDatosRegistro;
import com.example.APIRest.model.Entidades.Roles.EntrenadorRoles;
import com.example.APIRest.model.Entidades.Roles.TrabajadorRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Table(name = "entrenadores")
@Entity(name = "entrenador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador extends Trabajador{

    @Enumerated(EnumType.STRING)
    private EntrenadorRoles rol_entrenador;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Size(max = 7)
    private List<Cliente> clientes;

    public Entrenador(EntrenadorDatosRegistro datos) {
        super(new TrabajadorDatosRegistro(
                datos.nombre(),
                datos.dni(),
                datos.telefono(),
                datos.email(),
                TrabajadorRoles.ENTRENADOR,
                datos.ultimoPago()
        ));
        this.rol_entrenador = datos.rol();
    }
    public void actualizarEntrenador(EntrenadorDatosActualizados datos){
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
        if (datos.rol() != null) {
            this.setRol_entrenador(datos.rol());
        }
        if (datos.ultimoPago() != null) {
            this.setFecha_pago(datos.ultimoPago());
        }
    }
    public void desactivarEntrenador(){
        this.setActivo(false);
    }
}
