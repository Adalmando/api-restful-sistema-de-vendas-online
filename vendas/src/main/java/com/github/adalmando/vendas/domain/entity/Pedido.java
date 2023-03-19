package com.github.adalmando.vendas.domain.entity;

import com.github.adalmando.vendas.domain.enums.StatusPedido;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING) // diz que no banco será guardado em formato string esse enum
    @Column(name = "status_pedido")
    private StatusPedido status;

    @OneToMany( mappedBy = "pedido")
    private List<ItemPedido> itens;

}
