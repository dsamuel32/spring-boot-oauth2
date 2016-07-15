/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oauth.builder;

import java.util.List;

/**
 *
 * @author Diego NOTE
 * @param <T>
 */
public class RespostaBuilder <T> {
    
    private final Resposta<T> resposta;
    
    private RespostaBuilder () {
        resposta = new Resposta<>();
    }
    public static RespostaBuilder getBuilder () {
        return new RespostaBuilder();
    }
    
    public Resposta <T> build() {
        return resposta;
    }
    
    public RespostaBuilder<T> setResultado(T resultado) {
        resposta.setResultado(resultado);
        return this;
    }
    
    public RespostaBuilder<T> setMensagens(String mensagem) {
        resposta.setMensagens(mensagem);
        return this;
    }
    
    public RespostaBuilder<T> setMensagens(List<String> mensagens) {
        resposta.setMensagens(mensagens);
        return this;
    }
    
    public RespostaBuilder<T> setErros(String erro) {
        resposta.setMensagens(erro);
        return this;
    }
    
    public RespostaBuilder<T> setErros(List<String> erros) {
        resposta.setErros(erros);
        return this;
    }
}
