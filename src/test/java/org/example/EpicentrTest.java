package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;

public class EpicentrTest {
    private WebDriver driver;

    @BeforeAll
    public static void setUpClassAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setDriver() {
        driver = new ChromeDriver();
    }

    @Test
    public void properCheckboxSelected() {
        String expectedURL = "https://epicentrk.ua/";
        driver.navigate().to(expectedURL);

        var pricesCatalog = driver.findElements(
                By.cssSelector(".card__price-sum"));


        var expectedPrices = pricesCatalog.stream().map(e -> e.getText()).toArray();
        System.out.println(Arrays.toString(expectedPrices));
        //Assertions.assertArrayEquals(expectedPrices, actualItem);

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
