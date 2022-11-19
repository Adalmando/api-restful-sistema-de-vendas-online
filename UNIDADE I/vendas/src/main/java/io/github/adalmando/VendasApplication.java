package io.github.adalmando;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Gato
    private Animal animal;

    @Bean("Name = execut")
    public CommandLineRunner execut(){
        return args -> {
            this.animal.fazerBarulho();
        };
    }

    @Value("${application.name}")
    private String boasVindas;

    @GetMapping("/")
    private String boas_vindas() {
        return boasVindas;
    }

    @GetMapping("/mensagem")
    public String mensagem() {
        return "Opa!, você está na nossa página de mensagem '/mensagem'.";
    }
}
