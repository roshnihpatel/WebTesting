package com.sparta.rp.web_gui_testing;


import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

import  static  org.junit.jupiter.api.Assertions.*;


public class BasicWebTests {
    private static WebDriver driver;
    private static ChromeDriverService service;

    private static ChromeOptions options;

    @BeforeAll
    static void setupAll() {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(service, options);
        driver.get("https://www.bbc.co.uk");
    }

    @Test
    @DisplayName("Check that the webdriver works")
    void checkThatTheWebdriverWorks() {
        assertEquals("BBC - Home", driver.getTitle());
    }

    @Test
    @DisplayName("Check that the URL for the BBC is correct")
    void checkThatTheUrlForTheBbcIsCorrect() {
        assertEquals("https://www.bbc.co.uk/", driver.getCurrentUrl());
    }

    @AfterEach
    void tearDown() {
        //driver.close(); //closes windows after each test
    }

    @AfterAll
    static void tearDownAll() {
        service.stop();
        //driver.quit(); // quits the webdriver and will also close all the windows
    }
}
