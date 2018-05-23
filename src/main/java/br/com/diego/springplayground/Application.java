package br.com.diego.springplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
//https://github.com/savicprvoslav/Spring-Boot-starter
// http://www.baeldung.com/rest-api-spring-oauth2-angularjs
}
