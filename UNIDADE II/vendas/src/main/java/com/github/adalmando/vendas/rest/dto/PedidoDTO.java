package com.github.adalmando.vendas.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
