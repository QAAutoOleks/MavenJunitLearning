package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToDoTests {
    private final int WAIT_FOR_ELEMENT_TIMEOUT = 30;
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Actions actions;

    private WebElement waitAndFindElement(By locator) {
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void openTechnologyApp(String technologyName) {
        var technologyLink = waitAndFindElement(By.cssSelector(technologyName));
        technologyLink.click();
    }

    private void addNewToDoItem(String itemText) {
        var toDoInput = waitAndFindElement(
                By.xpath("//input[@placeholder='What needs to be done?']"));
        toDoInput.sendKeys(itemText);
        actions.click(toDoInput).sendKeys(Keys.ENTER).pause(1000).perform();
    }

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver,
                Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT));
        actions = new Actions(driver);
    }

    @Test
    public void verifyToDoListCreatedSuccessfully() {
        driver.navigate().to("https://todomvc.com");
        openTechnologyApp("[data-source='http://backbonejs.org/']");
        addNewToDoItem("ToDo Text");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
