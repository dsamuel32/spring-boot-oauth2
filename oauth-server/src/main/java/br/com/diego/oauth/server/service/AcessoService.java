/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.diego.oauth.server.service;

import br.com.diego.oauth.server.dtos.TokenDTO;
import br.com.diego.oauth.server.entidades.Acesso;
import br.com.diego.oauth.server.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author diego
 */
@Service
public class AcessoService {
    
    @Autowired
    private AcessoRepository acessoRepository;
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Acesso salvar(Acesso acesso) {
        return acessoRepository.salvar(acesso);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Acesso salvar(TokenDTO tokenDTO) {
        return salvar(new Acesso(tokenDTO.getToken(), tokenDTO.getRefreshToken()));
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Acesso atualizar(TokenDTO tokenDTO) {
        return acessoRepository.atualizar(new Acesso(tokenDTO.getToken(), tokenDTO.getRefreshToken()));
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(Acesso acesso) {
        acessoRepository.apagar(acesso);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(TokenDTO tokenDTO) {
        apagar(new Acesso(tokenDTO.getToken(), tokenDTO.getRefreshToken()));
    }
    
    public Boolean isRefreshTokenExiste(String refreshToken) {
        return acessoRepository.isRefreshTokenExite(refreshToken);
    }
    
}
