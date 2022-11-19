package com.github.adalmando.vendas.domain.repository;

import com.github.adalmando.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
