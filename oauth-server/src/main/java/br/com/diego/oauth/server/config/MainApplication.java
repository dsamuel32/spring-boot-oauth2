package br.com.diego.oauth.server.config;

import br.com.diego.oauth.server.filter.ValidadorFilter;
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

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new ValidadorFilter());
		registrationBean.addUrlPatterns("/api/security/*");

		return registrationBean;
	}

	// https://spring.io/blog/2015/02/03/sso-with-oauth2-angular-js-and-spring-security-part-v
}
