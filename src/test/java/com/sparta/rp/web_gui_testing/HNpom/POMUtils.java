package com.sparta.rp.web_gui_testing.HNpom;

public class POMUtils {
    public static void setDriverLocation(String path){
        System.setProperty("webdriver.chrome.driver", path);
    }
}
