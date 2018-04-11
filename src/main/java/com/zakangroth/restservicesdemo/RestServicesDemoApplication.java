package com.zakangroth.restservicesdemo;

import com.zakangroth.restservicesdemo.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServicesDemoApplication {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RecipeRepository recipeRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestServicesDemoApplication.class, args);
    }
}
