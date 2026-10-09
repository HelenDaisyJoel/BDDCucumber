package com.nclh.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "@target/failed.txt",
        glue = {"com/nclh/webSteps", "com/nclh/hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-html-reports",
                "html:target/cucumber-report.html",
               // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed_rerun1.txt"
        }
)
public class FailTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("================ BEFORE SUITE ================");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("================ AFTER SUITE ================");
    }

    @BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("before class");
        //context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(Integer.parseInt(System.getProperty("parallel_threads")));
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);
    }
}