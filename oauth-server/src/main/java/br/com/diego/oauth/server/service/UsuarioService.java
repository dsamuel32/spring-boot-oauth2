/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.service;

import br.com.diego.oauth.server.dtos.RequestTokenDTO;
import br.com.diego.oauth.server.exceptions.AutenticacaoException;
import br.com.diego.oauth.server.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Diego NOTE
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public void autenticar(RequestTokenDTO requestTokenDTO) throws AutenticacaoException {
        if (!usuarioRepository.isAtenticarUsuario(requestTokenDTO.getUserName(), requestTokenDTO.getPassword(), requestTokenDTO.getClientId())) {
            throw new AutenticacaoException("Usuário ou senha invalidos");
        }
    }

}
