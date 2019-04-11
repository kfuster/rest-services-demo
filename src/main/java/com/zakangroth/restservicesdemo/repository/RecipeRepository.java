package com.zakangroth.restservicesdemo.repository;

import com.zakangroth.restservicesdemo.exceptions.ElementNotFoundException;
import com.zakangroth.restservicesdemo.model.Recipe;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RecipeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public RecipeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Recipe> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Recipe");
        return query.getResultList();
    }

    public Recipe getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Recipe recipe = session.get(Recipe.class, id);

        if (recipe == null) {
            throw new ElementNotFoundException();
        }
        return recipe;
    }

    public void create(Recipe recipe) {
        if (recipe.getId() == null) {
            Session session = entityManager.unwrap(Session.class);
            session.persist(recipe);
        }
    }

    public void update(Recipe recipe) {
        Session session = entityManager.unwrap(Session.class);
        session.get(Recipe.class, recipe.getId());
        session.merge(recipe);
    }

    public void delete(Recipe recipe) {
        Session session = entityManager.unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, recipe.getId());
        session.remove(recipeInDB);
    }

    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, id);
        session.remove(recipeInDB);
    }
}
