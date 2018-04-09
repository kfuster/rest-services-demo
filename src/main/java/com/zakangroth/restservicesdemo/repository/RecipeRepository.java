package com.zakangroth.restservicesdemo.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RecipeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List getAllRecipes() {
        Session session = getEntityManager().unwrap(Session.class);
        Query query = session.createQuery("from Recipe");
        return query.getResultList();
    }

    public List getRecipeById(Long id){
        Session session = getEntityManager().unwrap(Session.class);
        Query query = session.createQuery("from Recipe where id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
