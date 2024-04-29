package ru.juli.praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
@RunWith(Parameterized.class)
    public class BunParameterizedTest {

        private String name;
        private float price;
    private final static double DELTA = 0.0;

        // Конструктор, который принимает параметры для каждого теста.
        public BunParameterizedTest(String name, float price) {
            this.name = name;
            this.price = price;
        }

        @Parameterized.Parameters
        public static Object[][] dataBun() {
            return new Object[][] {
                    { "Флюоресцентная булка R2-D3", 988.9f },
                    { "Краторная булка N-200i", 1255f },
                    { "Любое другое название булки", 150.45f },
                    { "Л", 0.0001f },
                    { "", 999999f }
            };
        }

        @Test
        public void testBunConstructorAndGetters() {
            Bun bun = new Bun(name, price);
            Assert.assertEquals(name, bun.getName());
            Assert.assertEquals(price, bun.getPrice(), DELTA);
        }
    }
