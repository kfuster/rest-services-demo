package com.zakangroth.restservicesdemo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient implements Serializable{

    @EmbeddedId
    private RecipeIngredientPk recipeIngredientPk;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit")
    private String unit;

    public RecipeIngredient() {
    }

    public RecipeIngredientPk getRecipeIngredientPk() {
        return recipeIngredientPk;
    }

    public void setRecipeIngredientPk(RecipeIngredientPk recipeIngredientPk) {
        this.recipeIngredientPk = recipeIngredientPk;
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

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeIngredientPk=" + recipeIngredientPk +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                '}';
    }
}
