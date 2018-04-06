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
