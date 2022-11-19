package com.github.adalmando.vendas.domain.repository;

import com.github.adalmando.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository <Pedido, Integer> {
}
