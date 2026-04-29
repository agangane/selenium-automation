package com.forteholdings.turncloud.pageobject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class Employer extends CommonCode {

	WebDriver driver;

	public Employer(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ------------------------
	// Locators
	// ------------------------
	By employetab = By.id("PatientEmployer");

	@FindBy(css = "#PatientEmployer .k-panelbar-item-text")
	WebElement employertextname;

	// Company Details
	@FindBy(id = "PatientEmployerCompanyName")
	WebElement companyname;

	@FindBy(id = "PatientEmployerOccupation")
	WebElement occupation;

	@FindBy(id = "PatientEmployeraddress")
	WebElement companyaddress;

	@FindBy(id = "PatientEmployeraddress2")
	WebElement suite;

	@FindBy(id = "PatientEmployerCity")
	WebElement city;

	@FindBy(xpath = "//div[@id='EmployerForm']//label[text()='State']/following-sibling::span[contains(@class,'k-picker')]")
	WebElement statebutton;

	@FindBy(css = "div.k-list.k-list-md ul li")
	WebElement stateOptions;

	@FindBy(id = "PatientEmployerZipCode")
	WebElement zipcode;

	// Contact Details
	@FindBy(id = "PatientEmployerPhone")
	WebElement employephone;

	@FindBy(id = "PatientEmployerPhoneExt")
	WebElement extension;

	@FindBy(id = "PatientEmployerFax")
	WebElement fax;

	@FindBy(id = "PatientEmployerWebsite")
	WebElement website;

	// ------------------------
	// Public Methods
	// ------------------------
	public void isEmployerDisplayed() throws InterruptedException {

		waitForVisibility(employetab);
		Thread.sleep(1000);
	}

	public void verifyEmployerTabText() throws InterruptedException {
		Thread.sleep(1000);
		waitForVisibility(employetab);
		String actualText = employertextname.getText().trim();
		String expectedText = "Employer";
		System.out.println("Employer tab text: " + actualText);
		Assert.assertEquals(actualText, expectedText, "Employer tab text does not match!");
	}

	public void addEmployerDetails(List<HashMap<String, Object>> listmap)
			throws InterruptedException, TimeoutException {
		fillCompanyDetails(listmap);
		fillContactDetails(listmap);
		clickSaveButton();
	}

	// ------------------------
	// Private Helpers
	// ------------------------
	private void fillCompanyDetails(List<HashMap<String, Object>> listmap) throws InterruptedException {
		// Company Name
		clearAndType(companyname, listmap.get(0).get("CompanyName").toString());
		Assert.assertEquals(companyname.getAttribute("value"), listmap.get(0).get("CompanyName").toString(),
				"CompanyName mismatch");

		// Occupation
		clearAndType(occupation, listmap.get(0).get("Occupation").toString());
		waitForVisibility(occupation);
		Assert.assertEquals(occupation.getAttribute("value"), listmap.get(0).get("Occupation").toString(),
				"Occupation mismatch");

		// Company Address
		clearAndType(companyaddress, listmap.get(0).get("Company Address").toString());
		waitForVisibility(companyaddress);
		Assert.assertEquals(companyaddress.getAttribute("value"), listmap.get(0).get("Company Address").toString(),
				"Company Address mismatch");

		// Suite
		clearAndType(suite, listmap.get(0).get("Suite").toString());
		waitForVisibility(suite);
		Assert.assertEquals(suite.getAttribute("value"), listmap.get(0).get("Suite").toString(), "Suite mismatch");

		// City
		clearAndType(city, listmap.get(0).get("City").toString());
		waitForVisibility(city);
		Assert.assertEquals(city.getAttribute("value"), listmap.get(0).get("City").toString(), "City mismatch");

		// State
		Thread.sleep(1000);
		statebutton.click();
		Thread.sleep(500);
		selectdropdown(stateOptions, listmap.get(0).get("State").toString());
		assertdropdownvalues(listmap.get(0).get("State").toString(), "State");

		// Zipcode
		clearAndType(zipcode, listmap.get(0).get("Zipcode").toString());
		waitForVisibility(zipcode);
		Assert.assertEquals(zipcode.getAttribute("value"), listmap.get(0).get("Zipcode").toString(),
				"Zipcode mismatch");
	}

	private void fillContactDetails(List<HashMap<String, Object>> listmap) {
		// Telephone
		clearAndType(employephone, listmap.get(0).get("Telephone").toString());
		waitForVisibility(employephone);
		Assert.assertEquals(employephone.getAttribute("value"), listmap.get(0).get("Telephone").toString(),
				"Telephone mismatch");

		// Ext
		clearAndType(extension, listmap.get(0).get("Ext").toString());
		waitForVisibility(extension);
		Assert.assertEquals(extension.getAttribute("value"), listmap.get(0).get("Ext").toString(), "Ext mismatch");

		// Fax
		clearAndType(fax, listmap.get(0).get("Fax").toString());
		waitForVisibility(fax);
		Assert.assertEquals(fax.getAttribute("value"), listmap.get(0).get("Fax").toString(), "Fax mismatch");

		// Website
		clearAndType(website, listmap.get(0).get("Website").toString());
		Assert.assertEquals(website.getAttribute("value"), listmap.get(0).get("Website").toString(),
				"Website mismatch");
	}

}
