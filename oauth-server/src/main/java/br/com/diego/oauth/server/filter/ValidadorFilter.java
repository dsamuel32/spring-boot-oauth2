package br.com.diego.oauth.server.filter;

import br.com.diego.oauth.server.exceptions.TokenException;
import br.com.diego.oauth.server.token.Token;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorFilter extends GenericFilterBean {
    
    @Autowired
    private Token token;

    @Override
    public void doFilter(final ServletRequest req,
            final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        final String authHeader = request.getHeader(Token.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(Token.BEARER)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        final String tokenHeader = authHeader.substring(7); // The part after "Bearer "

        try {
            final Claims claims = token.verificarNormalTokenValido(tokenHeader);
            request.setAttribute(Token.CLAIMS, claims);
        } catch (SignatureException | TokenException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new ServletException(e.getMessage());
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } 

        chain.doFilter(req, res);
    }

}
