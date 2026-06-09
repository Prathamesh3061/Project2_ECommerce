package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

	public static String takeScreenshot(WebDriver driver,String testName) {
		 // Step 1 — Take screenshot as file
        // TakesScreenshot is built into Selenium
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		
        // Step 2 — Create unique filename with timestamp
        // So screenshots don't overwrite each other!
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String fileName = testName + "_" + timestamp + ".png";
		
//		step 3 define destination path
		String destPath = System.getProperty("user.dir")+"/screenshots"+fileName;
		
//		step 4 copy screenshots to destination
	       try {
	            FileUtils.copyFile(srcFile, new File(destPath));
	            System.out.println("Screenshot saved: " + destPath);
	        } catch (IOException e) {
	            System.out.println("Screenshot failed: " + e.getMessage());
	        }

	        // Return path so Extent Report can attach it
	        return destPath;
	}
}
