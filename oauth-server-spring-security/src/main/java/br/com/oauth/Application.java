package br.com.oauth;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableSwagger
@SpringBootApplication
public class Application {

    @Autowired
    private SpringSwaggerConfig swaggerConfig;

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

    @Bean
    public SwaggerSpringMvcPlugin groupOnePlugin() {
        return new SwaggerSpringMvcPlugin(swaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns("/publico.*?", "/home.*?", "/users.*?")
                .swaggerGroup("publico");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Swagger com Spring Boot",
                "Simples demostração Swagger e Spring Boot",
                "Uso livre",
                "diego.samuel.alves@gmail.com",
                "Licença pública",
                "diego.samuel.alves@gmail.com"
        );
        return apiInfo;
    }
}
