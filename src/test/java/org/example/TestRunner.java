package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = "stepdefs",
//        tags = "@test or @expedia",
        plugin = {
                "pretty",
                "html:target/cucumber",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:test-outcome/cucumber-report.json"
        })
public class TestRunner {
}
