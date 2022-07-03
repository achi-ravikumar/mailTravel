package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;


@CucumberOptions(
        monochrome = true,
        features = {"src/test/resources/"},
        glue = "com.stepDefs",
        plugin = "json:target/cucumber-reports/CucumberTestReport.json",
        tags = "@api"
)

public class TestRunnerAPI extends AbstractTestNGCucumberTests {
    @AfterSuite(alwaysRun = true)
    public void generateHTMLReports() {
        reportGeneration.generateCucumberReport();
    }

}