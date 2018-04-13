package com.zakangroth.restservicesdemo.repository;

import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.model.RecipeIngredients;
import org.hibernate.Hibernate;
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
        return session.get(Recipe.class, id);
    }

    public void create(Recipe recipe) {
        Session session = getEntityManager().unwrap(Session.class);
        session.save(recipe);
    }

    public void addIngredient(Long recipeId, Long ingredientId, int quantity, String unit) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, recipeId);
        Ingredient ingredientInDB = session.get(Ingredient.class, ingredientId);
        RecipeIngredients recipeIngredient = new RecipeIngredients(recipeInDB, ingredientInDB);
        recipeIngredient.setQuantity(quantity);
        recipeIngredient.setUnit(unit);
        recipeInDB.getRecipeIngredients().add(recipeIngredient);
        session.update(recipeInDB);
    }

    public void update(Recipe recipe) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, recipe.getId());
        recipeInDB = recipe;
        session.update(recipeInDB);
    }

    public void delete(Recipe recipe) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, recipe.getId());
        session.remove(recipeInDB);
    }

    public void deleteById(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = session.get(Recipe.class, id);
        session.remove(recipeInDB);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
