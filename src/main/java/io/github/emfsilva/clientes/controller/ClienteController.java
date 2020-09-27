package io.github.emfsilva.clientes.controller;

import io.github.emfsilva.clientes.model.entity.Cliente;
import io.github.emfsilva.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
