package com.github.adalmando.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty(message = "A descrição não pode ser vazia!")
    @Column(length = 100)
    private String descricao;

    @NotNull(message = "O preço do produto não pode ser nulo!")
    @Column(name = "preco_unitario", precision = 20, scale = 2)
    private BigDecimal preco;
    
    public Produto (String descricao, BigDecimal preco){
        this.setDescricao(descricao);
        this.setPreco(preco);
    }

}
