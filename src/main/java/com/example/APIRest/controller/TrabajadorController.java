package com.example.APIRest.controller;

import com.example.APIRest.model.DAO.Trabajador.TrabajadorDatosActualizados;
import com.example.APIRest.model.DAO.Trabajador.TrabajadorDatosRegistro;
import com.example.APIRest.model.DAO.Trabajador.TrabajadorDatosRespuesta;
import com.example.APIRest.model.DAO.Trabajador.TrabajadorListado;
import com.example.APIRest.model.Entidades.Roles.TrabajadorRoles;
import com.example.APIRest.model.Entidades.Trabajador;
import com.example.APIRest.model.Repositorios.TrabajadorRepositorio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/trabajadores")
public class TrabajadorController {
    @Autowired
    TrabajadorRepositorio repositorio;

    @GetMapping
    @Operation(summary = "Listando a los trabajadores disponibles")
    public ResponseEntity<Page<TrabajadorListado>> listandoTrabajadores(@PageableDefault(size = 5) Pageable paginacion){
        var page = repositorio.findByActivoTrue(paginacion).map(TrabajadorListado::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Listando a trabajador por Id")
    public ResponseEntity<TrabajadorDatosRespuesta> listandoTrabajadorPorId(@PathVariable Long id){
        Trabajador trabajador = repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trabajador no encontrado"));
        if (!trabajador.isActivo()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trabajador no activo");
        }
        var datosTrabajador = new TrabajadorDatosRespuesta(trabajador.getId(), trabajador.getNombre(),
                trabajador.getDni(), trabajador.getRol());

        return ResponseEntity.ok(datosTrabajador);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Registrando nuevo trabajador")
    public ResponseEntity registrarTrabajador(@RequestBody @Valid TrabajadorDatosRegistro datos,
                                              UriComponentsBuilder uriComponentsBuilderBuilder){
        if (datos.rol_trabajador() == TrabajadorRoles.ENTRENADOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol de entrenador no es válido para este endpoint.");
        }
        Trabajador trabajador = repositorio.save(new Trabajador(datos));
        TrabajadorDatosRespuesta trabajadorDatosRespuesta = new TrabajadorDatosRespuesta(trabajador.getId(), trabajador.getNombre(),
                trabajador.getDni(),trabajador.getRol());

        URI url = uriComponentsBuilderBuilder.path("/trabajador/{id}").buildAndExpand(trabajador.getId()).toUri();
        return ResponseEntity.created(url).body(datos);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un trabajador existente")
    public ResponseEntity actualizarTrabajador(@RequestBody @Valid TrabajadorDatosActualizados datos){
        if (datos.rol_trabajador() == TrabajadorRoles.ENTRENADOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol de entrenador no es válido para este endpoint.");
        }
        Trabajador trabajador = repositorio.getReferenceById(datos.id());
        if (trabajador.getRol() == TrabajadorRoles.ENTRENADOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pueden actualizar los datos de un entrenador en este endpoint.");
        }

        trabajador.actualizarTrabajador(datos);
        return ResponseEntity.ok(new TrabajadorDatosRespuesta(trabajador.getId(),trabajador.getNombre(),
                trabajador.getDni(),trabajador.getRol()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un trabajador registrado - inactivo")
    public ResponseEntity eliminarTrabajador(@PathVariable Long id) {
        Trabajador trabajador = repositorio.getReferenceById(id);
        if (trabajador.getRol() == TrabajadorRoles.ENTRENADOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol de entrenador no es válido para este endpoint.");
        }
        trabajador.desactivarTrabajador();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    @Transactional
    @Operation(summary = "Activando a un trabajador registrado")
    public ResponseEntity activarTrabajar(@PathVariable Long id){
        Trabajador trabajador = repositorio.getReferenceById(id);
        if (trabajador.isActivo()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El trabajador ya está activo.");
        }
        trabajador.activarTrabajador();
        return ResponseEntity.ok("Trabajador activado correctamente !");
    }
}
