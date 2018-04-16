package com.zakangroth.restservicesdemo.repository;

import com.zakangroth.restservicesdemo.dto.RecipeIngredientsDto;
import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.model.RecipeIngredients;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

        final ArrayList<RecipeIngredients> ingredients = new ArrayList<>(recipe.getRecipeIngredients());
        recipe.getRecipeIngredients().clear();
        session.save(recipe);

        List<RecipeIngredientsDto> ingredientsDtos = new ArrayList<>();
        for (RecipeIngredients ingredient : ingredients){
            RecipeIngredientsDto recipeIngredientDto = new RecipeIngredientsDto(ingredient);
            recipeIngredientDto.setRecipeId(recipe.getId());
            ingredientsDtos.add(recipeIngredientDto);
        }
        addIngredients(ingredientsDtos);
    }

    public void addIngredients(List<RecipeIngredientsDto> ingredients) {
        Session session = getEntityManager().unwrap(Session.class);
        Recipe recipeInDB = new Recipe();

        for (RecipeIngredientsDto ingredient : ingredients) {
            recipeInDB = session.get(Recipe.class, ingredient.getRecipeId());
            Ingredient ingredientInDB = session.get(Ingredient.class, ingredient.getIngredientId());
            RecipeIngredients recipeIngredient = new RecipeIngredients(recipeInDB, ingredientInDB);
            recipeIngredient.setQuantity(ingredient.getQuantity());
            recipeIngredient.setUnit(ingredient.getUnit());
            recipeInDB.getRecipeIngredients().add(recipeIngredient);
        }
        session.update(recipeInDB);
    }

    public void update(Recipe recipe) {
        Session session = getEntityManager().unwrap(Session.class);
        session.get(Recipe.class, recipe.getId());
        session.save(recipe);
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
