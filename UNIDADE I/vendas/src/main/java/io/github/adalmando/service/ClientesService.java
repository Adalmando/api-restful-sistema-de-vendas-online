package io.github.adalmando.service;

import io.github.adalmando.model.Cliente;
import io.github.adalmando.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.clientesRepository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        // validando cliente
    }

}
