package com.github.adalmando.vendas;

import com.github.adalmando.vendas.domain.entity.Cliente;
import com.github.adalmando.vendas.domain.entity.Pedido;
import com.github.adalmando.vendas.domain.repository.ClienteRepository;
import com.github.adalmando.vendas.domain.repository.ItemRepository;
import com.github.adalmando.vendas.domain.repository.PedidoRepository;
import com.github.adalmando.vendas.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clientes,
		@Autowired ProdutoRepository produtos,
		@Autowired PedidoRepository pedidos,
		@Autowired ItemRepository itens){
		return args -> {

			System.out.println("Salvando cliente..");
			clientes.save(new Cliente("Adalmando", "28385939483"));

			System.out.println("\nCadastrando pedido...\n");
			Pedido p = new Pedido();
			p.setCliente(clientes.findById(1));
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(120));
			pedidos.save(p);

			System.out.println("\nClientes salvos:\n");
			List<Cliente> todosClientes = clientes.findAll();
			System.out.println(todosClientes);

//			Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//			System.out.println(cliente);
//			System.out.println(cliente.getPedidos());


		};
	}
}
