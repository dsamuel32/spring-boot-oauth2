/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.diego.oauth.server.dtos;

import java.io.Serializable;

/**
 *
 * @author diego
 */
public class ErroDTO implements Serializable {
    
    private String mesagem;

    public ErroDTO() {
    }

    public ErroDTO(String mesagem) {
        this.mesagem = mesagem;
    }

    public String getMesagem() {
        return mesagem;
    }

    public void setMesagem(String mesagem) {
        this.mesagem = mesagem;
    }
    
}
