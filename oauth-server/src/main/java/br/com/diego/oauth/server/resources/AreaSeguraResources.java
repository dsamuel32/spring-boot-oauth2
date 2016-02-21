/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego NOTE
 */
@RestController
@Path("api/privado/usuario")
public class AreaSeguraResources {
    
    @GET
    public Response areaRestrita() {
        return Response.ok("Você está em área restrita.").build();
    }
    
}
