package io.github.adalmando.configurations;

import io.github.adalmando.anotations.Production;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Production
public class ConfigurationTwo {
    @Bean
    public CommandLineRunner exec(){
        return args -> {
            System.out.println("Rodando configuração de produção!");
        };
    }
}
