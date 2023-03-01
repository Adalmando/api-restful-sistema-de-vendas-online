package com.github.adalmando.vendas.rest.controller;

import com.github.adalmando.vendas.domain.entity.ItemPedido;
import com.github.adalmando.vendas.domain.entity.Pedido;
import com.github.adalmando.vendas.rest.dto.InformacaoItemPedidoDTO;
import com.github.adalmando.vendas.rest.dto.PedidoDTO;
import com.github.adalmando.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.github.adalmando.vendas.rest.dto.InformacoesPedidoDTO;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save (@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable  Integer id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Pedido não encontrado!"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .totalPedido(pedido.getTotal())
                .itens(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter (List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens))
            return Collections.emptyList(); // para retornar uma lista vazia e n um objeto null
        return itens.stream().map( itemPedido -> InformacaoItemPedidoDTO
                .builder()
                .descricao(itemPedido.getProduto().getDescricao())
                .precoUnitario(itemPedido.getProduto().getPreco())
                .quantidade(itemPedido.getQuantidade())
                .build();
    }

}






