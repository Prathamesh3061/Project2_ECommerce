package com.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	  private static ExtentReports extent;

	    public static ExtentReports getReportInstance() {

	        if (extent == null) {

	            String reportPath = System.getProperty("user.dir") + "/reports/TestReport.html";
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

	            sparkReporter.config().setTheme(Theme.DARK);
	            sparkReporter.config().setDocumentTitle("ECommerce Automation Report");
	            sparkReporter.config().setReportName("Project 2 - SauceDemo Test Results");

	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);

	            extent.setSystemInfo("Tester", "Prathamesh Sawant");
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("Browser", "Chrome");
	            extent.setSystemInfo("Website", "SauceDemo");
	        }

	        return extent;
	    }
}
