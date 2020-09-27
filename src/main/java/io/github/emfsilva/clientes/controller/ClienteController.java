package io.github.emfsilva.clientes.controller;

import ch.qos.logback.core.net.server.Client;
import io.github.emfsilva.clientes.model.entity.Cliente;
import io.github.emfsilva.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Integer id) {
        clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente( @PathVariable Integer id, @RequestBody Cliente clienteAtualizado ){
        clienteRepository.findById(id).map(cliente -> {
            clienteAtualizado.setId(cliente.getId());
          return clienteRepository.save(clienteAtualizado);

        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
