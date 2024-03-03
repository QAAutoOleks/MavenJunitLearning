package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {
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
        driver.navigate().to("https://lambdatest.github.io/sample-todo-app/");

        var addField = driver.findElement(By.id("sampletodotext"));
        addField.sendKeys("123456789");

        var addButton = driver.findElement(By.id("addbutton"));
        addButton.click();

        // get() - number of element in list
        var checkboxesList = driver.findElements(By.cssSelector("[type='checkbox']"));
        checkboxesList.get(0).click();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
