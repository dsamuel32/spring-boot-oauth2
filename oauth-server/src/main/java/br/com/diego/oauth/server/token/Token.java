/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.token;

import br.com.diego.oauth.server.dtos.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.util.List;
import javax.validation.ValidationException;

/**
 *
 * @author Diego NOTE
 */
public class Token {

    private GeradorToken normalToken;
    private GeradorToken refeshToken;

    private static Token instance;

    private Token() {
    }

    public static synchronized Token getInstance() {
        if (instance == null) {
            instance = new Token();
        }

        return instance;
    }

    public String gerarRefreshToken(String userName, String tipoToken, List<String> roles, String scope) {
        if (refeshToken == null) {
            refeshToken = new RefreshToken(userName, tipoToken, roles, scope);
        }

        return refeshToken.gerarToken();
    }

    public String gerarNormalToken(String userName, String tipoToken, List<String> roles, String scope) {
        if (normalToken == null) {
            normalToken = new NormalToken(userName, tipoToken, roles, scope);
        }

        return normalToken.gerarToken();
    }

    public Claims verificarTokenValido(String token) {
        Claims c = Jwts.parser().setSigningKey(GeradorToken.SECRET_KEY).parseClaimsJws(token).getBody();
        return Jwts.parser().setSigningKey(GeradorToken.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public TokenDTO revalidarToken(TokenDTO tokenDTO) {
        Token token = Token.getInstance();

        try {
            final Claims claims = token.verificarTokenValido(tokenDTO.getToken());
            return criaTokenDTO(claims);
        } catch (SignatureException e) {
            throw new ValidationException("Token invalido.");
        } catch (ExpiredJwtException e) {
            final Claims claims = token.verificarTokenValido(tokenDTO.getRefreshToken());
            return criaTokenDTO(claims);
        }
    }

    private TokenDTO criaTokenDTO(Claims claims) {
        String userName = (String) claims.get(GeradorToken.USER_NAME);
        String grantType = (String) claims.get(GeradorToken.GRUNT_TYPE);
        List<String> roles = (List<String>) claims.get(GeradorToken.ROLES);
        String scope = (String) claims.get(GeradorToken.SCOPE);
        
        String novoToken = this.gerarNormalToken(userName, grantType, roles, scope);
        String novoRefreshToken = this.gerarRefreshToken(userName, grantType, roles, scope);

        return new TokenDTO(novoToken, novoRefreshToken);
    }

}
