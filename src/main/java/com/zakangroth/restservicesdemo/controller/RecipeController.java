package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.dto.RecipeIngredientsDto;
import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @CrossOrigin
    @GetMapping
    public List<RecipeDto> getAll() {
        return recipeRepository.getAll().stream().map(RecipeDto::new).collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public RecipeDto getById(@PathVariable("id") Long id) {
        return new RecipeDto(recipeRepository.getById(id));
    }

    @CrossOrigin
    @PostMapping
    public void create(@RequestBody RecipeDto recipe) {
        recipeRepository.create(recipe.toRecipe());
    }

    @CrossOrigin
    @PutMapping(value = "/ingredients")
    public void addIngredient(@RequestBody List<RecipeIngredientsDto> ingredients) {
        recipeRepository.addIngredients(ingredients);
    }

    @CrossOrigin
    @PatchMapping
    public void update(@RequestBody RecipeDto recipe) {
        recipeRepository.update(recipe.toRecipe());
    }

    @CrossOrigin
    @DeleteMapping
    public void delete(@RequestBody RecipeDto recipeDto) {
        recipeRepository.delete(recipeDto.toRecipe());
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }
}
