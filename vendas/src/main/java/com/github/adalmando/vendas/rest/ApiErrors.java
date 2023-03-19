package com.github.adalmando.vendas.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors {

    @Getter // Para criar um get para cada erro da lista.
    private List<String> Errors;

    public ApiErrors(String mensagemErro){
        // A classe arrays tem o metodo as list que recebe um objeto e transforma em um arraylist
        this.Errors = Arrays.asList(mensagemErro);
    }

    public ApiErrors(List<String> errors) {
        this.Errors = errors;
    }
}
