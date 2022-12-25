package com.github.adalmando.vendas.domain.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {


    @RequestMapping(
            value = {"/welcome/{nome}", "/bemvindo/{nome}"}, // Nesse request, posso passar 2 rotas para o mesmo caminho
            method = RequestMethod.GET)  // sempre tenho que definir o method do método request.
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente){
        return String.format("Welcome, " + nomeCliente);
    }
}
