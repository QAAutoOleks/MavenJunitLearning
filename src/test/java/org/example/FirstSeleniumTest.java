package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

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
        String expectedURL = "https://lambdatest.github.io/sample-todo-app/";
        driver.navigate().to(expectedURL);

        var addField = driver.findElement(By.id("sampletodotext"));
        addField.sendKeys("123456789");

        var addButton = driver.findElement(By.id("addbutton"));
        addButton.click();

        // get() - number of element in list
        // By.xpath("//li[@ng-repeat]/input"));
        var checkboxesList = driver.findElements(By.cssSelector("[type='checkbox']"));
        checkboxesList.get(0).click();

        // By.cssSelector("[class='done-false']")); doesn't work, wrong letters?
        var infoList = driver.findElements(By.xpath("//li[@ng-repeat]/span"));

        Assertions.assertEquals("123456789",
                infoList.get(infoList.size() - 1).getText());

        Assertions.assertTrue(expectedURL.equals(driver.getCurrentUrl()),
                "URL doesn't match");

        Assertions.assertTrue(checkboxesList.get(0).isSelected(),
                "Checkbox is not selected");

        var expectedItems = new String[]{
                "First Item", "Second Item", "Third Item", "Fourth Item", "Fifth Item", "123456789"
        };
//        for (int i = 0; i < infoList.size(); i++) {
//        }
//            Assertions.assertEquals(expectedItems[i], infoList.get(i).getText());

//      WebElements to Array String
        var actualItem = infoList.stream().map(e -> e.getText()).toArray();
        Assertions.assertArrayEquals(expectedItems, actualItem);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
