package com.github.adalmando.vendas.domain.repository;

import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PedidoRepository extends JpaRepository <Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);

    //Metodo que retorna um Optional de pedido com a lista completa dos itens desse pedido (fetch)
    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
