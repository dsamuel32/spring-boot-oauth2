/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.exceptions;

/**
 *
 * @author Diego NOTE
 */
public class ClienteIdException extends Exception {

    public ClienteIdException() {
        super("Cliente Id inválido.");
    }

    public ClienteIdException(String message) {
        super(message);
    }
    
}
