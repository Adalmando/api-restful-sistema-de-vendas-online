package com.github.adalmando.vendas.rest.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class InformacaoItemPedidoDTO {
    private String descricao;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
