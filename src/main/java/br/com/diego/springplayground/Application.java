package br.com.diego.springplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
        //new SpringApplicationBuilder(Application.class).web(true).run(args);
        /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("123");
        System.out.println(result);*/
    }
//https://github.com/savicprvoslav/Spring-Boot-starter
// http://www.baeldung.com/rest-api-spring-oauth2-angularjs
}
