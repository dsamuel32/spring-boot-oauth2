/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.repository;

import br.com.diego.oauth.server.entidades.Sistema;
import br.com.diego.oauth.server.entidades.Usuario;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego NOTE
 */
@Repository
public class UsuarioRepository extends GenericRepository<Usuario> {

    @Autowired
    private SistemaRepository sistemaRepository;
    
    public Boolean isAtenticarUsuario(String userName, String password, String clientId) {
        Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.userName =:userName AND u.password =:password");
        query.setParameter("userName", userName);
        query.setParameter("password", password);

        List<Usuario> usuarios = query.getResultList();

        if (usuarios.isEmpty()) {
            return Boolean.FALSE;
        } else {
            return verificarUsuarioPermissaoAcessoSistema(usuarios, clientId);
        }

    }

    private Boolean verificarUsuarioPermissaoAcessoSistema(List<Usuario> usuarios, String clientId) {
        Boolean isTemPermissaoAcesso = Boolean.FALSE;

        Sistema sistema = sistemaRepository.buscarPorClietSecret(clientId);

        for (Usuario usuario : usuarios) {
            if (usuario.getSistemas().contains(sistema)) {
                isTemPermissaoAcesso = Boolean.TRUE;
                break;
            }
        }
        return isTemPermissaoAcesso;
    }

}
