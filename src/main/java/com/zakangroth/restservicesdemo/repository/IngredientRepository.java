package com.zakangroth.restservicesdemo.repository;

import com.zakangroth.restservicesdemo.model.Ingredient;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IngredientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Ingredient getIngredientById(long id) {
        return entityManager.find(Ingredient.class, id);
    }

    public void create(Ingredient ingredient) {
        entityManager.persist(ingredient);
    }

    public void update(Ingredient ingredient) {
        entityManager.merge(ingredient);
    }

    public void delete(long id) {
        Ingredient ingredient = getIngredientById(id);
        if (ingredient != null) {
            entityManager.remove(ingredient);
        }
    }
}
