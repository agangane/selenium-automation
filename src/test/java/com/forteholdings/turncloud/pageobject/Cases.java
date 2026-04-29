package com.forteholdings.turncloud.pageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class Cases extends CommonCode {

	private WebDriver driver;
	private WebDriverWait wait;

	public Cases(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// ========== Case Tab ==========
	@FindBy(css = "#PatientCases .k-panelbar-item-text")
	private WebElement casesTabName;

	@FindBy(css = "div[id='PatientCasesGrid'] div[class='k-grid-content k-auto-scrollable'] table tr")
	List<WebElement> cases;

	// =============Case add button============

	@FindBy(xpath = "//button[@id='addCase']")
	WebElement addCasebutton;

	// ========== Case Dropdown ==========
	@FindBy(css = "#PatientCaseseditForm [aria-owns='PatientCasesCaseTypeDropDown_listbox'] > button")
	private WebElement caseTypeDropdown;

	@FindBy(css = "#PatientCasesCaseTypeDropDown-list li span")
	WebElement caseTypeOptions;

	// ================ Doctors ==============
	@FindBy(css = "#PatientCaseseditForm [aria-owns='PatientCasesDoctorDropDown_listbox'] > button")
	private WebElement caseDoctors;

	/*
	 * @FindBy(css = "div[id='PatientCasesDoctorDropDown-list'] li span") WebElement
	 * caseDoctorslist;
	 */

	private By doctorOptions = By.cssSelector("#PatientCasesDoctorDropDown-list li span");

	// ================= Other Service Facility ==============
	@FindBy(css = "#PatientCaseseditForm [aria-owns='PatientCasesOSFDropDown_listbox'] > button")
	private WebElement otherservicefacility;

	@FindBy(css = "div[id='PatientCasesOSFDropDown-list'] li span")
	WebElement otherservicefacilitylist;

	// ================== Similar illness qualifier ==============
	@FindBy(css = "#PatientCaseseditForm [aria-owns='PatientCaseSimilarIllnessQualifier_listbox'] > button")
	private WebElement similarillnessqualifier;

	@FindBy(css = "div[id='PatientCaseSimilarIllnessQualifier-list'] li span")
	WebElement similarillnessqualifierlist;

	// ===================Outside Liab=============================

	@FindBy(xpath = "//label[normalize-space(text())='Outside Lab:']/following-sibling::span//button")
	WebElement outsidelab;

	@FindBy(xpath = "//ul[@id=//label[normalize-space(.)='Outside Lab:']/following-sibling::span/@aria-owns]//span")
	WebElement outsideLablist;

	// ===============Patient Condition Related To Accident====================

	@FindBy(xpath = "//label[normalize-space(text())='Patient Condition Related To Accident:']/following-sibling::span//button")
	WebElement patientcondition;

	@FindBy(xpath = "//ul[@id=//label[normalize-space(.)='Patient Condition Related To Accident:']/following-sibling::span/@aria-owns]//span")
	WebElement patientconditionlist;

	// ===================Illness Qualifie=============================

	@FindBy(xpath = "//label[normalize-space(text())='Illness Qualifier:']/following-sibling::span//button")
	WebElement illness;

	@FindBy(xpath = "//ul[@id=//label[normalize-space(.)='Illness Qualifier:']/following-sibling::span/@aria-owns]//span")
	WebElement illnessqualifierlist;

	// ======================Accident====================

	@FindBy(xpath = "//label[normalize-space(text())='Accident State:']/following-sibling::span//button")
	WebElement accidentstate;

	@FindBy(xpath = "//ul[@id=//label[normalize-space(.)='Accident State:']/following-sibling::span/@aria-owns]//span")
	WebElement accidentsstaelist;

	// ========== Scroll Target ==========
	@FindBy(css = "#PatientCaseInsuranceGrid .k-button-text")
	private WebElement addInsuranceButton;

	// ==============Initial Complaints============

	@FindBy(id = "PatientCasesInitialComplaintDropDown")
	WebElement initalcomplaint;

	// ============Additional Claim Information:===========
	@FindBy(id = "PatientCaseAdditionalClaimInfo")
	WebElement additionalclaiminformtion;

	// ================MC Resubmission Code:=================

	@FindBy(xpath = "//label[normalize-space(text())='MC Resubmission Code:']/following-sibling::input")
	WebElement mcresubmissioncode;

	// =============== Prior Auth No:===========================

	@FindBy(id = "PatientCasePriorAuthNumber")
	WebElement prior;

	// =================Lab Charges:==================

	@FindBy(xpath = "(//label[normalize-space(text())='Lab Charges:']/following::input[@type='text' and not(contains(@style,'display: none'))])[1]")
	WebElement labcharges;

	// ===============MC Reference Number:===========

	@FindBy(xpath = "//label[normalize-space(text())='MC Reference Number:']/following-sibling::input")
	WebElement mcreference;

	// ====================Select Insurance===============

	@FindBy(xpath = "//div[@id='PatientCaseInsuranceGrid']//td[contains(@class, 'k-edit-cell')]//button[contains(@class, 'k-input-button')]")
	WebElement insuranceDropdown;

	@FindBy(xpath = "//div[contains(@class, 'k-state-border-up')]//div[contains(@class, 'k-list-content') and contains(@class, 'k-list-scroller')]//li[contains(@class, 'k-list-item')]")
	WebElement insuranceDropdownValues;

	// ===========overlays==================

	@FindBy(css = "div.k-overlay")
	List<WebElement> overlays;

	// ================== Page Methods ==================

	public void selectFromDropdown(List<HashMap<String, Object>> listmap) throws InterruptedException {

		allDropdownfields(listmap);
		// fillTextDetails(listmap);
		// scrollToAddInsurance();
		// insuranceForSelectedCase(listmap);

		// insuranceForSelectedCases(listmap);
		clickSaveButton();

	}

	public void verifyPageDisplayed() {
		System.out.println("Case Tab Name: " + casesTabName.getText().trim());
	}

	public void selectExistingCases(String value) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(cases));
		clickOnGrid(cases, value); // reuse CommonCode
	}

	// =============Multiple cases grid=====================

	public void selectExistingMultipleCases(String value) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(cases));
		clickOnGrid(cases, value); // reuse CommonCode
	}
//==================chatgptcode for select case===================================

	private void waitForOverlayToDisappear() {
		if (!overlays.isEmpty()) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
		}
	}

	public void selectCaseSafelyByDoctor(String doctorName) throws InterruptedException {

		// 1️⃣ Wait for grid overlay to disappear (THIS is key)
		waitForOverlayToDisappear();

		// 2️⃣ Get fresh rows AFTER overlay is gone
		List<WebElement> rows = driver
				.findElements(By.cssSelector("#PatientCasesGrid div.k-grid-content table tr.k-master-row"));

		// 3️⃣ Loop rows
		for (WebElement row : rows) {

			List<WebElement> cells = row.findElements(By.tagName("td"));
			if (cells.size() < 2) {
				continue;
			}

			String actualDoctor = cells.get(1).getText().replaceAll("\\s+", " ").trim();

			System.out.println("Row doctor → [" + actualDoctor + "]");

			if (actualDoctor.contains(doctorName.trim())) {
				Thread.sleep(1000);

				wait.until(ExpectedConditions.elementToBeClickable(cells.get(1)));
				cells.get(1).click();
				return;
			}
		}

		throw new RuntimeException("Doctor not found using safe method → " + doctorName);

	}

	public void selectCases(List<HashMap<String, Object>> listmap) throws InterruptedException {

		Thread.sleep(1000);
		String caseName = listmap.get(0).get("Doctor").toString();
		selectExistingCases(caseName);
	}

	public void addMutipleInsuranceDetails(HashMap<String, Object> map, String value) throws InterruptedException {

		List<String> casevalue = Arrays.asList(map.get("Doctor").toString().split(","));

		casevalue.forEach(caseValue -> {
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				selectExistingMultipleCases(value);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addCasebutton.click();
			clickSaveButton();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// home.clickAddInsuranceButtonAndRow();
		});
	}

	// ===================Multiple cases selection==================

	public void selectMultipleCases(List<HashMap<String, Object>> listmap) throws InterruptedException {

		Thread.sleep(800);
		String caseName = listmap.get(0).get("Doctor").toString();
		selectExistingCases(caseName);
	}

	public void allDropdownfields(List<HashMap<String, Object>> listmap) throws InterruptedException {

		Thread.sleep(1000);
		caseTypeDropdown.click();
		Thread.sleep(500);
		selectdropdown(caseTypeOptions, listmap.get(0).get("Case Type").toString());
		Thread.sleep(500);
		caseDoctors.click();
		Thread.sleep(2000);
		selectdropdowns(doctorOptions, listmap.get(0).get("Doctor").toString());

		Thread.sleep(1000);
		/*
		 * similarillnessqualifier.click(); Thread.sleep(500);
		 * selectdropdown(similarillnessqualifierlist,
		 * listmap.get(0).get("Similar Illness Qualifier").toString());
		 * Thread.sleep(1000); otherservicefacility.click(); Thread.sleep(500);
		 * selectdropdown(otherservicefacilitylist,
		 * listmap.get(0).get("Other Service Facility").toString()); Thread.sleep(500);
		 *
		 * Thread.sleep(500); outsidelab.click(); Thread.sleep(800);
		 * selectdropdown(outsideLablist, listmap.get(0).get("Outside Lab").toString());
		 *
		 * Thread.sleep(500); patientcondition.click(); Thread.sleep(500);
		 * selectdropdown(patientconditionlist,
		 * listmap.get(0).get("Patient Condition Related To Accident").toString());
		 *
		 * Thread.sleep(500); illness.click(); Thread.sleep(500);
		 * selectdropdown(illnessqualifierlist,
		 * listmap.get(0).get("Illness Qualifier").toString());
		 *
		 * Thread.sleep(500); accidentstate.click(); Thread.sleep(500);
		 * selectdropdown(accidentsstaelist,
		 * listmap.get(0).get("Accident State").toString());
		 *
		 * Thread.sleep(800);
		 */

	}

	public void fillTextDetails(List<HashMap<String, Object>> listmap) throws InterruptedException {

		clearAndType(initalcomplaint, listmap.get(0).get("Initial Complaint").toString());
		Thread.sleep(500);
		clearAndType(additionalclaiminformtion, listmap.get(0).get("Additional Claim Information").toString());
		Thread.sleep(500);
		clearAndType(mcresubmissioncode, listmap.get(0).get("MC Resubmission Code").toString());
		Thread.sleep(500);
		clearAndType(prior, listmap.get(0).get("Prior Auth No").toString());
		Thread.sleep(500);
		clearAndType(labcharges, listmap.get(0).get("Lab Charges").toString());
		Thread.sleep(500);
		clearAndType(mcreference, listmap.get(0).get("MC Reference Number").toString());
		Thread.sleep(500);

	}

	/** Scrolls to Add Insurance button */
	public void scrollToAddInsurance() {
		scrollToElement(addInsuranceButton, true);
	}

	// ===============Insurance for Selected Case====================

	public void insuranceForSelectedCase(List<HashMap<String, Object>> listmap) throws InterruptedException {

		Thread.sleep(500);
		addInsuranceButton.click();
		Thread.sleep(500);
		insuranceDropdown.click();
		Thread.sleep(500);
		insuranceDropdownValues.getText().trim();
		selectdropdown(insuranceDropdownValues, listmap.get(0).get("Patient Insurance").toString());

	}

	public void insuranceForSelectedCases(List<HashMap<String, Object>> listmap) throws InterruptedException {

		for (HashMap<String, Object> map : listmap) {
			ArrayList<String> insuranceValues = (ArrayList<String>) map.get("Patient Insurance");

			insuranceValues.forEach(insuranceValue -> {
				addInsuranaceCase(insuranceValue);
			});
		}
	}

	public void addInsuranaceCase(String insuranceValue) {
		try {
			Thread.sleep(500);
			addInsuranceButton.click();
			insuranceDropdown.click();
			Thread.sleep(500);
			selectdropdown(insuranceDropdown, insuranceValue);
		} catch (Exception ex) {
			// do nothing
		}
	}

	public void waitForCaseFormReady() throws InterruptedException {
		// TODO Auto-generated method stub
		// wait for overlay to disappear
		By overlay = By.cssSelector("div.k-overlay");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));

		// extra safety for slow app
		Thread.sleep(2000);

		// ensure Case Type dropdown is clickable
		wait.until(ExpectedConditions.elementToBeClickable(caseTypeDropdown));
	}
}
