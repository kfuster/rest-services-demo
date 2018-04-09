package com.zakangroth.restservicesdemo;

import com.zakangroth.restservicesdemo.model.Ingredient;
import com.zakangroth.restservicesdemo.model.Recipe;
import com.zakangroth.restservicesdemo.model.RecipeIngredient;
import com.zakangroth.restservicesdemo.repository.IngredientRepository;
import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServicesDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeRepository recipeRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestServicesDemoApplication.class, args);
    }

    public void run(String... args) {
        Recipe recipe = (Recipe) recipeRepository.getRecipeById(1L).get(0);
        logger.info("Ingredients :");
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            logger.info("{}", ingredient.getIngredient().getName());
        }
    }
}
