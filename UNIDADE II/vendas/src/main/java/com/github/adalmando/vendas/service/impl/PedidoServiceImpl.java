package com.github.adalmando.vendas.service.impl;

import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.entity.ItemPedido;
import com.github.adalmando.vendas.domain.entity.Pedido;
import com.github.adalmando.vendas.domain.entity.Produto;
import com.github.adalmando.vendas.domain.repository.ClienteRepository;
import com.github.adalmando.vendas.domain.repository.ItemRepository;
import com.github.adalmando.vendas.domain.repository.PedidoRepository;
import com.github.adalmando.vendas.domain.repository.ProdutoRepository;
import com.github.adalmando.vendas.exception.RegraNegocioException;
import com.github.adalmando.vendas.rest.dto.ItemPedidoDTO;
import com.github.adalmando.vendas.rest.dto.PedidoDTO;
import com.github.adalmando.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;



    @Override
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException(
                        "Código de cliente inválido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidoRepository.save(pedido);
        itemRepository.saveAll(itensPedido);

        return null;
    }




    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){

        if(itens.isEmpty()){
            throw new RegraNegocioException("Não existem itens no pedido!");
        }

        return itens
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("código de Produto não encontrado: "+idProduto)
                            );

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
