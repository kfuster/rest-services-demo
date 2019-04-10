package com.zakangroth.restservicesdemo.dto;

import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.model.RecipeIngredients;

import java.util.ArrayList;
import java.util.List;

public class RecipeDto {

    private Long id;
    private String name;
    private String picture;
    private String description;
    private List<RecipeIngredientsDto> ingredients = new ArrayList<>();
    private List<String> instructions;

    public RecipeDto() {
    }

    public RecipeDto(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.picture = recipe.getPicture();
        this.description = recipe.getDescription();

        for (RecipeIngredients recipeIngredient : recipe.getRecipeIngredients()) {
            this.ingredients.add(new RecipeIngredientsDto(recipeIngredient));
        }

        this.instructions = recipe.getInstructions();
    }

    public Recipe toRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(this.id);
        recipe.setName(this.name);
        recipe.setPicture(this.picture);
        recipe.setDescription(this.description);

        List<RecipeIngredients> recipeIngredients = new ArrayList<>();

        for (RecipeIngredientsDto recipeIngredientsDto : ingredients) {
            recipeIngredients.add(recipeIngredientsDto.toRecipeIngredients());
        }

        recipe.getRecipeIngredients().addAll(recipeIngredients);

        recipe.setInstructions(this.instructions);
        return recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<RecipeIngredientsDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientsDto> ingredientDtos) {
        this.ingredients = ingredientDtos;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
