package com.forteholdings.turncloud.pageobject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class MultipleDemographics extends CommonCode {

	WebDriver driver;

	public MultipleDemographics(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "NewPatientButton")
	WebElement addbutton;
	
	By patient = By.id("patient");

	By demographic = By.id("PatientDemographics");

	@FindBy(css = "div[class='PatientSideBarContainer'] li[id='PatientDemographics']")
	WebElement Demographics;

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

	By Maritialstatus = By.id("PatientDemographicsMaritalStatus-list");

	@FindBy(id = "PatientDemographicsSpouse")
	WebElement spouse;

	@FindBy(id = "PatientDemographicsEmail")
	WebElement email;

	@FindBy(id = "PatientDemographicsSSN")
	WebElement ssn;

	@FindBy(id = "PatientDemographicsMobilePhone")
	WebElement mobile;

	@FindBy(css = "span[aria-controls='PatientDemographicsSex_listbox'] button")
	WebElement gender;

	@FindBy(id = "PatientDemographicsSex_listbox")
	WebElement genderoptions;
	
	@FindBy(xpath="//li[@alt='Lookup']")
	WebElement patientlookup;

	// DOB
	@FindBy(id = "PatientDemographicsDOB")
	WebElement dobbutton;

	// Year selector
	@FindBy(xpath = "//a[@class='k-link' and normalize-space()]")
	WebElement selectyear;

	// prev button
	@FindBy(css = "a.k-nav-prev.k-icon-button[aria-label='Previous']")
	WebElement previousbutton;

	// next button
	@FindBy(xpath = "//a[@data-action='next' and @aria-label='Next']")
	WebElement nextbutton;

	@FindBy(xpath = "//a[@class='k-link' and @data-value and normalize-space()]")
	WebElement date;

	@FindBy(xpath = "//a[@class='k-link' and normalize-space()]")
	WebElement month;

	public void isdemopagedisplayed() throws InterruptedException {
		Thread.sleep(1000);
		waitForVisibility(demographic);
	}

	public String demographicPagedisplayed() {
		// TODO Auto-generated method stub

		waitForVisibility(patient);

		return Demographics.getText();

	}

	public String getDemographicTabLabel() {
		return driver.findElement(demographic).getText();
	}

	public void addPatient(List<HashMap<String, Object>> listMap) throws InterruptedException {
		if (listMap == null || listMap.isEmpty()) {
            throw new IllegalArgumentException("Patient data is missing or empty.");
        }

        for (int i = 0; i < listMap.size(); i++) {
            HashMap<String, Object> patientData = listMap.get(i);

            Thread.sleep(2000); // Ensure form is loaded

            clearAndType(first, patientData.get("First").toString());
            clearAndType(middlename, patientData.get("M").toString());
            clearAndType(last, patientData.get("Last").toString());
            clearAndType(prefferedname, patientData.get("Preferred Name").toString());

            selectitem.click();
            Thread.sleep(1000);
            selectdropdown(selectitem, patientData.get("Marital Status").toString());

            clearAndType(spouse, patientData.get("Spouse").toString());

            Thread.sleep(1000);
            dobbutton.click();
            selectDOB(dobbutton, patientData.get("DOB").toString());

            gender.click();
            Thread.sleep(500);
            selectdropdown(genderoptions, patientData.get("Gender").toString());

            clearAndType(email, patientData.get("Email").toString());
            Thread.sleep(1000);

            ssn.click();
            clearAndType(ssn, patientData.get("SSN").toString());

            mobile.click();
            clearAndType(mobile, patientData.get("Mobile").toString());

            clickSaveButton();
            Thread.sleep(3000); // Wait for save

            if (i < listMap.size() - 1) {
                patientlookup.click();
                Thread.sleep(1500);

                addbutton.click();
                Thread.sleep(3000);

                // Reinitialize PageFactory to refresh WebElements for new form
                PageFactory.initElements(driver, this);
            }
        }
	}

	public void selectDOB(WebElement element, String dobValue) throws InterruptedException {

		Thread.sleep(1000);

		// Clear the input
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);

		// Remove slashes and type one character at a time with short delay
		String cleanDOB = dobValue.replace("/", "");

		for (char ch : cleanDOB.toCharArray()) {
			element.sendKeys(Character.toString(ch));
			try {
				Thread.sleep(100); // Slight pause to mimic real typing, can be tuned or removed if not needed
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		// Tab out to trigger blur/validation
		element.sendKeys(Keys.TAB);
	}

	public void selectdropdown(WebElement element, String value) {
		element.findElement(By.xpath("//span[text()='" + value + "']")).click();
	}

	public void clearAndType(WebElement element, String value) {
		element.click(); // Focus the field
		element.sendKeys(Keys.CONTROL + "a"); // Select all text
		element.sendKeys(Keys.DELETE); // Delete it
		element.sendKeys(value); // Type the new value
	}

}