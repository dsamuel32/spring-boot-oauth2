/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.dtos;

import java.io.Serializable;

/**
 *
 * @author Diego NOTE
 */
public class RequestTokenDTO implements Serializable {
    //{"userName" : "teste@teste.com", "password": "646514d85279c63c7aacbc7a91e7575bd2d1adcb0086c45840242b090c69cc08", "clientId": "646514d85279c63c7aacbc7a91e7575bd2d1adcb0086c45840242b090c69cc08", "grantType": "password", "scope" :"API"}
    private String userName;
    
    private String password;
    
    private String clientId;
    
    private String grantType;
    
    private String scope;
    
    private String clientSecret;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
}
