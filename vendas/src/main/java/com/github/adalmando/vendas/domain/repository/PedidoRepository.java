package com.github.adalmando.vendas.domain.repository;

import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PedidoRepository extends JpaRepository <Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);

    //Metodo que retorna um Optional de pedido com a lista completa dos itens desse pedido (fetch)
    Optional<Pedido> findByIdFetchItens(Integer id);
}
