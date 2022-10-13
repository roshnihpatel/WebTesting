package com.sparta.rp.web_gui_testing.HNpom.pages;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class Page {


    private WebDriver driver;

    private final By searchBar = new By.ByName("q");
    private final By navBar = new By.ByClassName("pagetop");
    private final By homeLogo = new By.ByClassName("hname");


    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public QueryPage searchInSearchBar(String searchWord) {
        driver.findElement(searchBar).sendKeys(searchWord, Keys.ENTER);
        return new QueryPage(driver);
    }

    public Page goToNavBar(WebDriver driver) {
        driver.findElement(navBar);
        return this;
    }

    public void goToHomePageUsingLogo(WebDriver driver) {
        driver.findElement(homeLogo).click();
    }

    public String getURL() {
        return driver.getCurrentUrl();

    }


}
