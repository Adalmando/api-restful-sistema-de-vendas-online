package com.github.adalmando.vendas.rest.controller;

import com.github.adalmando.vendas.domain.entity.ItemPedido;
import com.github.adalmando.vendas.domain.entity.Pedido;
import com.github.adalmando.vendas.rest.dto.InformacaoItemPedidoDTO;
import com.github.adalmando.vendas.rest.dto.InformacoesPedidoDTO;
import com.github.adalmando.vendas.rest.dto.PedidoDTO;
import com.github.adalmando.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    // EndPoint que cadastra um pedido e salva no banco de dados
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save (@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    // EndPoint que busca o pedido pelo id e retorna o pedido completo com a lista de itens do pedido.
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
        if(CollectionUtils.isEmpty(itens)){ // Se a lista que ta sendo passada como parametro estiver vazia:
            return Collections.emptyList(); // A gente retorna uma lista vazia para não retornar um objeto null
        }
        return itens.stream().map( item -> InformacaoItemPedidoDTO
                .builder()
                .descricao(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build())
                .collect(Collectors.toList()); // transforma o return em uma list.
    }
}






