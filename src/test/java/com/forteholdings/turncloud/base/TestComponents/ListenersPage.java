package com.forteholdings.turncloud.base.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.forteholdings.turncloud.resources.json.ExtentReportNG;

public class ListenersPage extends BasePage implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportNG.Reportobject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());

		WebDriver testclassdriver = null;

		Object testinstance = result.getInstance();
		if (testinstance instanceof BasePage) {
			testclassdriver = ((BasePage) testinstance).driver;

		} else {

			System.err.println("The test instance is not a BaseTest. Cannot retrieve WebDriver for screenshot.");
		}

		String filepath = null;

		if (testclassdriver != null) {

			try {
				filepath = Screenshot(result.getMethod().getMethodName(), testclassdriver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			System.err.println(
					"WebDriver was null for test: " + result.getMethod().getMethodName() + ". Screenshot not taken.");
		}

		if (filepath != null) {

			extentTest.get().addScreenCaptureFromBase64String(filepath, result.getMethod().getMethodName());

		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
