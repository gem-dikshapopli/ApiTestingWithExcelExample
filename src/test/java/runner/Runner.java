package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/feature/Demo.feature"
        ,"src/test/resources/feature/GoRestDemo.feature"
                ,"src/test/resources/feature/GoRestPostDemo.feature"}
        ,glue = "stepdefinition"
        ,tags = "@Demo1"
        ,monochrome = true
        ,publish = true
)

public class Runner extends AbstractTestNGCucumberTests {
}
