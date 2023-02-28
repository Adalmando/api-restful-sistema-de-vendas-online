package com.github.adalmando.vendas.rest.controller;

import com.github.adalmando.vendas.exception.RegraNegocioException;
import com.github.adalmando.vendas.rest.ApiErrors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){

    }

}
