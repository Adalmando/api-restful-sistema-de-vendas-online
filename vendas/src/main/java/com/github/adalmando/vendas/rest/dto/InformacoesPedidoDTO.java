package com.github.adalmando.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;
    private String nomeCliente;
    private String cpf;
    private BigDecimal totalPedido;
    private List<InformacaoItemPedidoDTO> itens;
    private String dataPedido;

}
