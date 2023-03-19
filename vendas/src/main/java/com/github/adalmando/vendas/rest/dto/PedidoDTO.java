package com.github.adalmando.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente que realizará a compra!")
    private Integer cliente;

    @NotNull(message = "Informe o total do pedido!")
    private BigDecimal total;

    private List<ItemPedidoDTO> itens;
}
