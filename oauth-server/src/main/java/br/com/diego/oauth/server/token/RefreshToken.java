/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego NOTE
 */
public class RefreshToken implements  GeradorToken {
    
    private String userName;
    
    private String tipoToken;
    
    private List<String> roles;
    
    private String scope;

    public RefreshToken(String userName, String tipoToken, List<String> roles, String scope) {
        this.userName = userName;
        this.tipoToken = tipoToken;
        this.roles = roles;
        this.scope = scope;
    }

    @Override
    public String gerarToken() {
        Date dataAtual = new Date();
        
        String token = Jwts.builder()
                .setSubject(tipoToken)
                .claim(GeradorToken.USER_NAME, userName)
                .claim(GeradorToken.SCOPE, scope)
                .claim(GeradorToken.ROLES, roles).setIssuedAt(dataAtual)
                .signWith(SignatureAlgorithm.HS256, GeradorToken.SECRET_KEY)
                .compact();

        return token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }
    
}
