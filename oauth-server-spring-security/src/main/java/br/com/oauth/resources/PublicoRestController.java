/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oauth.resources;

import br.com.oauth.builder.Resposta;
import br.com.oauth.builder.RespostaBuilder;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego NOTE
 */
@Api(value = "publico")
@RestController
@RequestMapping(value = "publico", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicoRestController {
    
    @ApiOperation(value = "Exibe que usuario não precisa estar logado")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resposta<String>> getHome() {
        Resposta<String> resposta = RespostaBuilder.getBuilder()
//                .setErros(Arrays.asList("Erro 1", "Erro 2"))
//                .setMensagens(Arrays.asList("Mensagem 1", "Mensagem 2"))
                .setResultado("Você não precisa estar logado").build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
    
    class Teste {
        private String resposta;
        
        public String getResposta() {
            return this.resposta;
        }
        
        public void setResposta(String resposta) {
            this.resposta = resposta;
        }
    }
    
}
