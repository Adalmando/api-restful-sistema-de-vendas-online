package com.github.adalmando.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String descricao;


    @Column(name = "preco_unitario", precision = 20, scale = 2)
    private BigDecimal preco;
    
    public Produto (String descricao, BigDecimal preco){
        this.setDescricao(descricao);
        this.setPreco(preco);
    }

}
