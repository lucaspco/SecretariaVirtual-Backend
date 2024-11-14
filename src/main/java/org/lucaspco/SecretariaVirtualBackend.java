package org.lucaspco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "org.lucaspco")
public class SecretariaVirtualBackend {
    public static void main(String[] args) {
        SpringApplication.run(SecretariaVirtualBackend.class, args);
        System.out.println("Backend rodando na porta 8080");
    }
}
