package com.github.adalmando.vendas.service;

import com.github.adalmando.vendas.domain.entity.Pedido;
import com.github.adalmando.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
}
