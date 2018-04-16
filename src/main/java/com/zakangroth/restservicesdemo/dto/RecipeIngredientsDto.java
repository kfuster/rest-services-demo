package com.zakangroth.restservicesdemo.dto;

import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.model.RecipeIngredients;

public class RecipeIngredientsDto {

    private Long recipeId;
    private Long ingredientId;
    private String name;
    private int quantity;
    private String unit;

    public RecipeIngredientsDto() {
    }

    public RecipeIngredientsDto(RecipeIngredients recipeIngredient) {
        this.recipeId = recipeIngredient.getRecipe().getId();
        this.ingredientId = recipeIngredient.getIngredient().getId();
        this.name = recipeIngredient.getIngredient().getName();
        this.quantity = recipeIngredient.getQuantity();
        this.unit = recipeIngredient.getUnit();
    }

    public RecipeIngredients toRecipeIngredients(){
        RecipeIngredients recipeIngredient = new RecipeIngredients();
        Recipe recipe = new Recipe();
        recipe.setId(this.recipeId);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(this.ingredientId);
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(this.quantity);
        recipeIngredient.setUnit(this.unit);
        return recipeIngredient;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
