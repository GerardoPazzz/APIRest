package com.example.APIRest.controller;

import com.example.APIRest.model.DAO.Cliente.ClienteListar;
import com.example.APIRest.model.DAO.Cliente.ClienteDatosRegistro;
import com.example.APIRest.model.Repositorios.ClienteRepositorio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepositorio repositorio;

    @GetMapping
    @Operation(summary = "Obtiene el listado de clientes")
    public ResponseEntity<Page<ClienteListar>> listadoCliente(@PageableDefault(size = 5) Pageable paginacion){
        var page = repositorio.findByActivoTrue(paginacion).map(ClienteListar::new);
        return ResponseEntity.ok(page);
    }

//    @PostMapping
//    @Operation(summary = "Registra nuevos clientes")
//    public ResponseEntity registrarCliente(@RequestBody @Valid ClienteDatosRegistro datos, UriComponentsBuilder uriBuilder){
//
//    }
}
