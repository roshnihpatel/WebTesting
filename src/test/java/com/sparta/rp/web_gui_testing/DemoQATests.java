package com.sparta.rp.web_gui_testing;

import com.beust.ah.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DemoQATests {
    private static WebDriver driver;
    private static ChromeDriverService service;
    private static ChromeOptions options;
    private static List<WebElement> cards;

    @BeforeAll
    static void setupAll() {
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("src/test/resources/chromedriver.exe")).usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(service, options);
        driver.get("https://demoqa.com/");
        cards = driver.findElements(By.className("card mt-4 top-card"));

    }


    @Nested
    class ElementsTests {

        @BeforeAll
        static void setupAll() {
            cards.get(0).click();

        }

        @Test
        @DisplayName("Check that URL is /elements")
        void checkThatUrlIsElements() {
            Assertions.assertEquals("https://demoqa.com/elements", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Check that the textboxes work")
        void checkThatTheTextboxesWork() {
            driver.findElement(By.id("item-0")).click();
            driver.findElement(By.id("userName")).sendKeys("Roshni", Keys.TAB, "roshni@gmail.com", Keys.TAB, "London Victoria", Keys.TAB, "London Bridge", Keys.TAB, Keys.ENTER);
            //Assertions.assertEquals("Roshni",);


        }
    }

}
