package com.forteholdings.turncloud.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class PatientLookupGrid extends CommonCode {

	// -------------------- Web Elements --------------------
	@FindBy(css = "tbody[role=rowgroup] tr")
	private List<WebElement> patientList;

	@FindBy(css = "div[class='PatientSideBarContainer'] li[id='PatientDemographics']")
	private WebElement demographicsTab;

	private final By patientTabLocator = By.id("PatientOverview");
	private final By demographicLocator = By.id("PatientDemographics");

	// -------------------- Constructor --------------------
	public PatientLookupGrid(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// -------------------- Actions --------------------
	public void selectPatient(String value) {
		waitForVisibilityOfAllElements(patientList);

		patientList.stream()
				.filter(row -> row.getText().replaceAll("\\s+", " ").toLowerCase().contains(value.toLowerCase()))
				.findFirst().orElseThrow(() -> new RuntimeException("Patient not found in grid: " + value)).click();
	}

	public void waitForPatientTabVisibility() {
		waitForVisibility(patientTabLocator);
	}

	public void clickDemographicsTab() {
		demographicsTab.click();
	}

	public void waitForDemographicsPage() throws InterruptedException {
		Thread.sleep(1000); // Consider replacing with explicit wait in future
		waitForVisibility(demographicLocator);
	}

	public void selectPatientSafely(String accountNumber) {

		for (WebElement row : patientList) {
			if (row.getText().contains(accountNumber)) {
				row.click();
				return;
			}
		}

		throw new RuntimeException("Patient not found in grid → " + accountNumber);
	}

	public boolean isPatientPresent(String value) {

		waitForVisibilityOfAllElements(patientList);

		return patientList.stream().anyMatch(row -> row.getText().contains(value));
	}

}
