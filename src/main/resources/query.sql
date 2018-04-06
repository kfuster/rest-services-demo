// Example query for all ingredients in a recipe
SELECT
  ingredient.name,
  recipe_ingredient.quantity,
  recipe_ingredient.unit
FROM ingredient
  JOIN recipe_ingredient ON ingredient.id = recipe_ingredient.ingredient_id
  JOIN recipe ON recipe.id = recipe_ingredient.recipe_id
WHERE recipe.id = 1
