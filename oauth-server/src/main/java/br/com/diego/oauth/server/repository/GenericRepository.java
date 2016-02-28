/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Diego NOTE
 * @param <T>
 */
@Repository
@Transactional(readOnly = true)
public class GenericRepository<T> {
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    public T buscarPorCodigo(Long codigo) {
        return (T) entityManager.find(getTypeClass(), codigo);
    }
 
    public T atualizar(T entity) {
        return entityManager.merge(entity);
    }
    
    public T salvar(T entity) {
        entityManager.persist(entity);
        return entity;
    }
 
    public void apagar(T entity) {
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
    }
 
    public List<T> buscarTodos() {
        return entityManager.createQuery(("SELECT c FROM " + getTypeClass().getName() + " c "))
                .getResultList();
    }
 
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }
}
