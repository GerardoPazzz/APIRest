package com.example.APIRest.model.Entidades;

import com.example.APIRest.model.DAO.Cliente.ClienteDatosActualizados;
import com.example.APIRest.model.DAO.Cliente.ClienteDatosRegistro;
import com.example.APIRest.model.DAO.Cliente.ClienteListado;
import com.example.APIRest.model.DAO.Cliente.ClientePlanes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "clientes")
@Entity(name = "cliente")
@Getter
@Setter
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


    public Cliente(ClienteDatosRegistro datos){
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.dni = datos.dni();
        this.activo = true;
        this.fechaInicio = datos.fechaInicio();
        this.determinarFechaFinal(datos.clientePlan());
    }

    public void actualizarCliente(ClienteDatosActualizados datos){
        this.setActivo(true);
        if(datos.nombre()!= null){
            this.nombre = datos.nombre();
        }
        if(datos.email()!= null){
            this.email = datos.email();
        }
        if(datos.telefono()!= null){
            this.telefono = datos.telefono();
        }
        if(datos.dni()!= null){
            this.dni = datos.dni();
        }
        if(datos.fechaInicio()!= null){
            this.nombre = datos.nombre();
        }
        if(datos.clientePlan()!= null){
            this.determinarFechaFinal(datos.clientePlan());
        }
    }

    private void determinarFechaFinal(ClientePlanes plan){
        if(plan.name().equalsIgnoreCase("MENSUAL")){
            this.fechaFinal = this.fechaInicio.plusMonths(1);
        }
        if(plan.name().equalsIgnoreCase("TRIMESTRAL")){
            this.fechaFinal = this.fechaInicio.plusMonths(3);
        }
        if(plan.name().equalsIgnoreCase("SEMESTRAL")){
            this.fechaFinal = this.fechaInicio.plusMonths(6);
        }
        if(plan.name().equalsIgnoreCase("ANUAL")){
            this.fechaFinal = this.fechaInicio.plusYears(1);
        }
    }
    public void desactivarCliente(){
        this.setActivo(false);
    }
}
