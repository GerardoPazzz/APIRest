package com.example.APIRest.controller;

import com.example.APIRest.model.DAO.Cliente.ClienteListado;
import com.example.APIRest.model.Repositorios.ClienteRepositorio;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepositorio repositorio;

    @GetMapping
    @Operation(summary = "Obtiene el listado de clientes")
    public ResponseEntity<Page<ClienteListado>> listadoCliente(@PageableDefault(size = 10) Pageable paginacion){
        var page = repositorio.findByActivoTrue(paginacion).map(ClienteListado::new);
        return ResponseEntity.ok(page);
    }

//    @PostMapping
//    @Operation(summary = "Registra nuevos clientes")
//    public ResponseEntity registrarCliente(@RequestBody @Valid ClienteDatosRegistro datos, UriComponentsBuilder uriBuilder){
//
//    }
}
