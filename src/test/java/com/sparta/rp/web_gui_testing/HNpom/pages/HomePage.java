package com.sparta.rp.web_gui_testing.HNpom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.management.Query;
import java.util.List;

public class HomePage extends RanksPage { //modeling the homepage and the things you can interact with
    private final WebDriver driver; // abstract type - only in homepage
    private final By comments = new By.ByLinkText("comments");
    private final By past = new By.ByLinkText("past");
    //private final By searchBar = new By.ByName("q");
    private final By scores = new By.ByClassName("scores");

    private final By rank = new By.ByClassName("rank");
    private final By show = new By.ByLinkText("show");


    private final By navBar = new By.ByClassName("pagetop");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        goToHomePage();
        setDriver(driver);

    }

    public HomePage goToHomePage() {
        driver.get("https://news.ycombinator.com/");
        return this;
    }

    public CommentsPage goToCommentsPage(){
        driver.findElement(comments).click();
        return new CommentsPage(driver);
    }

    public PastPage goToPastPage() {
        driver.findElement(past).click();
        return new PastPage(driver);
    }

    public ShowPage goToShowPage() {
        driver.findElement(show).click();
        return new ShowPage(driver);

    }

    public List<WebElement> getScores() {
        return driver.findElements(scores);
    }

}
