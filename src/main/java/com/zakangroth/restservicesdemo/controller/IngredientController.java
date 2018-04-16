package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ingredients")
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<IngredientDto> getAll() {

        List<IngredientDto> ingredientDtos = new ArrayList<>();
        List<Ingredient> ingredients;

        ingredients = ingredientRepository.getAll();

        for (Ingredient ingredient : ingredients) {
            ingredientDtos.add(new IngredientDto(ingredient));
        }

        return ingredientDtos;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public IngredientDto getById(@PathVariable("id") Long id) {
        return new IngredientDto(ingredientRepository.getById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void create(@RequestBody String name) {
        ingredientRepository.create(name);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public void update(@RequestBody IngredientDto ingredientDto) {
        ingredientRepository.update(ingredientDto.toIngredient());
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestBody IngredientDto ingredientDto) {
        ingredientRepository.delete(ingredientDto.toIngredient());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Long id) {
        ingredientRepository.deleteById(id);
    }
}
