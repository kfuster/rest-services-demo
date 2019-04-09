package com.zakangroth.restservicesdemo.model;


import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class RecipeIngredientsId implements Serializable {

    private Recipe recipe;
    private Ingredient ingredient;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Recipe getRecipe() {
        return recipe;
    }

    void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
