package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/recipes")
@Transactional
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody RecipeDto recipe) {
        recipeRepository.create(recipe.toRecipe());
    }

    @RequestMapping(value = "/ingredient", method = RequestMethod.POST)
    public void addIngredient(Long recipeId, Long ingredientId, int quantity, String unit){
        recipeRepository.addIngredient(recipeId, ingredientId, quantity, unit);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody RecipeDto recipe) {
        recipeRepository.update(recipe.toRecipe());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody RecipeDto recipeDto) {
        recipeRepository.delete(recipeDto.toRecipe());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }
}
