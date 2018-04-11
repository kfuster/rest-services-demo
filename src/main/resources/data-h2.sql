// Example data creation : For some reason fist instruction is ignored on startup

INSERT INTO ingredient VALUES (NULL, 'Dark rum (Appleton Estate Reserve)');
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

INSERT INTO recipe_ingredients VALUES (1, 1, 2, 'oz');
INSERT INTO recipe_ingredients VALUES (1, 2, 1, 'oz');
INSERT INTO recipe_ingredients VALUES (1, 3, 1, 'oz');
