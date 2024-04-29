package ru.juli.praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

    @RunWith(Parameterized.class)
    public class IngredientParameterizedTest {
        private final IngredientType type;
        private final String name;
        private final float price;
        private final String expectedType;
        private final String expectedName;
        private final float expectedPrice;


        public IngredientParameterizedTest(IngredientType type, String name, float price,String expectedType, String expectedName, float expectedPrice) {
            this.type = type;
            this.name = name;
            this.price = price;
            this.expectedType = expectedType;
            this.expectedName = expectedName;
            this.expectedPrice = expectedPrice;
        }

        @Parameterized.Parameters
        public static Object[][] ingredientData() {
            return new Object[][]{
                    {IngredientType.SAUCE, "Соус Spicy-X", 90f, "SAUCE", "Соус Spicy-X", 90f},
                    {IngredientType.SAUCE, "", 0, "SAUCE", "", 0},
                    {IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 13.37f, "FILLING", "Мясо бессмертных моллюсков Protostomia", 13.37f},
                    {IngredientType.FILLING, "", 0, "FILLING", "", 0},
            };
        }

        @Test
        public void shouldBeCreateIngredients() {
            Ingredient ingredient = new Ingredient(type, name, price);
            Assert.assertEquals(ingredient.getType(), IngredientType.valueOf(expectedType));
            Assert.assertEquals(ingredient.getName(), expectedName);
            Assert.assertEquals(ingredient.getPrice(), expectedPrice, 0.0);
        }

}
