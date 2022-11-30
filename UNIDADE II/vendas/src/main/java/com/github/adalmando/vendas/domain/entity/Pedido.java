package com.github.adalmando.vendas.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total_pedido", precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany( mappedBy = "pedido")
    private List<ItemPedido> itens;

    public Pedido(){
        this.setCliente(null);
        this.setDataPedido(null);
        this.setTotal(null);
        this.setItens(null);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                '}';
    }

    //    public String toString(){
//        return "Pedido: id: " + this.getId() + " cliente: " +this.getCliente().getNome() + " data: " + this.getDataPedido() + " total: " + this.getTotal();
//    }

}
