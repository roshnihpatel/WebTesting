package com.sparta.rp.web_gui_testing.HNpom.pages;

import com.sparta.rp.web_gui_testing.HNpom.DriverOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    private static final FirefoxDriver firefoxDriver = new FirefoxDriver();
    private static final ChromeDriver chromeDriver = new ChromeDriver();
    private static final SafariDriver safariDriver = new SafariDriver();

    public static WebDriver getDriver(DriverOptions driverOptions) {
         switch (driverOptions){
             case CHROME -> {
                 return chromeDriver;
             }
             case FIREFOX -> {
                 return firefoxDriver;
             }
            case SAFARI -> {
                return safariDriver;
            }
            case CHROME_IPHONE12PRO -> {
                 chromeDriver.manage().window().setSize(new Dimension(390,844));
                 return chromeDriver;
            }default -> {
                 return null;
             }

        }
    }
}
