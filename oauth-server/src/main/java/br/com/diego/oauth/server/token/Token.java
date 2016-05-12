/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.token;

import br.com.diego.oauth.server.dtos.TokenDTO;
import br.com.diego.oauth.server.exceptions.TokenException;
import br.com.diego.oauth.server.service.AcessoService;
import br.com.diego.oauth.server.utils.DataUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diego NOTE
 */
@Component
public class Token {

    private static final Long TEMPO_EXPIRAR = TimeUnit.MINUTES.toMillis(30L);

    public final static String AUTHORIZATION = "Authorization";

    public final static String BEARER = "Bearer ";

    public final static String CLAIMS = "claims";

    public final static String GRUNT_TYPE = "grunt_type";

    public final static String ROLES = "roles";

    public final static String SCOPE = "scope";

    public final static String SECRET_KEY = "secretkey";

    public final static String USER_NAME = "user_name";

    @Autowired
    private AcessoService acessoService;

    public String gerarRefreshToken(String userName, String tipoToken, List<String> roles, String scope) {
        return this.gerarToken(tipoToken, userName, scope, roles, TimeUnit.DAYS.toMillis(1L));
    }

    public String gerarNormalToken(String userName, String tipoToken, List<String> roles, String scope) {
        return this.gerarToken(tipoToken, userName, scope, roles, TEMPO_EXPIRAR);
    }

    public Claims verificarNormalTokenValido(String token) {
        if (acessoService.isTokenExiste(token)) {
            return  verificarTokenValido(token);
        } else {
            throw new TokenException();
        }
    }

    private Claims verificarTokenValido(String token) {
        Claims c = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public TokenDTO revalidarToken(TokenDTO tokenDTO) {
        try {
            final Claims claims = this.verificarTokenValido(tokenDTO.getRefreshToken());
            return criaTokenDTO(claims);
        } catch (SignatureException | ExpiredJwtException e) {
            throw new ValidationException("Token invalido.");
        }
    }

    private TokenDTO criaTokenDTO(Claims claims) {
        String userName = (String) claims.get(USER_NAME);
        String grantType = (String) claims.get(GRUNT_TYPE);
        List<String> roles = (List<String>) claims.get(ROLES);
        String scope = (String) claims.get(SCOPE);

        String novoToken = this.gerarNormalToken(userName, grantType, roles, scope);
        String novoRefreshToken = this.gerarRefreshToken(userName, grantType, roles, scope);

        return new TokenDTO(novoToken, novoRefreshToken);
    }

    public String getUserName(Claims claims) {
        return (String) claims.get(USER_NAME);
    }

    public String gerarToken(String tipoToken, String userName, String scope, List<String> roles, Long tempoExpirar) {

        Date dataAtual = new Date();
        Date dataExpirar = DataUtil.gerarTempoExpiracaoToken(dataAtual, tempoExpirar);

        String token = Jwts.builder()
                .setSubject(tipoToken)
                .claim(USER_NAME, userName)
                .claim(SCOPE, scope)
                .claim(ROLES, roles).setIssuedAt(dataAtual)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).setExpiration(dataExpirar)
                .compact();

        return token;
    }

}
