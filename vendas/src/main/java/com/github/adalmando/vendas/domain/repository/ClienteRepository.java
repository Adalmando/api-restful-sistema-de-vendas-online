package com.github.adalmando.vendas.domain.repository;

import com.github.adalmando.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByNomeLike(String nome);

    // Com o native query ativado eu digo que o script de busca vai rodar na linguagem do banco de dados que estou usando, nesse caso, H2
    @Query(value = " SELECT * FROM CLIENTE C WHERE C.NOME LIKE %:nome% ", nativeQuery = true)
    List<Cliente> buscarPorNomeLike(@Param("nome") String nome);


    //    Consulta sem o native query, com native está no método a cima, comentado.
    //    @Query(value = " SELECT C FROM Cliente C WHERE C.nome LIKE %:nome% ")
    //    List<Cliente> buscarPorNomeLike(@Param("nome") String nome);

    @Query(value = " delete from cliente c where c.nome = :nome", nativeQuery = true)
    @Modifying  // modifying pq modifica o banco de dados.
    @Transactional // transactional pois altera o banco de dados
    void deleteByNome (@Param("nome") String nome);

    @Query( " select c from Cliente c left join fetch c.pedidos where c.id = :id " )
    Cliente findClienteFetchPedidos(@Param("id") Integer id );

}
