package com.example.APIRest.Service;

import com.example.APIRest.model.DAO.Cliente.ClienteDatosActualizados;
import com.example.APIRest.model.DAO.Cliente.ClienteDatosRegistro;
import com.example.APIRest.model.DAO.Cliente.ClienteDatosRespuesta;
import com.example.APIRest.model.DAO.Cliente.ClienteListado;
import com.example.APIRest.model.Entidades.Cliente;
import com.example.APIRest.model.Entidades.Entrenador;
import com.example.APIRest.model.Repositorios.ClienteRepositorio;
import com.example.APIRest.model.Repositorios.EntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    private final ClienteRepositorio clienteRepository;
    private final EntrenadorRepositorio entrenadorRepository;

    @Autowired
    public ClienteService(ClienteRepositorio clienteRepository, EntrenadorRepositorio entrenadorRepository) {
        this.clienteRepository = clienteRepository;
        this.entrenadorRepository = entrenadorRepository;
    }

    public Page<ClienteListado> obtenerListadoClientes(Pageable paginacion) {
        return clienteRepository.findByActivoTrue(paginacion).map(ClienteListado::new);
    }

    public ClienteDatosRespuesta obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        if (!cliente.isActivo()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no activo");
        }
        return new ClienteDatosRespuesta(cliente.getId(), cliente.getNombre(), cliente.getDni(),
                cliente.getFechaInicio(), cliente.getFechaFinal());
    }
    @Transactional
    public ClienteDatosRespuesta registrarCliente(ClienteDatosRegistro datos) {
        Cliente cliente = clienteRepository.save(new Cliente(datos));
        return new ClienteDatosRespuesta(cliente.getId(), cliente.getNombre(), cliente.getDni(),
                cliente.getFechaInicio(), cliente.getFechaFinal());
    }
    @Transactional
    public ClienteDatosRespuesta actualizarCliente(ClienteDatosActualizados datos) {
        Cliente cliente = clienteRepository.getReferenceById(datos.id());
        cliente.actualizarCliente(datos);
        return new ClienteDatosRespuesta(cliente.getId(), cliente.getNombre(), cliente.getDni(),
                cliente.getFechaInicio(), cliente.getFechaFinal());
    }
    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.desactivarCliente();
    }
    public Cliente asignarEntrenador(Long clienteId, Long entrenadorId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Entrenador entrenador = entrenadorRepository.findById(entrenadorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no encontrado"));

        if (!entrenador.isActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El entrenador no está activo");
        }

        if (entrenador.getClientes().size() >= 7) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El entrenador ya tiene 7 clientes asignados");
        }

        cliente.setEntrenador(entrenador);
        return clienteRepository.save(cliente);
    }
}
