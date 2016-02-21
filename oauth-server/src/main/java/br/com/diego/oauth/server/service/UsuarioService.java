/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.service;

import br.com.diego.oauth.server.dtos.RequestTokenDTO;
import br.com.diego.oauth.server.exceptions.AutenticacaoException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego NOTE
 */
@Service
public class UsuarioService {

    public Boolean autenticar(RequestTokenDTO requestTokenDTO) throws AutenticacaoException {
        if (Boolean.TRUE) {
            return Boolean.TRUE;
        } else {
            throw new AutenticacaoException("Usuário ou senha invalidos");
        }
    }

}
