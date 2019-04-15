package com.zakangroth.restservicesdemo.services;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.dto.RecipeIngredientDto;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    public RecipeDto getById(Long id) {
        return new RecipeDto(recipeRepository.getById(id));
    }

    @Transactional
    public void create(RecipeDto recipeDto) {
        Recipe recipe = recipeDto.toRecipe();

        setupIngredients(recipe);

        recipeRepository.create(recipe);
    }


    @Transactional
    public void addIngredients(Long id, List<RecipeIngredientDto> ingredients) {
        RecipeDto recipeDto = getById(id);
        recipeDto.getIngredients().addAll(ingredients);

        Recipe recipe = recipeDto.toRecipe();

        setupIngredients(recipe);

        recipeRepository.update(recipe);
    }

    @Transactional
    public void update(RecipeDto recipedto) {
        RecipeDto recipeDto = getById(recipedto.getId());
        recipeDto.setIngredients(recipedto.getIngredients());

        Recipe recipe = recipeDto.toRecipe();

        setupIngredients(recipe);

        recipeRepository.update(recipe);
    }

    private void setupIngredients(Recipe recipe) {
        recipe.getIngredients().forEach(recipeIngredient -> {
            ingredientRepository.create(recipeIngredient.getIngredient());
            recipeIngredient.setIngredient(ingredientRepository.getById(recipeIngredient.getIngredient().getId()));
        });
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
