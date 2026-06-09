package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.utilities.ExtentReportManager;

public class BaseTest {
	
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	@BeforeClass
	public void setup() {
//		Report setup
		extent = ExtentReportManager.getReportInstance();
		
//		browser Setup
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		System.out.println("Browser opened - SauceDemo site loaded!");
	}
	
	@AfterClass
	public void teardown() {
		if(driver != null) {
			driver.quit();
			System.out.println("Browser closed");
		}
	}
	

}
