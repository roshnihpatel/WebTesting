package com.sparta.rp.web_gui_testing.HNpom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class RanksPage extends Page{
    private  WebDriver driver;
    final By rank = new By.ByClassName("rank");

    public List<WebElement> getRanks() {
        return driver.findElements(rank);
    }

    public  boolean checkRanksAreInOrder(){
        List<WebElement> ranks = getRanks();
        boolean answer = true;
        for (int i = 0; i < ranks.size(); i++) {
            if (!ranks.get(i).getText().matches(i + 1 + ".")) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}

