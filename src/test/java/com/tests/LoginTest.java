package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.LoginPage;
import com.utilities.ExcelUtils;

public class LoginTest extends BaseTest {

//	object of loginPage
	LoginPage loginPage;
	ExcelUtils excel;
	
//	before class setup
	@BeforeClass
	public void initPage() throws Exception {
//		create login page object
		loginPage = new LoginPage(driver);
		
		String excelPath = System.getProperty("user.dir")+"/testdata/LoginData.xlsx";
		
		excel = new ExcelUtils(excelPath,"LoginData");
		
		System.out.println("Login test setup done");
	}
	
	
//	DataProvider
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData() {
//		how many data rows in Excel
		int rowCount = excel.getRowCount();
		System.out.println("Total test rows in Excel: " + rowCount);
		
        // Create 2D array to hold all rows
        // rowCount = number of rows
        // 3 = number of columns (username, password, expected)
        Object[][] data = new Object[rowCount][3];
        
        // Loop through each row and read data
        for (int i = 0; i < rowCount; i++) {
            data[i][0] = excel.getCellData(i + 1, 0); // username  (col 0)
            data[i][1] = excel.getCellData(i + 1, 1); // password  (col 1)
            data[i][2] = excel.getCellData(i + 1, 2); // expected  (col 2)
        }

        return data;

	}
	
//	Test 1-Valid Login
	@Test(dataProvider= "loginData", priority = 1)
	public void validLoginTest(String username, String password, String expected) {
		
//		create test entry in the report
		extentTest = extent.createTest(
		            "Login Test | User: " + username + " | Expected: " + expected
		        );
		extentTest.info("Starting login with - Username: " 
                + username + ", Password: " + password);
		
		// Navigate to login page fresh for each run
        driver.navigate().to("https://www.saucedemo.com");
		
//		call login method from loginPage
        loginPage.login(username, password);
		extentTest.info("Login action performed");
		
		
//		verify based on expected
		 if (expected.equalsIgnoreCase("success")) {

	            // Should land on inventory page
	            String currentUrl = driver.getCurrentUrl();
	            Assert.assertTrue(currentUrl.contains("inventory.html"),
	                    "Expected success but login failed! URL: " + currentUrl);

	            extentTest.pass("Login SUCCESS ✅ | URL: " + currentUrl);
	            System.out.println("PASS → Valid login successful!");

	        } else {

	            // Should show error message
	            String errorMsg = loginPage.getErrorMessage();
	            Assert.assertTrue(errorMsg.contains("Epic sadface"),
	                    "Expected failure message but got: " + errorMsg);

	            extentTest.pass("Login FAILURE handled correctly ✅ | Error: " 
	                            + errorMsg);
	            System.out.println("PASS → Invalid login error shown: " + errorMsg);
	        }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		verify - url shoud contain inventry.html after login
//		String currentUrl = driver.getCurrentUrl();
//		System.out.println("CurrentUrl: "+currentUrl);
//		
//		Assert.assertTrue(currentUrl.contains("inventory.html"),
//				"valid login failed! URL: "+currentUrl);
//		
//		extentTest.pass("valid login passed! landed on: "+ currentUrl);
//		
////		go back to login page for next test
//		driver.navigate().to("https://www.saucedemo.com");
//	}
	
//	invalid test
//	@Test(priority = 2)
//	public void invalidLoginTest() {
//		extentTest = extent.createTest("Invalid Login Start");
//		extentTest.info("Starting invalid login test");
//		
////		login with wrong credentials
//		loginPage.login("wrong_user", "wrong_pass");
//		extentTest.info("Entered invalid credentials and clicked login");
//		
////		verify error message shown
//		String errorMsg = loginPage.getErrorMessage();
//		System.out.println("Error msg: "+errorMsg);
//		
//		Assert.assertTrue(errorMsg.contains("Epic sadface"), 
//				"Error message not shown! Got: " + errorMsg);
//		
//	extentTest.pass("Invalid login handled correctly! Error: " + errorMsg);
	
	
	}
}
