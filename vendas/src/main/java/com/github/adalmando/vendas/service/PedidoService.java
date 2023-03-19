package com.github.adalmando.vendas.service;

import com.github.adalmando.vendas.domain.entity.Pedido;
import com.github.adalmando.vendas.domain.enums.StatusPedido;
import com.github.adalmando.vendas.rest.dto.AtualizacaoStatusPedidoDTO;
import com.github.adalmando.vendas.rest.dto.PedidoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);

    @Transactional
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
