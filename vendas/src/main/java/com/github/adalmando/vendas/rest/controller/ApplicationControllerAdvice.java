package com.github.adalmando.vendas.rest.controller;

import com.github.adalmando.vendas.exception.RegraNegocioException;
import com.github.adalmando.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // essa anotation tras a responseBody e o controller advice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // codigo de status 400
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
}
