package com.zakangroth.restservicesdemo.controller;

import com.zakangroth.restservicesdemo.dto.IngredientDto;
import com.zakangroth.restservicesdemo.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public IngredientDto getById(@PathVariable("id") final Long id) {
        return ingredientService.getById(id);
    }

    /**
     * @param name : Name of the ingredient (Not unique).
     * @return The id of the ingredient created
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody final String name) {
        Optional<Long> ingredientId = ingredientService.create(name);

        if (ingredientId.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ingredientId.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();


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
    public void deleteById(@PathVariable("id") final Long id) {
        ingredientService.deleteById(id);
    }
}
