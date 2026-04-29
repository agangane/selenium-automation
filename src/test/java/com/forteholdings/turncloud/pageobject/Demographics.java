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

public class Demographics extends CommonCode {

	WebDriver driver;

	public Demographics(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===========================
	// Locators (By)
	// ===========================
	By patient = By.id("patient");
	By demographic = By.id("PatientDemographics");
	By Maritialstatus = By.id("PatientDemographicsMaritalStatus-list");

	// ===========================
	// Page Elements
	// ===========================

	// ---- Header ----
	@FindBy(xpath = "//span[@class='k-panelbar-item-text'][contains(., 'Demographics')]")
	WebElement demographicText;

	@FindBy(css = "div[class='PatientSideBarContainer'] li[id='PatientDemographics']")
	WebElement Demographics;

	// ---- Basic Details ----
	@FindBy(id = "PatientDemographicsNameFirst")
	WebElement first;
	@FindBy(id = "PatientDemographicsNameLast")
	WebElement last;
	@FindBy(id = "PatientDemographicsNameMI")
	WebElement middlename;
	@FindBy(id = "PatientDemographicsNamePreferred")
	WebElement prefferedname;

	@FindBy(css = "span[aria-controls=\"PatientDemographicsMaritalStatus_listbox\"]")
	WebElement selectitem;

	@FindBy(id = "PatientDemographicsSpouse")
	WebElement spouse;

	// DOB
	@FindBy(id = "PatientDemographicsDOB")
	WebElement dobbutton;
	@FindBy(xpath = "//a[@class='k-link' and normalize-space()]")
	WebElement selectyear;
	@FindBy(css = "a.k-nav-prev.k-icon-button[aria-label='Previous']")
	WebElement previousbutton;
	@FindBy(xpath = "//a[@data-action='next' and @aria-label='Next']")
	WebElement nextbutton;
	@FindBy(xpath = "//a[@class='k-link' and @data-value and normalize-space()]")
	WebElement date;
	@FindBy(xpath = "//a[@class='k-link' and normalize-space()]")
	WebElement month;

	// Gender
	@FindBy(css = "span[aria-controls='PatientDemographicsSex_listbox'] button")
	WebElement gender;

	@FindBy(css = "span[aria-controls='PatientDemographicsSex_listbox']")
	WebElement genderoptions;

	// ---- Contact Info ----
	@FindBy(id = "PatientDemographicsEmail")
	WebElement email;
	@FindBy(id = "PatientDemographicsSSN")
	WebElement ssn;
	@FindBy(id = "PatientDemographicsMobilePhone")
	WebElement mobile;

	// ---- Address ----
	@FindBy(id = "PatientDemographicsaddress")
	WebElement address1;
	@FindBy(id = "PatientDemographicsaddress2")
	WebElement address2;
	@FindBy(id = "PatientDemographicsCity")
	WebElement city;
	@FindBy(id = "PatientDemographicsZipCode")
	WebElement zipcode;

	@FindBy(css = "span[aria-controls=\"PatientDemographicsState_listbox\"] button")
	WebElement state;

	@FindBy(id = "PatientDemographicsState_listbox")
	WebElement statelist;

	// ---- Referral ----
	@FindBy(css = "span[aria-controls='PatientDemographicsReferralSourcedrp_listbox'] button")
	WebElement referraltype;

	@FindBy(id = "PatientDemographicsReferralSourcedrp_listbox")
	WebElement referaltypeoptions;

	@FindBy(id = "PatientDemographicsReferralSourceTxt")
	WebElement referalsource;

	@FindBy(id = "PatientDemographicsemailoptin")
	WebElement emailopt;

	// ---- User Fields ----
	@FindBy(xpath = "//label[text()='User Field 1']/following-sibling::input")
	WebElement userfield1;

	@FindBy(xpath = "(//label[text()='User Field Value'])[1]/following-sibling::input")
	WebElement userfieldvalue1;

	@FindBy(xpath = "//label[text()='User Field 2']/following-sibling::input")
	WebElement userfield2;

	@FindBy(xpath = "(//label[text()='User Field Value'])[2]/following-sibling::input")
	WebElement userfieldvalue2;

	// ===========================
	// Public Methods
	// ===========================

	public void isDemoPageDisplayed() throws TimeoutException {
		waitForVisibility(patient);
	}

	public String getDemographicText() {
		waitForVisibility(patient);
		return demographicText.getText().trim();
	}

	public void addPatient(List<HashMap<String, Object>> listMap) throws InterruptedException {
		onlyMandatoryDetails(listMap);
		// fillBasicDetails(listMap);
		// fillContactAndAddressDetails(listMap);
		// fillUserFields(listMap);
		clickSaveButton();
	}

	// ===========================
	// Section 1: Basic Details
	// ===========================

	// ==========Only mandatory Primaruy Details of Patient===================

	public void onlyMandatoryDetails(List<HashMap<String, Object>> listMap) throws InterruptedException {

		Thread.sleep(2500);

		// First Name
		clearAndType(first, listMap.get(0).get("First").toString());
		Assert.assertEquals(first.getAttribute("value"), listMap.get(0).get("First").toString(), "First name mismatch");

		// Middle Name
		clearAndType(middlename, listMap.get(0).get("M").toString());
		Assert.assertEquals(middlename.getAttribute("value"), listMap.get(0).get("M").toString(),
				"Middle name mismatch");

		// Last Name
		clearAndType(last, listMap.get(0).get("Last").toString());
		Assert.assertEquals(last.getAttribute("value"), listMap.get(0).get("Last").toString(), "Last name mismatch");

		// Preferred Name
		clearAndType(prefferedname, listMap.get(0).get("Preferred Name").toString());
		Assert.assertEquals(prefferedname.getAttribute("value"), listMap.get(0).get("Preferred Name").toString(),
				"Preferred name mismatch");

	}

	// ==========Full Details of Patient===================

	public void fillBasicDetails(List<HashMap<String, Object>> listMap) throws InterruptedException {
		Thread.sleep(1000);

		// First Name
		clearAndType(first, listMap.get(0).get("First").toString());
		Assert.assertEquals(first.getAttribute("value"), listMap.get(0).get("First").toString(), "First name mismatch");

		// Middle Name
		clearAndType(middlename, listMap.get(0).get("M").toString());
		Assert.assertEquals(middlename.getAttribute("value"), listMap.get(0).get("M").toString(),
				"Middle name mismatch");

		// Last Name
		clearAndType(last, listMap.get(0).get("Last").toString());
		Assert.assertEquals(last.getAttribute("value"), listMap.get(0).get("Last").toString(), "Last name mismatch");

		// Preferred Name
		clearAndType(prefferedname, listMap.get(0).get("Preferred Name").toString());
		Assert.assertEquals(prefferedname.getAttribute("value"), listMap.get(0).get("Preferred Name").toString(),
				"Preferred name mismatch");

		// Marital Status
		Thread.sleep(1000);
		selectitem.click();
		Thread.sleep(1000);
		selectdropdown(selectitem, listMap.get(0).get("Marital Status").toString());
		Assert.assertEquals(selectitem.getText().trim(), listMap.get(0).get("Marital Status").toString(),
				"Marital Status mismatch");

		// Spouse
		clearAndType(spouse, listMap.get(0).get("Spouse").toString());
		Assert.assertEquals(spouse.getAttribute("value"), listMap.get(0).get("Spouse").toString(),
				"Spouse name mismatch");

		// DOB
		Thread.sleep(1000);
		dobbutton.click();
		Thread.sleep(1000);
		selectDOB(dobbutton, listMap.get(0).get("DOB").toString());
		Assert.assertEquals(dobbutton.getAttribute("value"), listMap.get(0).get("DOB").toString(), "DOB mismatch");

		// Gender
		gender.click();
		Thread.sleep(1000);
		selectdropdown(genderoptions, listMap.get(0).get("Gender").toString());
		assertDropdownValue("Gender", listMap.get(0).get("Gender").toString());
	}

	// ===========================
	// Section 2: Contact & Address Details
	// ===========================
	public void fillContactAndAddressDetails(List<HashMap<String, Object>> listMap) throws InterruptedException {

		// Email
		clearAndType(email, listMap.get(0).get("Email").toString());
		Assert.assertEquals(email.getAttribute("value"), listMap.get(0).get("Email").toString(), "Email mismatch");

		// SSN
		Thread.sleep(1000);
		ssn.click();
		clearAndType(ssn, listMap.get(0).get("SSN").toString());
		Assert.assertEquals(ssn.getAttribute("value"), listMap.get(0).get("SSN").toString(), "SSN mismatch");

		// Mobile
		mobile.click();
		clearAndType(mobile, listMap.get(0).get("Mobile").toString());
		Assert.assertEquals(mobile.getAttribute("value"), listMap.get(0).get("Mobile").toString(), "Mobile mismatch");

		// Address1
		Thread.sleep(1000);
		clearAndType(address1, listMap.get(0).get("Address1").toString());
		Assert.assertEquals(address1.getAttribute("value"), listMap.get(0).get("Address1").toString(),
				"Address1 mismatch");

		// Address2
		clearAndType(address2, listMap.get(0).get("Address2").toString());
		Assert.assertEquals(address2.getAttribute("value"), listMap.get(0).get("Address2").toString(),
				"Address2 mismatch");

		// City
		clearAndType(city, listMap.get(0).get("City").toString());
		Assert.assertEquals(city.getAttribute("value"), listMap.get(0).get("City").toString(), "City mismatch");

		// Label: State
		state.click();
		Thread.sleep(1000);
		selectdropdown(statelist, listMap.get(0).get("State").toString());
		assertDropdownValue("State", listMap.get(0).get("State").toString());

		// Zipcode
		clearAndType(zipcode, listMap.get(0).get("Zipcode").toString());
		Assert.assertEquals(zipcode.getAttribute("value"), listMap.get(0).get("Zipcode").toString(),
				"Zipcode mismatch");

		// Referral Type
		referraltype.click();
		Thread.sleep(1000);
		selectdropdown(referaltypeoptions, listMap.get(0).get("Referal").toString());
		assertDropdownValue("Referal", listMap.get(0).get("Referal").toString());

		// Referral Source
		clearAndType(referalsource, listMap.get(0).get("ReferalSource").toString());
		Assert.assertEquals(referalsource.getAttribute("value"), listMap.get(0).get("ReferalSource").toString(),
				"Referral Source mismatch");
	}

	// ===========================
	// Section 3: User Fields
	// ===========================
	public void fillUserFields(List<HashMap<String, Object>> listMap) throws InterruptedException {

		// Email Opt-In
		emailopt.click();

		// User Field 1
		Thread.sleep(1000);
		clearAndType(userfield1, listMap.get(0).get("UserField 1").toString());
		Assert.assertEquals(userfield1.getAttribute("value"), listMap.get(0).get("UserField 1").toString(),
				"UserField 1 mismatch");

		// User Field Value 1
		clearAndType(userfieldvalue1, listMap.get(0).get("UserField 2").toString());
		Assert.assertEquals(userfieldvalue1.getAttribute("value"), listMap.get(0).get("UserField 2").toString(),
				"UserField 2 mismatch");

		// User Field 2
		clearAndType(userfield2, listMap.get(0).get("UserField 3").toString());
		Assert.assertEquals(userfield2.getAttribute("value"), listMap.get(0).get("UserField 3").toString(),
				"UserField 3 mismatch");

		// User Field Value 2
		clearAndType(userfieldvalue2, listMap.get(0).get("UserField 4").toString());
		Assert.assertEquals(userfieldvalue2.getAttribute("value"), listMap.get(0).get("UserField 4").toString(),
				"UserField 4 mismatch");
	}

	/*
	 * public void selectFromDropdown(WebElement dropdownButton, String listboxId,
	 * String valueToSelect) throws InterruptedException { dropdownButton.click();
	 * Thread.sleep(1000); String optionXpath = "//ul[@id='" + listboxId +
	 * "']//li[.//span[text()='" + valueToSelect + "']]";
	 * driver.findElement(By.xpath(optionXpath)).click(); Thread.sleep(500); }
	 */

}
