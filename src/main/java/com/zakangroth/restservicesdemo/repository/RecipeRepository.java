package com.zakangroth.restservicesdemo.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RecipeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List getRecipes() {
        Session session = getEntityManager().unwrap(Session.class);
        return session.createQuery("from Recipe").list();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
