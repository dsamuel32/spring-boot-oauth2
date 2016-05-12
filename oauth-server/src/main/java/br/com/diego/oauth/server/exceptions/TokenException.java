/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.diego.oauth.server.exceptions;

/**
 *
 * @author diego
 */
public class TokenException extends RuntimeException {

    public TokenException() {
        super("Token não existe.");
    }
    
    public TokenException(String mensagem) {
        super(mensagem);
    }
 
    
    
}
