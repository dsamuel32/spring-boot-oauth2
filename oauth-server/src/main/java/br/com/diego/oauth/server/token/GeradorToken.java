/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.token;

/**
 *
 * @author Diego NOTE
 */
public interface GeradorToken {
    
    public final static String AUTHORIZATION = "Authorization";
    
    public final static String	BEARER = "Bearer ";
    
    public final static String CLAIMS = "claims";
    
    public final static String GRUNT_TYPE = "grunt_type";
    
    public final static String ROLES = "roles";        
    
    public final static String SCOPE = "scope";
    
    public final static String SECRET_KEY = "secretkey";
    
    public final static String USER_NAME = "user_name";
    
    public String gerarToken();
    
}