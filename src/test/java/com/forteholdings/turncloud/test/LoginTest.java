package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.HomePage;

public class LoginTest extends BasePage {

	List<HashMap<String, Object>> loginDataList;

	@BeforeMethod
	public void loginSetup() throws IOException {
		// Load login data from JSON
		loginDataList = DataReader.getjsondatamap("Logindata.json");

		// Perform login
		HomePage home = new HomePage(driver);
		home.PageCredentials(loginDataList.get(0).get("email").toString(),
				loginDataList.get(0).get("password").toString(), loginDataList.get(0).get("Account").toString());

		// Optionally, wait or validate home page loaded
		// home.ispagedisplayed();
	}

	@Test
	public void loginWithValidCredentials() {
		// You are already logged in via @BeforeMethod
		System.out.println("Valid login test - already logged in.");
	}

	@Test
	public void loginWithInvalidCredentials() {
		// If you want to test invalid login, you need a separate test
		System.out.println("Invalid login test - skip @BeforeMethod or override.");
	}
}