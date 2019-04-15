package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.dto.RecipeIngredientDto;
import com.zakangroth.restservicesdemo.services.RecipeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @CrossOrigin
    @GetMapping
    public List<RecipeDto> getAll() {
        return recipeService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public RecipeDto getById(@PathVariable("id") Long id) {
        return recipeService.getById(id);
    }

    @CrossOrigin
    @PostMapping
    public void create(@RequestBody RecipeDto recipe) {
        recipeService.create(recipe);
    }

    @CrossOrigin
    @PutMapping(value = "/ingredients")
    public void addIngredients(@RequestParam("id") Long id, @RequestBody List<RecipeIngredientDto> ingredients) {
        recipeService.addIngredients(id, ingredients);
    }

    @CrossOrigin
    @PatchMapping
    public void update(@RequestBody RecipeDto recipe) {
        recipeService.update(recipe);
    }

    @CrossOrigin
    @DeleteMapping
    public void delete(@RequestBody RecipeDto recipeDto) {
        recipeService.delete(recipeDto);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        recipeService.deleteById(id);
    }
}
