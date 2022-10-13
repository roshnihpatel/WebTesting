package com.sparta.rp.web_gui_testing.HNpom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PastPage extends RanksPage {
    private WebDriver driver;
    private By pageTop = new By.ByClassName("pagetop");
    private By pageDate = new By.ByTagName("font");


    public PastPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
    }

    public String getDateFromNavBar() {
        return driver.findElement(pageTop).findElement(pageDate).getText();
    }

}
