package com.github.adalmando.vendas.domain.rest.controllers;

import ch.qos.logback.core.net.server.Client;
import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ClienteController {


    private ClienteRepository clienteRepository;
    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById( @PathVariable Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }
}



