// Tables creation: For some reason fist instruction is ignored on startup

CREATE TABLE recipe (
  id           INT NOT NULL AUTO_INCREMENT,
  name         VARCHAR(50),
  picture      VARCHAR(255),
  description  VARCHAR(255),
  instructions VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE recipe (
  recipe_id    INT NOT NULL AUTO_INCREMENT,
  name         VARCHAR(50),
  picture      VARCHAR(255),
  description  VARCHAR(255),
  instructions VARCHAR(255),
  PRIMARY KEY (recipe_id)
);

CREATE TABLE ingredient (
  ingredient_id INT NOT NULL AUTO_INCREMENT,
  name          VARCHAR(50) UNIQUE,
  PRIMARY KEY (ingredient_id)
);


CREATE TABLE recipe_ingredients (
  recipe_id     INT NOT NULL,
  ingredient_id INT NOT NULL,
  quantity      FLOAT,
  unit          VARCHAR(10),
  PRIMARY KEY (recipe_id, ingredient_id),
  FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id),
  FOREIGN KEY (ingredient_id) REFERENCES ingredient (ingredient_id)
);
