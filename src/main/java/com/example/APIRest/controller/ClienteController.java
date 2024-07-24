package com.example.APIRest.controller;

import com.example.APIRest.Service.ClienteService;
import com.example.APIRest.model.DAO.Cliente.ClienteDatosActualizados;
import com.example.APIRest.model.DAO.Cliente.ClienteDatosRegistro;
import com.example.APIRest.model.DAO.Cliente.ClienteListado;
import com.example.APIRest.model.DAO.Cliente.ClienteDatosRespuesta;
import com.example.APIRest.model.Entidades.Cliente;
import com.example.APIRest.model.Repositorios.ClienteRepositorio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepositorio repositorio;

    @GetMapping
    @Operation(summary = "Obtiene el listado de clientes")
    public ResponseEntity<Page<ClienteListado>> listadoCliente(@PageableDefault(size = 10) Pageable paginacion) {
        var page = clienteService.obtenerListadoClientes(paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listando a cliente por Id")
    public ResponseEntity<ClienteDatosRespuesta> listandoClientePorId(@PathVariable Long id) {
        var datosCliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(datosCliente);
    }

    @PostMapping
    @Operation(summary = "Registra nuevos clientes")
    public ResponseEntity registrarCliente(@RequestBody @Valid ClienteDatosRegistro datos,
                                           UriComponentsBuilder uriComponentsBuilder) {
        var clienteRespuesta = clienteService.registrarCliente(datos);
        URI url = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(clienteRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(clienteRespuesta);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un cliente")
    public ResponseEntity actualizarCliente(@RequestBody @Valid ClienteDatosActualizados datos) {
        var clienteRespuesta = clienteService.actualizarCliente(datos);
        return ResponseEntity.ok(clienteRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un cliente registrado - inactivo")
    public ResponseEntity eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/asignar-entrenador")
    @Transactional
    public ResponseEntity<ClienteDatosRespuesta> asignarEntrenador(@RequestParam Long clienteId, @RequestParam Long entrenadorId) {
        Cliente cliente = clienteService.asignarEntrenador(clienteId, entrenadorId);
        return ResponseEntity.ok(new ClienteDatosRespuesta(cliente.getId(), cliente.getNombre(), cliente.getDni(),
                cliente.getFechaInicio(), cliente.getFechaFinal()));
    }
}
