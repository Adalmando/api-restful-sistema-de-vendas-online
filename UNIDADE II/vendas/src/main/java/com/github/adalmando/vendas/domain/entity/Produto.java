package com.github.adalmando.vendas.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "produto")
@ToString
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descricao;


    @Column(name = "preco_unitario", precision = 20, scale = 2)
    private BigDecimal preco;
    
    public Produto (String descricao, BigDecimal preco){
        this.setDescricao(descricao);
        this.setPreco(preco);
    }

}
