package com.zakangroth.restservicesdemo.repository;

import com.zakangroth.restservicesdemo.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class IngredientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List getAll() {
        Session session = getEntityManager().unwrap(Session.class);
        Query query = session.createQuery("from Ingredient");
        return query.getResultList();
    }

    public Ingredient getById(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Query query = session.createQuery("from Ingredient where id = :id");
        query.setParameter("id", id);
        Ingredient ingredient = new Ingredient();

        try {
            ingredient = (Ingredient) query.getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            // Real catching to do to handle. Not found.
        }

        return ingredient;
    }

    public void create(String name) {
        Session session = getEntityManager().unwrap(Session.class);
        Ingredient ingredient = new Ingredient(name);
        session.persist(ingredient);
    }

    public void update(Ingredient ingredient) {
        Session session = getEntityManager().unwrap(Session.class);
        session.update(ingredient);
    }

    public void delete(Ingredient ingredient) {
        Session session = getEntityManager().unwrap(Session.class);
        Ingredient ingredientInDB = session.get(Ingredient.class, ingredient.getId());
        session.remove(ingredientInDB);
    }

    public void deleteById(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Ingredient ingredientInDB = session.get(Ingredient.class, id);
        session.remove(ingredientInDB);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
