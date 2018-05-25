package br.com.diego.springplayground.configuracao;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

public class OAuth2AuthorizationServerConfigInMemory extends AuthorizationServerConfigurerAdapter {


    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.password}")
    private String senha;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String usuario;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {// @formatter:off
        clients
//				.jdbc(dataSource())
                .inMemory().withClient("sampleClientId").authorizedGrantTypes("implicit")
                .scopes("read", "write", "foo", "bar").autoApprove(false).accessTokenValiditySeconds(3600)

                .and().withClient("fooClientIdPassword").secret("secret")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("foo", "read", "write")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days

                .and().withClient("barClientIdPassword").secret("secret")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("bar", "read", "write")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days
        ;
    } // @formatter:on

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // @formatter:off
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
        endpoints.tokenStore(tokenStore())
                // .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager);
        // @formatter:on
    }

    /*
    @Bean
    public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    // converter.setSigningKey("123");
    final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
    return converter;
    }
    */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(usuario);
        dataSource.setPassword(senha);
        return dataSource;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

}
