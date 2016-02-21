/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.resources;

import br.com.diego.oauth.server.dtos.RequestTokenDTO;
import br.com.diego.oauth.server.dtos.TokenDTO;
import br.com.diego.oauth.server.service.UsuarioService;
import br.com.diego.oauth.server.token.Token;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import br.com.diego.oauth.server.exceptions.AutenticacaoException;
import br.com.diego.oauth.server.exceptions.ClienteIdException;
import br.com.diego.oauth.server.service.SistemaService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ValidationException;
import javax.ws.rs.GET;

/**
 *
 * @author Diego NOTE
 */
@RestController
@Path("api/autorizacao")
@Produces(MediaType.APPLICATION_JSON)
public class AutorizacaoResources {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private SistemaService sistemaService;

    @Path("request-token")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestToken(RequestTokenDTO requestTokenDTO) {
        
        try {
            sistemaService.verificarClienteIdValido(requestTokenDTO.getClientId());
            usuarioService.autenticar(requestTokenDTO);
            Token token = Token.getInstance();
            TokenDTO tokenDTO = 
                    new TokenDTO(token.gerarNormalToken(requestTokenDTO.getUserName(), requestTokenDTO.getGrantType(), new ArrayList<>(), requestTokenDTO.getScope()), 
                                 token.gerarRefreshToken(requestTokenDTO.getUserName(),requestTokenDTO.getGrantType(), new ArrayList<>(), requestTokenDTO.getScope()));
            return Response.ok(tokenDTO).build();
        } catch (AutenticacaoException | ClienteIdException e) {
            Logger.getLogger(AutorizacaoResources.class.getName()).log(Level.SEVERE, null, e);
            return Response.ok().status(Response.Status.UNAUTHORIZED).build();
        }

    }

    @Path("refresh-token")
    @POST
    public Response refreshToken(TokenDTO tokenDTO) {
        try {
            Token token = Token.getInstance();
            return Response.ok(token.revalidarToken(tokenDTO)).build();
        } catch (ValidationException e) {
            return Response.ok().status(Response.Status.UNAUTHORIZED).build();
        }
        
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response teste() {
        return Response.ok("funciona").build();
    }

}
