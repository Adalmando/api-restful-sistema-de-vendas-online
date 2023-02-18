package com.github.adalmando.vendas.service.impl;

import com.github.adalmando.vendas.domain.repository.PedidoRepository;
import com.github.adalmando.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;



}
