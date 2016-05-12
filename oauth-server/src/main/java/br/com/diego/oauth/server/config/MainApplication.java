package br.com.diego.oauth.server.config;

import br.com.diego.oauth.server.filter.ValidadorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.diego")
@EnableConfigurationProperties
public class MainApplication {
    
    /**
    http://localhost:8080/oauth-server/api/privado/usuario
    http://localhost:8080/oauth-server/api/oauth/token  {"userName" : "teste@teste.com", "password": "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", "clientId": "Web", "clientSecret": "646514d85279c63c7aacbc7a91e7575bd2d1adcb0086c45840242b090c69cc08", "grantType": "password", "scope" :"API"}
    http://localhost:8080/oauth-server/api/oauth/refresh-token
    
    **/
    
    /*
    {
        "access_token":"2YotnFZFEjr1zCsicMWpAA",
        "token_type":“bearer",
        "expires_in":3600,
        "refresh_token":"tGzv3JOkF0XG5Qx2TlKWIA",
    }
    */
    
    @Autowired
    private ValidadorFilter filter;
    
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean 
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new ValidadorFilter());
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/api/privado/*");

        return registrationBean;
    }

	// https://spring.io/blog/2015/02/03/sso-with-oauth2-angular-js-and-spring-security-part-v
}
