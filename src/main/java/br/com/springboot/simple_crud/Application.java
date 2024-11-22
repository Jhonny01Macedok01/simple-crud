package br.com.springboot.simple_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Spring Boot application starter class
 */
//@EntityScan(basePackages = "bbr.com.springboot.simple_crud.model")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
