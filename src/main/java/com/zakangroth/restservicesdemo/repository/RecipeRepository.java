package com.zakangroth.restservicesdemo.repository;

import com.zakangroth.restservicesdemo.model.Recipe;
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

    public List getAll() {
        Session session = getEntityManager().unwrap(Session.class);
        Query query = session.createQuery("from Recipe");
        return query.getResultList();
    }

    public Recipe getById(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Query query = session.createQuery("from Recipe where id = :id");
        query.setParameter("id", id);
        Recipe recipe = new Recipe();

        try {
            recipe = (Recipe) query.getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            // Real catching to do to handle. Not found.
        }

        return recipe;
    }

    public void create(Recipe recipe) {
        Session session = getEntityManager().unwrap(Session.class);
        session.save(recipe);
    }

    public void update(Recipe recipe) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = session.load(Recipe.class, recipe.getId());
        recipeInDB = recipe;
        session.update(recipeInDB);
    }

    public void delete(Recipe recipe) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = session.load(Recipe.class, recipe.getId());
        session.remove(recipeInDB);
    }

    public void deleteById(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = session.load(Recipe.class, id);
        session.remove(recipeInDB);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
