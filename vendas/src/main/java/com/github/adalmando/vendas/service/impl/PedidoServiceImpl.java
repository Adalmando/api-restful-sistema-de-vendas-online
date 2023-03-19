package com.github.adalmando.vendas.service.impl;

import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.entity.ItemPedido;
import com.github.adalmando.vendas.domain.entity.Pedido;
import com.github.adalmando.vendas.domain.entity.Produto;
import com.github.adalmando.vendas.domain.enums.StatusPedido;
import com.github.adalmando.vendas.domain.repository.ClienteRepository;
import com.github.adalmando.vendas.domain.repository.ItemRepository;
import com.github.adalmando.vendas.domain.repository.PedidoRepository;
import com.github.adalmando.vendas.domain.repository.ProdutoRepository;
import com.github.adalmando.vendas.exception.PedidoNaoEncontradoException;
import com.github.adalmando.vendas.exception.RegraNegocioException;
import com.github.adalmando.vendas.rest.dto.ItemPedidoDTO;
import com.github.adalmando.vendas.rest.dto.PedidoDTO;
import com.github.adalmando.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
        public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private ItemRepository itemRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ClienteRepository clienteRepository,
                             ProdutoRepository produtoRepository,
                             ItemRepository itemRepository)
    {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    @Transactional // garante que, ou tudo é salvo com sucesso ou se um der errado, tudo é cancelado!
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException(
                        "Código de cliente inválido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        itemRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);

        pedidoRepository.save(pedido);
        return pedido;
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

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id){
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository
                .findById(id)
                .map(pedido -> {pedido.setStatus(statusPedido);
                return pedidoRepository.save(pedido);})
                .orElseThrow( () -> new PedidoNaoEncontradoException());
    }
}
