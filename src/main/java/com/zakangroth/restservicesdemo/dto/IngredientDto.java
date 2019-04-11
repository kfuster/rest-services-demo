package com.zakangroth.restservicesdemo.dto;

import com.zakangroth.restservicesdemo.model.Ingredient;

public class IngredientDto {

    private Long id;
    private String name;

    /**
     *  Mandatory for Spring
     */
    public IngredientDto() {

    }

    public IngredientDto(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
    }

    public Ingredient toIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(this.id);
        ingredient.setName(this.name);
        return ingredient;
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
}
