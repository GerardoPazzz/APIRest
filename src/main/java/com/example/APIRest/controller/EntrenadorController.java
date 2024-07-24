package com.example.APIRest.controller;

import com.example.APIRest.model.DAO.Entrenador.EntrenadorDatosActualizados;
import com.example.APIRest.model.DAO.Entrenador.EntrenadorDatosRegistro;
import com.example.APIRest.model.DAO.Entrenador.EntrenadorDatosRespuesta;
import com.example.APIRest.model.DAO.Entrenador.EntrenadorListado;
import com.example.APIRest.model.Entidades.Entrenador;
import com.example.APIRest.model.Repositorios.EntrenadorRepositorio;
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
@RequestMapping("/entrenadores")
public class EntrenadorController {

    @Autowired
    EntrenadorRepositorio repositorio;


    @GetMapping
    @Operation(summary = "Listando a los entrenadores disponibles")
    public ResponseEntity<Page<EntrenadorListado>> listandoEntrenadores(@PageableDefault(size = 5) Pageable paginacion){
        var page = repositorio.findByActivoTrue(paginacion).map(EntrenadorListado::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listando a entrenador por Id")
    public ResponseEntity<EntrenadorDatosRespuesta> listandoEntrenadorPorId(@PathVariable Long id){
        Entrenador entrenador = repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no encontrado"));
        if (!entrenador.isActivo()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no activo");
        }
        var datosEntrenador = new EntrenadorDatosRespuesta(entrenador.getId(), entrenador.getNombre(),
                entrenador.getDni(), entrenador.getRol_entrenador());

        return ResponseEntity.ok(datosEntrenador);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Registrando nuevo entrenador")
    public ResponseEntity registrarEntrenador(@RequestBody @Valid EntrenadorDatosRegistro datos,
                                              UriComponentsBuilder uriComponentsBuilderBuilder){
        Entrenador entrenador = repositorio.save(new Entrenador(datos));
        EntrenadorDatosRespuesta entrenadorDatosRespuesta = new EntrenadorDatosRespuesta(entrenador.getId(), entrenador.getNombre(),
                entrenador.getDni(),entrenador.getRol_entrenador());

        URI url = uriComponentsBuilderBuilder.path("/entrenadores/{id}").buildAndExpand(entrenador.getId()).toUri();
        return ResponseEntity.created(url).body(entrenadorDatosRespuesta);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un entrenador existente")
    public ResponseEntity actualizarEntrenador(@RequestBody @Valid EntrenadorDatosActualizados datos){
        Entrenador entrenador = repositorio.getReferenceById(datos.id());
        entrenador.actualizarEntrenador(datos);
        return ResponseEntity.ok(new EntrenadorDatosRespuesta(entrenador.getId(),entrenador.getNombre(),
                entrenador.getDni(),entrenador.getRol_entrenador()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un entrenador registrado - inactivo")
    public ResponseEntity eliminarEntrenador(@PathVariable Long id) {
        Entrenador entrenador = repositorio.getReferenceById(id);
        entrenador.desactivarEntrenador();
        return ResponseEntity.noContent().build();
    }
}
