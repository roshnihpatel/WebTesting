package com.sparta.rp.web_gui_testing.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        //glue = {"com.sparta.rp.web_gui_testing.cucumber.MyStepdefs.java"}
        plugin = {"pretty", "html:target/report.html", "json:target/report.json"},
        publish = true
)

public class TestRunner {}
