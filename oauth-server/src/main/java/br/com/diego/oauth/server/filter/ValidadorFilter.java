package br.com.diego.oauth.server.filter;

import br.com.diego.oauth.server.token.GeradorToken;
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

public class ValidadorFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req,
            final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        final String authHeader = request.getHeader(GeradorToken.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(GeradorToken.BEARER)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        final String tokenHeader = authHeader.substring(7); // The part after "Bearer "

        try {
            Token token = Token.getInstance();
            final Claims claims = token.verificarTokenValido(tokenHeader);
            request.setAttribute(GeradorToken.CLAIMS, claims);
        } catch (SignatureException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new ServletException("Invalid token.");
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(req, res);
    }

}
