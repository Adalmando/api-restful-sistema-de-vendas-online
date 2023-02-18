package com.github.adalmando.vendas.rest.controller;

import com.github.adalmando.vendas.domain.entity.Produto;
import com.github.adalmando.vendas.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("{id}")
    public Produto getById (@PathVariable Integer id){
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save (@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id){
        produtoRepository.findById(id).
                map(produto -> {produtoRepository.delete(produto);
                return produto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "nenhum produto com esse nome"));
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable Integer id, @RequestBody Produto produto){
        produtoRepository.findById(id)
                .map(produtoEncontrado -> {produto.setId(produtoEncontrado.getId());
                    produtoRepository.save(produto);
                        return produtoEncontrado;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "produto não encontrado!"));
    }


    @GetMapping
    public List<Produto> findAllProdutos(){
        return produtoRepository.findAll();
    }

}
