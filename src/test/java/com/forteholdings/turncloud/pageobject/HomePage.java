package com.forteholdings.turncloud.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class HomePage extends CommonCode {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//====================Demographics========================

	@FindBy(id = "Username")
	WebElement useremail;

	@FindBy(id = "Password")
	WebElement userpassword;

	@FindBy(id = "Account")
	WebElement useraccount;

	@FindBy(id = "Login_btn")
	WebElement login;

	By patient = By.id("patient");

	By LoadPatienlist = By.cssSelector("tbody[role=\\\"rowgroup\\\"] tr");

	@FindBy(css = "tbody[role=rowgroup] tr")
	private List<WebElement> patientList;

	@FindBy(id = "NewPatientButton")
	WebElement addbutton;

	@FindBy(css = "div[class='PatientSideBarContainer'] li[id='PatientDemographics']")
	WebElement Demographics;

	// ================== Employer button=====================

	@FindBy(id = "PatientEmployer")
	WebElement employerbutton;

	@FindBy(id = "PatientEmployerCompanyName")
	WebElement companyname;

	@FindBy(id = "SavePatient")
	WebElement save;

	// ============= Insurance Click==================

	@FindBy(id = "PatientInsurance")
	WebElement insuranceclick;

	@FindBy(xpath = "//span[text()='Add New Insurance']")
	WebElement addinsurancebutton;

	@FindBy(xpath = "(//table[contains(@class,'k-selectable')]//tbody[@role='rowgroup']//tr)[last()]")
	WebElement insurancerow;

	// ============Patient History===================

	@FindBy(css = "#PatientHistory")
	WebElement patienthistorytab;

	@FindBy(xpath = "//span[normalize-space()='Add New Allergy']")
	WebElement addallergytext;

	// ==============Cases Tab==========================

	@FindBy(id = "PatientCases")
	WebElement casestab;

	@FindBy(xpath = "//label[normalize-space()='Patient Case(s)']")
	WebElement patientcases;

	@FindBy(id = "addCase")
	WebElement addCaseButton;

	// ==============Visit Tab=====================

	@FindBy(id = "PatientVisitEHR")
	WebElement visittab;

	@FindBy(xpath = "//label[normalize-space()='Visit(s)']")
	WebElement visitname;

	// ==================Realease Notes===================

	@FindBy(xpath = "//span[text()='Turncloud Release Notes']")
	WebElement releaseNotesTitles;

	@FindBy(css = "a.k-window-action[aria-label='Close']")
	WebElement closebutton;

	// =================Methods========================
	public void initialSteps(String email, String password, String Account) throws InterruptedException {

		PageUrl();
		PageCredentials(email, password, Account);
		ispagedisplayed();
		closeRealseNotes();

	}

	public void PageUrl() {

		driver.get("https://my.turncloud.com");
	}

	public void PageCredentials(String name, String password, String accountnumber) {

		useremail.sendKeys(name);
		userpassword.sendKeys(password);
		useraccount.sendKeys(accountnumber);
		login.click();
	}

	public void ispagedisplayed() throws InterruptedException {

		waitForVisibility(patient);
		Thread.sleep(5000);
		// waitForVisibility(LoadPatienlist);

	}

	public void closeRealseNotes() throws InterruptedException {

		Thread.sleep(1000); // wait for popup render

		waitForVisibility(releaseNotesTitles);
		closebutton.click();
	}

	public String getPatientsTabLabel() {
		// TODO Auto-generated method stub
		return driver.findElement(patient).getText();
	}

	public void addPatientButton() throws InterruptedException {
		// NewPatient patient = new NewPatient(driver);
		Thread.sleep(2000);
		waitForVisibilityOfAllElements(patientList);
		waitForVisibility(addbutton);
		addbutton.click();
		// return patient;
	}

	public void addEmployerTab() throws InterruptedException {
		Thread.sleep(1000);
		employerbutton.click();
		waitForVisibility(companyname);

	}

	public void addInsuranceTab() throws InterruptedException {
		// NewPatient patient = new NewPatient(driver);
		insuranceclick.click();
		clickAddInsuranceButtonAndRow();
		// return patient;
	}

	public void clickAddInsuranceButtonAndRow() throws InterruptedException {
		addinsurancebutton.click();
		Thread.sleep(1000);
		insurancerow.click();
	}

	public void addPatientHistoryTab() throws InterruptedException {

		Thread.sleep(500);
		patienthistorytab.click();
		waitForVisibility(addallergytext);

	}

	public void addCasesTab() throws InterruptedException {
		Thread.sleep(500);
		casestab.click();
		Thread.sleep(500);
		waitForVisibility(patientcases);
		clickAddCasesButtonAndRow();

	}

	public void clickAddCasesButtonAndRow() throws InterruptedException {
		// casestab.click();
		waitForVisibility(patientcases);

	}

	// ****chatgptcode**************

	public void clickAddNewCase() throws InterruptedException {
		waitForVisibility(addCaseButton);
		addCaseButton.click();
		Thread.sleep(2000); // app is slow → allow form to load
	}

	public void clickAddVisitTab() throws InterruptedException {
		Thread.sleep(500);
		visittab.click();
		waitForVisibility(visitname);

	}

}
