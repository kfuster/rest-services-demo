package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/recipes")
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
    public void create(RecipeDto recipe) {
        recipeRepository.create(recipe.toRecipe());
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(RecipeDto recipe) {
        recipeRepository.update(recipe.toRecipe());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(RecipeDto recipeDto) {
        recipeRepository.delete(recipeDto.toRecipe());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }
}
