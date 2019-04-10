package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @CrossOrigin
    @GetMapping
    public List<IngredientDto> getAll() {
        return ingredientService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public IngredientDto getById(@PathVariable("id") Long id) {
        return ingredientService.getById(id);
    }

    @CrossOrigin
    @PostMapping
    public void create(@RequestBody String name) {
        ingredientService.create(name);
    }

    @CrossOrigin
    @PatchMapping
    public void update(@RequestBody IngredientDto ingredientDto) {
        ingredientService.update(ingredientDto);
    }

    @CrossOrigin
    @DeleteMapping
    public void delete(@RequestBody IngredientDto ingredientDto) {
        ingredientService.delete(ingredientDto);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        ingredientService.deleteById(id);
    }
}
