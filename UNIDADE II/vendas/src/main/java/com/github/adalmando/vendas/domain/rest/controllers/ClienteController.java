package com.github.adalmando.vendas.domain.rest.controllers;

import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private ClienteRepository clienteRepository;
    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id) // com o pathvariable eu digo que o {id}
    {                                                                       // da URL é o mesmo id dos parametros passados.
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get() );
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity save(Cliente cliente){
        
    }
}
