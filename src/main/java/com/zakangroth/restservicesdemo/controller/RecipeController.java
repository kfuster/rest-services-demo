package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.dto.RecipeIngredientsDto;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/recipes")
@Transactional
public class RecipeController {

    RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<RecipeDto> getAll() {

        List<RecipeDto> recipeDtos = new ArrayList<>();
        List<Recipe> recipes;

        recipes = recipeRepository.getAll();

        for (Recipe recipe : recipes) {
            recipeDtos.add(new RecipeDto(recipe));
        }

        return recipeDtos;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RecipeDto getById(@PathVariable("id") Long id) {
        return new RecipeDto(recipeRepository.getById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void create(@RequestBody RecipeDto recipe) {
        recipeRepository.create(recipe.toRecipe());
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.PUT)
    public void addIngredient(@RequestBody List<RecipeIngredientsDto> ingredients) {
        recipeRepository.addIngredients(ingredients);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public void update(@RequestBody RecipeDto recipe) {
        recipeRepository.update(recipe.toRecipe());
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestBody RecipeDto recipeDto) {
        recipeRepository.delete(recipeDto.toRecipe());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }
}
