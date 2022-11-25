package com.github.adalmando.vendas.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @OneToMany( mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Cliente(String nome, String cpf) {
        this.setNome(nome);
        this.setCpf(cpf);
    }
    public Cliente(String nome) {
        this.setNome(nome);
        this.setCpf("");
    }

    public Cliente() {
        this.setNome("");
        this.setCpf("");
    }

    public String toString(){
        return "Cliente: id: " + this.getId() + " nome: " + this.getNome() + " cpf: " + this.getCpf();
    }
}