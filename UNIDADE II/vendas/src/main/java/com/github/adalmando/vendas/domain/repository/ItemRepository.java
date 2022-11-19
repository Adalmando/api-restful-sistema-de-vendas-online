package com.github.adalmando.vendas.domain.repository;

import com.github.adalmando.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemPedido, Integer> {
}
