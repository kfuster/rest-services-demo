package com.zakangroth.restservicesdemo.services;

import com.zakangroth.restservicesdemo.dto.RecipeDto;
import com.zakangroth.restservicesdemo.dto.RecipeIngredientDto;
import com.zakangroth.restservicesdemo.exceptions.ElementNotFoundException;
import com.zakangroth.restservicesdemo.model.Ingredient;
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
        Optional<Recipe> recipe = recipeRepository.getById(id);

        if (!recipe.isPresent()) {
            throw new ElementNotFoundException();
        }

        setupIngredients(recipe.get());

        return Optional.of(new RecipeDto(recipe.get()));
    }

    @Transactional
    public Optional<Long> create(RecipeDto recipeDto) {
        Recipe recipe = recipeDto.toRecipe();
        setupIngredients(recipe);
        return recipeRepository.create(recipe);
    }

    @Transactional
    public void addIngredients(Long id, List<RecipeIngredientDto> ingredients) {
        Optional<RecipeDto> recipeDto = getById(id);

        if (recipeDto.isPresent()) {
            recipeDto.get().getIngredients().addAll(ingredients);
            Recipe recipe = recipeDto.get().toRecipe();
            setupIngredients(recipe);
            recipeRepository.update(recipe);
        }
    }

    @Transactional
    public void update(RecipeDto recipeDto) {
        Optional<RecipeDto> recipeDtoOpt = getById(recipeDto.getId());

        if (recipeDtoOpt.isPresent()) {
            recipeDtoOpt.get().setIngredients(recipeDto.getIngredients());
            Recipe recipe = recipeDtoOpt.get().toRecipe();

            setupIngredients(recipe);

            recipeRepository.update(recipe);
        }
    }

    private void setupIngredients(Recipe recipe) {
        recipe.getIngredients().forEach(recipeIngredient -> {
            ingredientRepository.create(recipeIngredient.getIngredient());
            Optional<Ingredient> ingredient = ingredientRepository.getById(recipeIngredient.getIngredient().getId());
            ingredient.ifPresent(recipeIngredient::setIngredient);
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
