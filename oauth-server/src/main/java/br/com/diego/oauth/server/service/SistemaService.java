/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.service;

import br.com.diego.oauth.server.exceptions.ClienteIdException;
import br.com.diego.oauth.server.repository.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego NOTE
 */
@Service
public class SistemaService {
    
    @Autowired
    private SistemaRepository sistemaRepository;

    public void verificarClienteIdValido(String clientID) throws ClienteIdException {
        if (!sistemaRepository.isClientIdValido(clientID)) {
            throw new ClienteIdException("Cliente Id inválido.");
        } 
    }

}
