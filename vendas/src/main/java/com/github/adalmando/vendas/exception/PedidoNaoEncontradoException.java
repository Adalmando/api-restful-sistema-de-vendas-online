package com.github.adalmando.vendas.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado!");
    }
}
