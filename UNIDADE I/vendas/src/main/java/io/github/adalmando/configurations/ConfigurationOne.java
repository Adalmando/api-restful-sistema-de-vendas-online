package io.github.adalmando.configurations;

import io.github.adalmando.anotations.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class ConfigurationOne {

    @Bean
    public CommandLineRunner exec(){
        return args -> {
            System.out.println("Rodando configuração de desenvolvimento!");
        };
    }
}

