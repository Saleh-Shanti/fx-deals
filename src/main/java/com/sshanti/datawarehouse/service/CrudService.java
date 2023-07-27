package com.sshanti.datawarehouse.service;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Dependent
@Transactional(Transactional.TxType.MANDATORY)
public class CrudService<T> {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public T create(T entity) {
        this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.refresh(entity);
        return entity;
    }

    public T getById(Class<T> obj,Long id) {
        return entityManager.find(obj, id);
    }

}
