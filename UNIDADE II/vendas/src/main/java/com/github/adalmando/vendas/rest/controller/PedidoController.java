package com.github.adalmando.vendas.rest.controller;

import com.github.adalmando.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

}
