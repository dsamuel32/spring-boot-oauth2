/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.diego.oauth.server.batch;

import br.com.diego.oauth.server.entidades.Acesso;
import br.com.diego.oauth.server.exceptions.TokenException;
import br.com.diego.oauth.server.service.AcessoService;
import br.com.diego.oauth.server.token.Token;
import java.util.List;
import javax.validation.ValidationException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author diego
 */
@Component
public class AcessoBatchBean {
    
    @Autowired
    private AcessoService acessoService;
    
    @Autowired
    private Token token;
    
    @Scheduled(cron = "00 41 22 * * *")
    public void limparRefreshTokensExpirados() {
       List<Acesso> acessos = acessoService.findAll();
       for (Acesso acesso : acessos) {
           try {
              token.verificarRefreshTokenExpirou(acesso.getRefreshToken());
           } catch (ValidationException | TokenException e) {
               Logger.getInstance(AcessoBatchBean.class).info("Apagando token expirado.");
               acessoService.apagar(acesso);
           }
       }
    }
}
