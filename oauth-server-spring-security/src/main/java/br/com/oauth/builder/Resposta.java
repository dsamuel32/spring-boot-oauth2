/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oauth.builder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego NOTE
 * @param <T>
 */
public class Resposta <T> {
    
    private T resultado;
    
    private List<String> erros;
    
    private List<String> mensagens;

    public Resposta() {
        mensagens = new ArrayList<>();
        erros = new ArrayList<>();
    }
    
    public T getResultado() {
        return resultado;
    }

    public void setResultado(T resultado) {
        this.resultado = resultado;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErro(String erro) {
        this.erros.add(erro);
    }
    
    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(String mensagem) {
        this.mensagens.add(mensagem);
    }
    
    public void setMensagens(List<String> mensagens) {
        this.mensagens = mensagens;
    }

}
