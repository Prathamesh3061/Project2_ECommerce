package com.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.base.BaseTest;

public class TestListener implements ITestListener {

    // ========================
    // When Test STARTS
    // ========================

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("TEST STARTED: " + result.getName());
    }

    // ========================
    // When Test PASSES
    // ========================

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("TEST PASSED: " + result.getName());
    }

    // ========================
    // When Test FAILS — Main Logic!
    // ========================

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("TEST FAILED: " + result.getName());

        // Step 1 — Get the test class instance
        // result.getInstance() gives us the actual test object
        Object testInstance = result.getInstance();

        // Step 2 — Get driver and extentTest from BaseTest
        // Since all tests extend BaseTest, we can cast to BaseTest
        if (testInstance instanceof BaseTest) {

            BaseTest baseTest = (BaseTest) testInstance;
            WebDriver driver = baseTest.driver;
            ExtentTest extentTest = baseTest.extentTest;

            // Step 3 — Take screenshot
            String screenshotPath = ScreenshotUtils.takeScreenshot(
                driver, result.getName()
            );

            // Step 4 — Attach screenshot to Extent Report
            try {
                extentTest.fail("TEST FAILED: " + result.getName());
                extentTest.addScreenCaptureFromPath(screenshotPath,
                        "Failure Screenshot");
                System.out.println("Screenshot attached to report!");
            } catch (Exception e) {
                System.out.println("Could not attach screenshot: " 
                                   + e.getMessage());
            }
        }
    }

    // ========================
    // When Test SKIPS
    // ========================

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("TEST SKIPPED: " + result.getName());
    }

    // ========================
    // When Suite FINISHES
    // ========================

    @Override
    public void onFinish(ITestContext context) {
        // Flush report when everything is done
        ExtentReports extent = ExtentReportManager.getReportInstance();
        extent.flush();
        System.out.println("All tests done! Report saved!");
    }
}
