package com.zakangroth.restservicesdemo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RecipeIngredientPk implements Serializable{

    @Column(name = "recipe_id")
    private Long recipe_id;

    @Column(name = "ingredient_id")
    private Long ingredient_id;

    public RecipeIngredientPk() {
    }

    public RecipeIngredientPk(Long recipe_id, Long ingredient_id) {
        this.recipe_id = recipe_id;
        this.ingredient_id = ingredient_id;
    }

    @Override
    public String toString() {
        return "RecipeIngredientPk{" +
                "recipe_id=" + recipe_id +
                ", ingredient_id=" + ingredient_id +
                '}';
    }
}
