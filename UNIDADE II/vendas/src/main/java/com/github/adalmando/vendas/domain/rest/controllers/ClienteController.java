package com.github.adalmando.vendas.domain.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @RequestMapping(value = "/")
    public String helloClientes(String nomeCliente){
        return String.format("Hello %s", nomeCliente);
    }
}



