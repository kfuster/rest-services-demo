package com.zakangroth.restservicesdemo.services;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    public IngredientDto getById(Long id) {
        return new IngredientDto(ingredientRepository.getById(id));
    }

    @Transactional
    public void create(String name) {
        ingredientRepository.create(new Ingredient(name));
    }

    @Transactional
    public void update(IngredientDto ingredientDto) {
        ingredientRepository.update(ingredientDto.toIngredient());
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
