package com.zakangroth.restservicesdemo.services;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.dto.RecipeIngredientDto;
import com.zakangroth.restservicesdemo.exceptions.ElementNotFoundException;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<RecipeDto> getAll() {
        return recipeRepository.getAll().stream().map(RecipeDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Optional<RecipeDto> getById(Long id) {

        Recipe recipe = recipeRepository.getById(id);

        if (recipe == null) {
            throw new ElementNotFoundException();
        }


        return Optional.of(new RecipeDto(recipe));
    }

    @Transactional
    public Optional<Long> create(RecipeDto recipeDto) {
        Recipe recipe = recipeDto.toRecipe();
        recipe.getIngredients().forEach(recipeIngredient -> ingredientRepository.create(recipeIngredient.getIngredient()));
        return recipeRepository.create(recipe);
    }

    @Transactional
    public void addIngredients(Long id, List<RecipeIngredientDto> ingredients) {
        Optional<RecipeDto> recipeDto = getById(id);

        if (recipeDto.isPresent()) {
            recipeDto.get().getIngredients().addAll(ingredients);
            Recipe recipe = recipeDto.get().toRecipe();
            recipe.getIngredients().forEach(recipeIngredient -> ingredientRepository.create(recipeIngredient.getIngredient()));
            recipeRepository.update(recipe);
        }
    }

    @Transactional
    public void update(RecipeDto recipe) {
        recipeRepository.update(recipe.toRecipe());
    }

    @Transactional
    public void delete(RecipeDto recipeDto) {
        recipeRepository.delete(recipeDto.toRecipe());
    }

    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
