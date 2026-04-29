package com.forteholdings.turncloud.base.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	protected WebDriver driver;
	public HomePage home;
	protected static ExtentReports extent;
	protected static ExtentTest test;

	// Reads the browser name from GlobalData.properties and initializes WebDriver
	public WebDriver initializationDriver() throws IOException {
		if (driver == null) { // ✅ only create driver if not already created
			Properties property = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+ "//src/test//java//com//forteholdings//turncloud//resources//json//GlobalData.properties");
			property.load(file);

			String browserName = property.getProperty("browser");

			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--window-size=800,800");
				options.addArguments("--force-device-scale-factor=0.90");
				options.addArguments("--high-dpi-support=1");
				driver = new ChromeDriver(options);
			}
			// Uncomment when Firefox support is needed
			// else if (browserName.equalsIgnoreCase("firefox")) {
			// WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			// }

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		}
		return driver;
	}

	@BeforeClass(alwaysRun = true)
	public HomePage launchapplication() throws IOException, InterruptedException {
		driver = initializationDriver();
		home = new HomePage(driver);

		// 🔑 Load login data
		List<HashMap<String, Object>> logindata = DataReader.getjsondatamap("Logindata.json");

		String email = logindata.get(0).get("email").toString();
		String password = logindata.get(0).get("password").toString();
		String account = logindata.get(0).get("Account").toString();

		// 🔑 Navigate and login
		home.PageUrl();
		home.PageCredentials(email, password, account);
		// config();

		// 🔑 Try closing release notes (only if visible)
		// home.closeRealseNotes();

		// 🔑 Ensure patient page loaded
		// home.ispagedisplayed();

		return home;
	}

	@BeforeClass(alwaysRun = true)
	public void setupExtent() {
		ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@AfterClass(alwaysRun = true)
	public void flushReport() {
		extent.flush();
	}

	public String Screenshot(String TestName, WebDriver driver) throws IOException {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + TestName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + TestName + ".png";

	}

}
