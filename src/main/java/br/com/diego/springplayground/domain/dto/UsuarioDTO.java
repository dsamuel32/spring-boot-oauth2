package br.com.diego.springplayground.domain.dto;

import java.io.Serializable;
import java.util.Set;

public class UsuarioDTO implements Serializable {

    private String nome;
    private String userName;
    private Boolean enabled;
    private Set<DominioDTO> permissoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<DominioDTO> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<DominioDTO> permissoes) {
        this.permissoes = permissoes;
    }
}
