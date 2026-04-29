package com.forteholdings.turncloud.resources.json;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	ExtentReports extent;

	public static ExtentReports Reportobject() {

		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Anagha Gangane");
		reporter.config().setDocumentTitle("Turncloud Testing");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Anagha", "Tester");
		return extent;
	}

}