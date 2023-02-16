package com.github.adalmando.vendas.domain.rest.controllers;

import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    // Injeção de repositorio dentro do controller
    @Autowired
    private ClienteRepository clienteRepository;


    // Retorna um optional com o cliente, ou lança uma exceção de ResponseStatus:
    @GetMapping("/{id}")
    public Cliente getClienteById( @PathVariable Integer id){
        return clienteRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado (mensagem opcional)!"));
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Retorna o codigo 200 se a requisição ocorrer com sucesso!
    public Cliente save(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id){
        clienteRepository.findById(id)
                .map(cliente -> {clienteRepository.delete(cliente);
                return cliente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable Integer id, @RequestBody Cliente cliente ){
        clienteRepository.findById(id)
                .map(clienteExistente -> {cliente.setId(clienteExistente.getId());
                clienteRepository.save(cliente);
                return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "cliente não encontrado!"));
    }


    @GetMapping
    public List<Cliente> findAllClientes ( Cliente filtro){

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clienteRepository.findAll(example);

    }
}
