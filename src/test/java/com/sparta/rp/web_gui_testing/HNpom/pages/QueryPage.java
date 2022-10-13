package com.sparta.rp.web_gui_testing.HNpom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class QueryPage extends Page {

    private By searchBar = new By.ByName("q");
    private By navBar = new By.ByClassName("pagetop");

    public QueryPage(WebDriver driver) {
        setDriver(driver);
    }





 }
