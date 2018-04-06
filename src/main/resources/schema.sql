// Tables creation
CREATE TABLE recipe (
  id           INT NOT NULL AUTO_INCREMENT,
  name         VARCHAR(50),
  picture      VARCHAR(255),
  description  VARCHAR(255),
  instructions VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE ingredient (
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE recipe_ingredient (
  recipe_id     INT NOT NULL,
  ingredient_id INT NOT NULL,
  quantity      FLOAT,
  unit          VARCHAR(10),
  PRIMARY KEY (recipe_id, ingredient_id)
);

// Example data creation
INSERT INTO ingredient VALUES (NULL, 'Dark rum (Appleton Estate Reserve)');
INSERT INTO ingredient VALUES (NULL, 'Fresh lime juice');
INSERT INTO ingredient VALUES (NULL, 'Simple sirup');

INSERT INTO recipe VALUES (NULL,
                           'Daiquiri',
                           'https://cdn.liquor.com/wp-content/uploads/2016/07/29105024/DAIQUIRI-226x248-mosaic1.jpg',
                           'The classic Daiquiri is a super simple rum cocktail that\’s well-balanced and refreshing.\n'
                           ||
                           'The combination of sweet, sour and spirit is refreshingly tangy and perfect for any occasion.',
                           'Add all the ingredients to a shaker and fill with ice.\n' ||
                           'Shake, and strain into a chilled Martini glass.\n' ||
                           'Garnish with a lime wheel.'
);

INSERT INTO recipe_ingredient VALUES (1, 1, 2, 'oz');
INSERT INTO recipe_ingredient VALUES (1, 2, 1, 'oz');
INSERT INTO recipe_ingredient VALUES (1, 3, 1, 'oz');

// Example query for all ingredients in a recipe
SELECT
  ingredient.name,
  recipe_ingredient.quantity,
  recipe_ingredient.unit
FROM ingredient
  JOIN recipe_ingredient ON ingredient.id = recipe_ingredient.ingredient_id
  JOIN recipe ON recipe.id = recipe_ingredient.recipe_id
WHERE recipe.id = 1