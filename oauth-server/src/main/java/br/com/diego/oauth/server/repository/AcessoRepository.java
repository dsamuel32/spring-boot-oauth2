/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.diego.oauth.server.repository;

import br.com.diego.oauth.server.entidades.Acesso;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public class AcessoRepository extends GenericRepository<Acesso> {
    
    public Boolean isRefreshTokenExite(String refreshToken) {
        Query query = entityManager.createQuery("SELECT a FROM Acesso a WHERE a.refreshToken =:refreshToken");
        query.setParameter("refreshToken", refreshToken);
        return !query.getResultList().isEmpty();
    }
    
    public Acesso buscarPorRefreshToken(String refreshToken) {
        Query query = entityManager.createQuery("SELECT a FROM Acesso a WHERE a.refreshToken =:refreshToken");
        query.setParameter("refreshToken", refreshToken);
        try {
            return (Acesso) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getInstance(AcessoRepository.class).info("TOKEN NÃO EXISTE");
            return new Acesso();
        }
    }
    
    public Acesso buscarPorToken(String token) {
        Query query = entityManager.createQuery("SELECT a FROM Acesso a WHERE a.token =:token");
        query.setParameter("token", token);
        try {
            return (Acesso) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getInstance(AcessoRepository.class).info("TOKEN NÃO EXISTE");
            return new Acesso();
        }
    }
}
