package com.zakangroth.restservicesdemo.repository;

import com.zakangroth.restservicesdemo.exceptions.ElementNotFoundException;
import com.zakangroth.restservicesdemo.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public IngredientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Ingredient> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Ingredient");
        return query.getResultList();
    }

    public Ingredient getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Ingredient ingredient = session.get(Ingredient.class, id);

        if (ingredient == null) {
            throw new ElementNotFoundException();
        }

        return ingredient;
    }

    public Optional<Long> create(Ingredient ingredient) {

        if(ingredient.getId() == null) {
            Session session = entityManager.unwrap(Session.class);
            return Optional.of((long) session.save(ingredient));
        }

        return Optional.empty();
    }

    public void update(Ingredient ingredient) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(ingredient);
    }

    public void delete(Ingredient ingredient) {
        Session session = entityManager.unwrap(Session.class);
        Ingredient ingredientInDB = session.get(Ingredient.class, ingredient.getId());
        session.remove(ingredientInDB);
    }

    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Ingredient ingredientInDB = session.get(Ingredient.class, id);
        session.remove(ingredientInDB);
    }
}
