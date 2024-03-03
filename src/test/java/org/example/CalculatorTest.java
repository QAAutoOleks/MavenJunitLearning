package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;

public class CalculatorTest {
    private final Calculator _c = new Calculator();


    @Before
    public void setUpClass() {
        System.out.println("This is @Before annotation");
    }

    @BeforeAll
    public static void setUpClassAll() {
        System.out.println("This is @BeforeAll annotation");
    }

    @Test
    @RepeatedTest(value = 10, name = "{displayName}_{currentRepetition}/totalRepetitions}")
    public void isSumCorrect1() {
        var actualResult = _c.add(2, 3);
        Assertions.assertEquals(5, actualResult);
    }

    @Test
    public void isSumCorrect2() {
        var actualResult = _c.add(2, 2);
        Assertions.assertEquals(4, actualResult);
    }

    @After
    public void tearDown() {
        System.out.println("This is @After annotation");
    }
}
