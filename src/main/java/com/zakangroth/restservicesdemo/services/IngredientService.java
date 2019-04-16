package com.zakangroth.restservicesdemo.services;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.exceptions.ElementNotFoundException;
import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<IngredientDto> getAll() {
        return ingredientRepository.getAll().stream().map(IngredientDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Optional<IngredientDto> getById(Long id) {
        Ingredient ingredient = ingredientRepository.getById(id);
        if (ingredient == null) {
            throw new ElementNotFoundException();
        }

        return Optional.of(new IngredientDto(ingredient));
    }

    @Transactional
    public Optional<Long> create(String name) {
        Ingredient ingredient = new Ingredient(name);
        return ingredientRepository.create(ingredient);
    }

    @Transactional
    public Optional<Ingredient> update(IngredientDto ingredientDto) {

        Ingredient ingredient = ingredientRepository.update(ingredientDto.toIngredient());
        if (ingredient != null) {
            return Optional.of(ingredient);
        }

        return Optional.empty();
    }

    @Transactional
    public void delete(IngredientDto ingredientDto) {
        ingredientRepository.delete(ingredientDto.toIngredient());
    }

    @Transactional
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
