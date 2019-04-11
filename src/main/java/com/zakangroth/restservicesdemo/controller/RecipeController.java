package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.dto.RecipeIngredientDto;
import com.zakangroth.restservicesdemo.exceptions.ElementNotFoundException;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<RecipeDto> getById(@PathVariable("id") final Long id) {
        Optional<RecipeDto> recipeDto = recipeService.getById(id);

        if (recipeDto.isPresent()) {
            return ResponseEntity.ok(recipeDto.get());
        }

        throw new ElementNotFoundException();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody RecipeDto recipe) {
        Optional<Long> recipeId = recipeService.create(recipe);

        if (recipeId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(recipeId.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @CrossOrigin
    @PutMapping(value = "/ingredients")
    public void addIngredients(@RequestParam("id") final Long id, @RequestBody List<RecipeIngredientDto> ingredients) {
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
    public void deleteById(@PathVariable("id") final Long id) {
        recipeService.deleteById(id);
    }
}
