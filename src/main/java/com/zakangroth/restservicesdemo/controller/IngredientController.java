package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/ingredients")
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void create(String name) {
        ingredientRepository.create(name);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(IngredientDto ingredientDto) {
        ingredientRepository.update(ingredientDto.toIngredient());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(IngredientDto ingredientDto) {
        ingredientRepository.delete(ingredientDto.toIngredient());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Long id) {
        ingredientRepository.deleteById(id);
    }
}
