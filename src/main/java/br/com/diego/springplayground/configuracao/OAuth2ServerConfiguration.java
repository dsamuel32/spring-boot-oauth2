package br.com.diego.springplayground.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "restservice";
}
