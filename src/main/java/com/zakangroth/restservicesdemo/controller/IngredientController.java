package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @CrossOrigin
    @GetMapping
    public List<IngredientDto> getAll() {
        return ingredientRepository.getAll().stream().map(IngredientDto::new).collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public IngredientDto getById(@PathVariable("id") Long id) {
        return new IngredientDto(ingredientRepository.getById(id));
    }

    @CrossOrigin
    @PostMapping
    public void create(@RequestBody String name) {
        ingredientRepository.create(new Ingredient(name));
    }

    @CrossOrigin
    @PatchMapping
    public void update(@RequestBody IngredientDto ingredientDto) {
        ingredientRepository.update(ingredientDto.toIngredient());
    }

    @CrossOrigin
    @DeleteMapping
    public void delete(@RequestBody IngredientDto ingredientDto) {
        ingredientRepository.delete(ingredientDto.toIngredient());
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        ingredientRepository.deleteById(id);
    }
}
