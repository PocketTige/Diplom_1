package ru.juli.praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    Burger burger = new Burger();
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.addIngredient(ingredient);
        Assert.assertEquals(ingredients, burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertEquals(ingredients, burger.ingredients);
    }

    @Test
    public void moveIngredientTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1); // Индекс 0
        burger.addIngredient(ingredient2); // Индекс 1
        burger.moveIngredient(0, 1);
        Assert.assertEquals(ingredient2, burger.ingredients.get(0));
        Assert.assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn((float) 998);
        burger.setBuns(bun);
        when(ingredient.getPrice()).thenReturn((float) 15.4);
        burger.addIngredient(ingredient);
        Assert.assertEquals((float) 2011.4, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptTest() {
        when(bun.getPrice()).thenReturn((float) 998);
        when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        burger.setBuns(bun);
        when(ingredient.getPrice()).thenReturn((float) 15.4);
        when(ingredient.getName()).thenReturn("Соус Spicy-X");
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        burger.addIngredient(ingredient);
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", (float) 2011.4));
        Assert.assertEquals(receipt.toString(), burger.getReceipt());
    }
}

