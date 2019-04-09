package com.zakangroth.restservicesdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "recipe_ingredients")
@AssociationOverrides({
        @AssociationOverride(name = "recipeIngredientsId.ingredient", joinColumns = @JoinColumn(name = "ingredient_id")),
        @AssociationOverride(name = "recipeIngredientsId.recipe", joinColumns = @JoinColumn(name = "recipe_id"))
})
public class RecipeIngredients {

    // Composite id_key
    private RecipeIngredientsId recipeIngredientsId = new RecipeIngredientsId();

    private int quantity;
    private String unit;

    public RecipeIngredients() {
    }

    public RecipeIngredients(Recipe recipe, Ingredient ingredient) {
        this.recipeIngredientsId.setRecipe(recipe);
        this.recipeIngredientsId.setIngredient(ingredient);
    }

    @EmbeddedId
    private RecipeIngredientsId getRecipeIngredientsId() {
        return recipeIngredientsId;
    }

    public void setRecipeIngredientsId(RecipeIngredientsId recipeIngredientsId) {
        this.recipeIngredientsId = recipeIngredientsId;
    }

    @Transient
    public Ingredient getIngredient() {
        return getRecipeIngredientsId().getIngredient();
    }

    public void setIngredient(Ingredient ingredient) {
        getRecipeIngredientsId().setIngredient(ingredient);
    }

    @Transient
    public Recipe getRecipe() {
        return getRecipeIngredientsId().getRecipe();
    }

    public void setRecipe(Recipe recipe) {
        getRecipeIngredientsId().setRecipe(recipe);
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
